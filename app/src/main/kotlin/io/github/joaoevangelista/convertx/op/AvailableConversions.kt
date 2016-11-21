package io.github.joaoevangelista.convertx.op

import android.support.annotation.StringRes
import io.github.joaoevangelista.convertx.R

enum class Conversion(@StringRes val title: Int) {
  MEASURE_UNIT(R.string.conversion_measure_unit), TEMPERATURE(
    R.string.conversion_temperature),
  AREA(R.string.conversion_area)
}

enum class MeasureUnits {
  METERS, KILOMETERS, CENTIMETERS, MILLIMETERS
}

enum class Temperatures {
  CELSIUS, FAHRENHEIT, KELVIN
}

val measureUnits = MeasureUnits.values()
val temperatures = Temperatures.values()
val conversions = Conversion.values()
