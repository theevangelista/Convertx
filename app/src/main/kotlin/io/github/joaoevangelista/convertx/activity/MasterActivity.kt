package io.github.joaoevangelista.convertx.activity

import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import io.github.joaoevangelista.convertx.R
import io.github.joaoevangelista.convertx.R.id
import io.github.joaoevangelista.convertx.R.layout
import io.github.joaoevangelista.convertx.bindView
import io.github.joaoevangelista.convertx.op.Conversion
import io.github.joaoevangelista.convertx.op.Conversion.MEASURE_UNIT
import io.github.joaoevangelista.convertx.op.conversions

class MasterActivity : AppCompatActivity() {

  private val toolbar: Toolbar by bindView(id.toolbar)

  private val title: TextView by bindView(id.toolbar_title)

  private val conversionTypes: Spinner by bindView(id.conversion_spinner_selector)

  private val dataInput: EditText by bindView(id.data_input)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_master)

    setSupportActionBar(toolbar)
    supportActionBar?.setDisplayShowTitleEnabled(false)
    title.typeface = Typeface.createFromAsset(assets, getString(R.string.custom_font))

    dataInput.typeface = Typeface.createFromAsset(assets, getString(R.string.custom_font))

    val names = conversions.map { it -> getString(it.title) }
    val conversionTypesAdapter = ArrayAdapter<String>(baseContext, layout.first_item_spinner_layout , names)
    conversionTypesAdapter.setDropDownViewResource(layout.simple_spinner_item)
    conversionTypes.adapter = conversionTypesAdapter

    conversionTypes.onItemSelectedListener = object : OnItemSelectedListener {
      override fun onNothingSelected(adapter: AdapterView<*>?) {
      }

      override fun onItemSelected(adapter: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val conversion = conversions[position]
        loadTypesForConversion(conversion)
      }
    }

    dataInput.addTextChangedListener(object : TextWatcher {
      override fun afterTextChanged(p0: Editable?) {
      }

      override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
      }

      override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
      }
    })


    loadTypesForConversion(MEASURE_UNIT)
  }

  private fun loadTypesForConversion(conversion: Conversion) {
    // todo
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
