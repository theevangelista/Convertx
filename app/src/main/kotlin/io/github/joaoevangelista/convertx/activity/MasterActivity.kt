package io.github.joaoevangelista.convertx.activity

import android.animation.Animator
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
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
import android.widget.Spinner
import android.widget.TextView
import com.jakewharton.rxbinding.widget.RxTextView
import io.github.joaoevangelista.convertx.R
import io.github.joaoevangelista.convertx.R.id
import io.github.joaoevangelista.convertx.R.layout
import io.github.joaoevangelista.convertx.R.string
import io.github.joaoevangelista.convertx.bindView
import io.github.joaoevangelista.convertx.op.ConversionTypes
import io.github.joaoevangelista.convertx.op.NamedUnit
import io.github.joaoevangelista.convertx.op.conversions
import io.github.joaoevangelista.convertx.op.typesMap
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import java.text.DecimalFormat
import java.util.concurrent.TimeUnit.MILLISECONDS

class MasterActivity : AppCompatActivity() {

  private val toolbar: Toolbar by bindView(id.toolbar)

  private val title: TextView by bindView(id.toolbar_title)

  private val conversionTypes: Spinner by bindView(id.conversion_spinner_selector)

  private val fromUnitSpinner: Spinner by bindView(id.spinner_from_unit)

  private val toUnitSpinner: Spinner by bindView(id.spinner_to_unit)

  private val dataInput: EditText by bindView(id.data_input)

  private val resultCard: CardView by bindView(id.result_card_view)

  private val resultText: TextView by bindView(id.result_text_view)

  private var currentConversion: ConversionTypes = conversions[0]

  private val executor = ConversionExecutor()

  private val decimalFormatter = DecimalFormat("##.####")

  private lateinit var textChangesSubscription: Subscription

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_master)

    setSupportActionBar(toolbar)
    supportActionBar?.setDisplayShowTitleEnabled(false)

    setCustomFonts()

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
        loadTypesForConversion(conversion)
      }
    }

    textChangesSubscription = RxTextView.textChanges(dataInput).debounce(500,
      MILLISECONDS).map { it.toString() }
      .filter(String::isNotBlank)
      .subscribeOn(AndroidSchedulers.mainThread())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe { executor.execute(it, { updatedResultBox(it) }) }

    // load initial set of types
    loadTypesForConversion(conversions[0])
    this.currentConversion = conversions[0]
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
    val isNotVisible = resultCard.visibility != View.VISIBLE
    if (isNotVisible) {
      enterReveal()
    }
    resultText.text = result.toString()
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
}