package io.github.joaoevangelista.convertx.activity

import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import io.github.joaoevangelista.convertx.op.NamedUnit

/**
 * @author Joao Pedro Evangelista
 */
object UnitsSelectedHolder {

  var fromUnit: NamedUnit? = null

  var toUnit: NamedUnit? = null

}

class ToUnitItemSelected(val typeUnits: Array<out NamedUnit>?) : OnItemSelectedListener {
  override fun onNothingSelected(adapter: AdapterView<*>?) {
    UnitsSelectedHolder.toUnit = typeUnits?.get(0)
  }

  override fun onItemSelected(adapter: AdapterView<*>?, view: View?, position: Int, id: Long) {
    UnitsSelectedHolder.toUnit = typeUnits?.get(position)
  }
}

class FromUnitItemSelected(val typeUnits: Array<out NamedUnit>?) : OnItemSelectedListener {
  override fun onNothingSelected(adapter: AdapterView<*>?) {
    UnitsSelectedHolder.fromUnit = typeUnits?.get(0)
  }

  override fun onItemSelected(adapter: AdapterView<*>?, view: View?, position: Int, id: Long) {
    UnitsSelectedHolder.fromUnit = typeUnits?.get(position)
  }
}


