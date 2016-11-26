package io.github.joaoevangelista.convertx.activity

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.CardView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewAnimationUtils
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.jakewharton.rxbinding.widget.RxTextView
import io.github.joaoevangelista.convertx.HistoryFragment
import io.github.joaoevangelista.convertx.R
import io.github.joaoevangelista.convertx.R.id
import io.github.joaoevangelista.convertx.R.layout
import io.github.joaoevangelista.convertx.R.string
import io.github.joaoevangelista.convertx.op.ConversionExecutor
import io.github.joaoevangelista.convertx.op.ConversionTypes
import io.github.joaoevangelista.convertx.op.NamedUnit
import io.github.joaoevangelista.convertx.op.conversions
import io.github.joaoevangelista.convertx.op.typesMap
import io.github.joaoevangelista.convertx.support.CopyManager
import io.github.joaoevangelista.convertx.support.bindView
import it.sephiroth.android.library.tooltip.Tooltip.AnimationBuilder
import it.sephiroth.android.library.tooltip.Tooltip.Builder
import it.sephiroth.android.library.tooltip.Tooltip.ClosePolicy
import it.sephiroth.android.library.tooltip.Tooltip.Gravity.BOTTOM
import it.sephiroth.android.library.tooltip.Tooltip.make
import me.grantland.widget.AutofitTextView
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit.MILLISECONDS


class MasterActivity : AppCompatActivity() {

  private val toolbar: Toolbar by bindView(id.toolbar)

  private val title: TextView by bindView(id.toolbar_title)

  private val conversionTypes: Spinner by bindView(id.conversion_spinner_selector)

  private val fromUnitSpinner: Spinner by bindView(id.spinner_from_unit)

  private val toUnitSpinner: Spinner by bindView(id.spinner_to_unit)

  private val dataInput: EditText by bindView(id.data_input)

  private val resultCard: CardView by bindView(id.result_card_view)

  private val resultText: AutofitTextView by bindView(id.result_text_view)

  private val copyResultButton: ImageButton by bindView(id.btn_copy_result)

  private val copyFormattedResultButton: ImageButton by bindView(id.btn_copy_formatted_result)

  private var currentConversion: ConversionTypes = conversions[0]

  private val executor = ConversionExecutor()

  private lateinit var copyManager: CopyManager

