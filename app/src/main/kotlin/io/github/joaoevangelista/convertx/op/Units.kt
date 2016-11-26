package io.github.joaoevangelista.convertx.op

import android.support.annotation.StringRes
import android.util.Pair
import io.github.joaoevangelista.convertx.R
import io.github.joaoevangelista.convertx.op.ConversionTypes.AREA
import io.github.joaoevangelista.convertx.op.ConversionTypes.FORCES
import io.github.joaoevangelista.convertx.op.ConversionTypes.LENGTH
import io.github.joaoevangelista.convertx.op.ConversionTypes.TEMPERATURE
import io.github.joaoevangelista.convertx.op.ConversionTypes.VOLUME
import io.github.joaoevangelista.convertx.op.ConversionTypes.values

/**
 * Provides common accessor to the translatable name of a unit
 */
interface NamedUnit {
  @StringRes fun t(): Int
}

enum class ConversionTypes(@StringRes val title: Int) {
  LENGTH(R.string.conversion_measure_unit), TEMPERATURE(R.string.conversion_temperature), AREA(
    R.string.conversion_area),
  VOLUME(R.string.conversion_volume), FORCES(R.string.conversion_force)
}

enum class Lengths(val unit: String) : NamedUnit {
  METERS("m") {
    override fun t(): Int = R.string.unit_length_meters
  },
  KILOMETERS("km") {
    override fun t(): Int = R.string.unit_length_kilometers
  },
  CENTIMETERS("cm") {
    override fun t(): Int = R.string.unit_length_centimeters
  },
  MILLIMETERS("mm") {
    override fun t(): Int = R.string.unit_length_millimeters
  },
  MILES("mi") {
    override fun t(): Int = R.string.unit_length_miles
  },
  FOOT("ft") {
    override fun t(): Int = R.string.unit_length_foot
  },
  YARD("yd") {
    override fun t(): Int = R.string.unit_length_yard
  },
  INCH("in") {
    override fun t(): Int = R.string.unit_length_inch
  },
  NAUTICAL_MILES("nmi") {
    override fun t(): Int = R.string.unit_length_nautica_miles
  },
  ANGSTROM("Å") {
    override fun t(): Int = R.string.unit_length_angstrom
  },
  ASTRONOMICAL_UNIT("ua") {
    override fun t(): Int = R.string.unit_length_astronomical_unit
  },
  LIGHT_YEAR("ly") {
    override fun t(): Int = R.string.unit_length_light_year
  },
  PARSEC("pc") {
    override fun t(): Int = R.string.unit_length_parsec
  },
  POINT("pt") {
    override fun t(): Int = R.string.unit_length_point
  },
  PIXEL("pixel") {
    override fun t(): Int = R.string.unit_length_pixel
  }
}

enum class Temperatures(val unit: String) : NamedUnit {
  CELSIUS("°C") {
    override fun t(): Int = R.string.unit_temperature_celsius
  },
  FAHRENHEIT("°F") {
    override fun t(): Int = R.string.unit_temperature_fahrenheit
  },
  KELVIN("K") {
    override fun t(): Int = R.string.unit_temperature_kelvin
  }
}

enum class Areas(val unit: String) : NamedUnit {
  SQUARE_METRE("m²") {
    override fun t(): Int = R.string.unit_area_square_metre
  },
  ARE("a") {
    override fun t(): Int = R.string.unit_area_are
  },
  HECTARE("ha") {
    override fun t(): Int = R.string.unit_area_hectare
  }
}

enum class Volumes(val unit: String) : NamedUnit {
  CUBIC_METRE("m³") {
    override fun t(): Int = R.string.unit_volume_cubic_metre
  },
  LITER("L") {
    override fun t(): Int = R.string.unit_volume_liter
  },
  CUBIC_INCH("in³") {
    override fun t(): Int = R.string.unit_volume_cubic_inch
  }
}

enum class Forces(val unit: String) : NamedUnit {
  DYNE("dyn") {
    override fun t(): Int = R.string.unit_force_dyne
  },
  KILOGRAM_FORCE("kgf") {
    override fun t(): Int = R.string.unit_force_kilogram_force
  },
  POUND_FORCE("lbf") {
    override fun t(): Int = R.string.unit_force_pound_force
  },
  NEWTON("N") {
    override fun t(): Int = R.string.unit_force_newton
  }
}


val areas = Areas.values()
val lengths = Lengths.values()
val temperatures = Temperatures.values()
val volumes = Volumes.values()
val conversions = values()
val forces = Forces.values()
val typesMap = arrayListOf(
  Pair<ConversionTypes, Array<out NamedUnit>>(LENGTH, lengths),
  Pair<ConversionTypes, Array<out NamedUnit>>(AREA, areas),
  Pair<ConversionTypes, Array<out NamedUnit>>(VOLUME, volumes),
  Pair<ConversionTypes, Array<out NamedUnit>>(TEMPERATURE, temperatures),
  Pair<ConversionTypes, Array<out NamedUnit>>(FORCES, forces)
)
