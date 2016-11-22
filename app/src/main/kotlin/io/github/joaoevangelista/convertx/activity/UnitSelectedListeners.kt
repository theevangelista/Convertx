package io.github.joaoevangelista.convertx.activity

import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import io.github.joaoevangelista.convertx.op.NamedUnit

class ToUnitItemSelected(val typeUnits: Array<out NamedUnit>?,
  val notifyChange: () -> Unit) : OnItemSelectedListener {
  override fun onNothingSelected(adapter: AdapterView<*>?) {
    UnitsSelectedHolder.toUnit = typeUnits?.get(0)
  }

  override fun onItemSelected(adapter: AdapterView<*>?, view: View?, position: Int, id: Long) {
    UnitsSelectedHolder.toUnit = typeUnits?.get(position)
    notifyChange()
  }
}

class FromUnitItemSelected(val typeUnits: Array<out NamedUnit>?,
  val notifyChange: () -> Unit) : OnItemSelectedListener {
  override fun onNothingSelected(adapter: AdapterView<*>?) {
    UnitsSelectedHolder.fromUnit = typeUnits?.get(0)
  }

  override fun onItemSelected(adapter: AdapterView<*>?, view: View?, position: Int, id: Long) {
    UnitsSelectedHolder.fromUnit = typeUnits?.get(position)
    notifyChange()
  }
}

object UnitsSelectedHolder {

  var fromUnit: NamedUnit? = null

  var toUnit: NamedUnit? = null

}