  private lateinit var textChangesSubscription: Subscription

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_master)

    setSupportActionBar(toolbar)
    supportActionBar?.setDisplayShowTitleEnabled(false)

    setCustomFonts()

    copyManager = CopyManager(this)

    val names = conversions.map { it -> getString(it.title) }
    val conversionTypesAdapter = ArrayAdapter<String>(baseContext, layout.first_item_spinner_layout,
      names)
    conversionTypesAdapter.setDropDownViewResource(layout.simple_toolbar_spinner_item)
    conversionTypes.adapter = conversionTypesAdapter

    conversionTypes.onItemSelectedListener = object : OnItemSelectedListener {
      override fun onNothingSelected(adapter: AdapterView<*>?) {
      }

      override fun onItemSelected(adapter: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val conversion = conversions[position]
        UnitsSelectedHolder.clear()
        loadTypesForConversion(conversion)
      }
    }

    copyFormattedResultButton.setOnClickListener {
      copyManager.copyFormattedToClipboard(resultText.text.toString(), { notifyCopied() })
    }
    copyResultButton.setOnClickListener {
      copyManager.copyToClipboard(resultText.text.toString(), { notifyCopied() })
    }
    copyResultButton.setOnLongClickListener { v ->
      val builder = genTooltipBuilder(v, string.copy_result, COPY_BUTTON_REQ_ID)
      make(this, builder).show()
      true
    }
    copyFormattedResultButton.setOnLongClickListener { v ->
      val builder = genTooltipBuilder(v, string.copy_formatted_result, COPY_FORMATTED_BUTTON_REQ_ID)
      make(this, builder).show()
      true
    }

    textChangesSubscription = RxTextView.textChanges(dataInput).debounce(500, MILLISECONDS)
      .map { it.toString() }
      .subscribeOn(AndroidSchedulers.mainThread())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe {
        decideActionUponEmpty(it)
      }


    // load initial set of types
    loadTypesForConversion(conversions[0])
    this.currentConversion = conversions[0]

    // add history fragment
    supportFragmentManager.beginTransaction()
      .replace(R.id.frame_holder, HistoryFragment.newInstance())
      .commit()
  }

  private fun genTooltipBuilder(v: View?, @StringRes text: Int, reqId: Int): Builder? {
    return Builder(reqId).anchor(v, BOTTOM).closePolicy(ClosePolicy.TOUCH_ANYWHERE_NO_CONSUME, 2500)
      .activateDelay(200)
      .showDelay(300)
      .text(resources, text)
      .withStyleId(R.style.AppTheme_Tooltip)
      .withOverlay(false)
      .floatingAnimation(AnimationBuilder.SLOW)
  }

  private fun notifyCopied() {
    Toast.makeText(this, R.string.text_copied_notification, Toast.LENGTH_SHORT).show()
  }

  private fun decideActionUponEmpty(input: String) {
    if (input.isNotBlank()) {
      executor.execute(input, { updatedResultBox(it) })
    } else {
      closeResultBox()
    }
  }

  private fun setCustomFonts() {
    val customFont = Typeface.createFromAsset(assets, getString(string.custom_font))
    title.typeface = customFont
    dataInput.typeface = customFont
    resultText.typeface = customFont
  }

  private fun loadTypesForConversion(conversionTypes: ConversionTypes) {
    val typeUnits = typesMap.find { it.first == conversionTypes }?.second
    // Set up the adapters
    setToUnitAdapter(typeUnits)
    setFromUnitAdapter(typeUnits)
  }

  private fun setToUnitAdapter(typeUnits: Array<out NamedUnit>?) {
    val toUnitAdapter = ArrayAdapter<String>(baseContext, layout.first_item_spinner_layout,
      typeUnits?.map { it -> getString(it.t()) })
    toUnitAdapter.setDropDownViewResource(layout.simple_spinner_item)
    toUnitSpinner.onItemSelectedListener = ToUnitItemSelected(typeUnits,
      notifyChange = {
        executor.execute(dataInput.text.toString(), onResult = { updatedResultBox(it) })
      })
    toUnitSpinner.adapter = toUnitAdapter
  }

  private fun setFromUnitAdapter(typeUnits: Array<out NamedUnit>?) {
    val fromUnitAdapter = ArrayAdapter<String>(baseContext, layout.first_item_spinner_layout,
      typeUnits?.map { it -> getString(it.t()) })
    fromUnitAdapter.setDropDownViewResource(layout.simple_spinner_item)
    fromUnitSpinner.onItemSelectedListener = FromUnitItemSelected(typeUnits,
      notifyChange = {
        executor.execute(dataInput.text.toString(), onResult = { updatedResultBox(it) })
      })
    fromUnitSpinner.adapter = fromUnitAdapter
  }

  override fun onDestroy() {
    super.onDestroy()
    if (!textChangesSubscription.isUnsubscribed) {
      textChangesSubscription.unsubscribe()
    }
  }

  private fun updatedResultBox(result: Double) {
    resultText.text = result.toString()
    val isNotVisible = resultCard.visibility != View.VISIBLE
    if (isNotVisible) {
      enterReveal()
    }
  }

  private fun closeResultBox() {
    exitReveal()
  }

  private fun enterReveal() {
    // get the center
    val cx = resultCard.measuredWidth / 2
    val cy = resultCard.measuredHeight / 2
    // get final radius
    val finalRadius = Math.max(resultCard.width, resultCard.height / 2).toFloat()

    var anim: Animator? = null

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      anim = ViewAnimationUtils.createCircularReveal(resultCard, cx, cy, 0f, finalRadius)
    }
    resultCard.visibility = View.VISIBLE
    anim?.start()

  }

  private fun exitReveal() {
    // get the center
    val cx = resultCard.measuredWidth / 2
    val cy = resultCard.measuredHeight / 2
    // get intial radius
    val initialRadius = resultCard.width / 2


    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      val anim = ViewAnimationUtils.createCircularReveal(resultCard, cx, cy,
        initialRadius.toFloat(), 0f)

      anim.addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator) {
          super.onAnimationEnd(animation)
          resultCard.visibility = View.GONE
        }
      })

      anim.start()
    } else {
      resultCard.visibility = View.GONE
    }
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    // Inflate the menu; this adds items to the action bar if it is present.
    menuInflater.inflate(R.menu.menu_master, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    val id = item.itemId

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true
    }
    return super.onOptionsItemSelected(item)
  }


  companion object {
    val COPY_BUTTON_REQ_ID = 101
    val COPY_FORMATTED_BUTTON_REQ_ID = 102
  }
}
