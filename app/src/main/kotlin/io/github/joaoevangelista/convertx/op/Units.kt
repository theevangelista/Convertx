package io.github.joaoevangelista.convertx.op

import android.support.annotation.StringRes
import android.util.Pair
import io.github.joaoevangelista.convertx.R
import io.github.joaoevangelista.convertx.op.ConversionTypes.AREA
import io.github.joaoevangelista.convertx.op.ConversionTypes.FORCES
import io.github.joaoevangelista.convertx.op.ConversionTypes.LENGTH
import io.github.joaoevangelista.convertx.op.ConversionTypes.MASS
import io.github.joaoevangelista.convertx.op.ConversionTypes.TEMPERATURE
import io.github.joaoevangelista.convertx.op.ConversionTypes.VOLUME
import io.github.joaoevangelista.convertx.op.ConversionTypes.values
import java.util.Arrays

/**
 * Provides common accessor to the translatable name of a unit
 */
interface NamedUnit {
  @StringRes fun t(): Int
  override fun toString(): String
  fun valueOf(string: String): Any
}

enum class ConversionTypes(@StringRes val title: Int) {
  LENGTH(R.string.conversion_measure_unit), TEMPERATURE(R.string.conversion_temperature), AREA(
    R.string.conversion_area),
  VOLUME(R.string.conversion_volume), FORCES(R.string.conversion_force), MASS(
    R.string.conversion_mass)
}

enum class Lengths(val unit: String) : NamedUnit {
  METERS("m") {
    override fun valueOf(string: String): Any = Lengths.valueOf(string)
    override fun t(): Int = R.string.unit_length_meters
  },
  KILOMETERS("km") {
    override fun valueOf(string: String): Any = Lengths.valueOf(string)
    override fun t(): Int = R.string.unit_length_kilometers
  },
  CENTIMETERS("cm") {
    override fun valueOf(string: String): Any = Lengths.valueOf(string)
    override fun t(): Int = R.string.unit_length_centimeters
  },
  MILLIMETERS("mm") {
    override fun valueOf(string: String): Any = Lengths.valueOf(string)
    override fun t(): Int = R.string.unit_length_millimeters
  },
  MILES("mi") {
    override fun valueOf(string: String): Any = Lengths.valueOf(string)
    override fun t(): Int = R.string.unit_length_miles
  },
  FOOT("ft") {
    override fun valueOf(string: String): Any = Lengths.valueOf(string)
    override fun t(): Int = R.string.unit_length_foot
  },
  YARD("yd") {
    override fun valueOf(string: String): Any = Lengths.valueOf(string)
    override fun t(): Int = R.string.unit_length_yard
  },
  INCH("in") {
    override fun valueOf(string: String): Any = Lengths.valueOf(string)
    override fun t(): Int = R.string.unit_length_inch
  },
  NAUTICAL_MILES("nmi") {
    override fun valueOf(string: String): Any = Lengths.valueOf(string)
    override fun t(): Int = R.string.unit_length_nautica_miles
  },
  ANGSTROM("Å") {
    override fun valueOf(string: String): Any = Lengths.valueOf(string)
    override fun t(): Int = R.string.unit_length_angstrom
  },
  ASTRONOMICAL_UNIT("ua") {
    override fun valueOf(string: String): Any = Lengths.valueOf(string)
    override fun t(): Int = R.string.unit_length_astronomical_unit
  },
  LIGHT_YEAR("ly") {
    override fun valueOf(string: String): Any = Lengths.valueOf(string)
    override fun t(): Int = R.string.unit_length_light_year
  },
  PARSEC("pc") {
    override fun valueOf(string: String): Any = Lengths.valueOf(string)
    override fun t(): Int = R.string.unit_length_parsec
  },
  POINT("pt") {
    override fun valueOf(string: String): Any = Lengths.valueOf(string)
    override fun t(): Int = R.string.unit_length_point
  },
  PIXEL("pixel") {
    override fun valueOf(string: String): Any = Lengths.valueOf(string)
    override fun t(): Int = R.string.unit_length_pixel
  }
}

enum class Temperatures(val unit: String) : NamedUnit {
  CELSIUS("°C") {
    override fun valueOf(string: String): Any = Temperatures.valueOf(string)
    override fun t(): Int = R.string.unit_temperature_celsius
  },
  FAHRENHEIT("°F") {
    override fun valueOf(string: String): Any = Temperatures.valueOf(string)
    override fun t(): Int = R.string.unit_temperature_fahrenheit
  },
  KELVIN("K") {
    override fun valueOf(string: String): Any = Temperatures.valueOf(string)
    override fun t(): Int = R.string.unit_temperature_kelvin
  }
}

enum class Areas(val unit: String) : NamedUnit {
  SQUARE_METRE("m²") {
    override fun valueOf(string: String): Any = Areas.valueOf(string)
    override fun t(): Int = R.string.unit_area_square_metre
  },
  ARE("a") {
    override fun valueOf(string: String): Any = Areas.valueOf(string)
    override fun t(): Int = R.string.unit_area_are
  },
  HECTARE("ha") {
    override fun valueOf(string: String): Any = Areas.valueOf(string)
    override fun t(): Int = R.string.unit_area_hectare
  }
}

enum class Volumes(val unit: String) : NamedUnit {
  CUBIC_METRE("m³") {
    override fun valueOf(string: String): Any = Volumes.valueOf(string)
    override fun t(): Int = R.string.unit_volume_cubic_metre
  },
  LITER("L") {
    override fun valueOf(string: String): Any = Volumes.valueOf(string)
    override fun t(): Int = R.string.unit_volume_liter
  },
  CUBIC_INCH("in³") {
    override fun valueOf(string: String): Any = Volumes.valueOf(string)
    override fun t(): Int = R.string.unit_volume_cubic_inch
  }
}

enum class Forces(val unit: String) : NamedUnit {
  DYNE("dyn") {
    override fun valueOf(string: String): Any = Forces.valueOf(string)
    override fun t(): Int = R.string.unit_force_dyne
  },
  KILOGRAM_FORCE("kgf") {
    override fun valueOf(string: String): Any = Forces.valueOf(string)
    override fun t(): Int = R.string.unit_force_kilogram_force
  },
  POUND_FORCE("lbf") {
    override fun valueOf(string: String): Any = Forces.valueOf(string)
    override fun t(): Int = R.string.unit_force_pound_force
  },
  NEWTON("N") {
    override fun valueOf(string: String): Any = Forces.valueOf(string)
    override fun t(): Int = R.string.unit_force_newton
  }
}

enum class Mass(val unit: String) : NamedUnit {
  GRAMS("g") {
    override fun valueOf(string: String): Any = Mass.valueOf(string)
    override fun t(): Int = R.string.unit_mass_grams
  },
  KILOGRAM("kg") {
    override fun valueOf(string: String): Any = Mass.valueOf(string)
    override fun t(): Int = R.string.unit_mass_kilogram
  },
  TON("t") {
    override fun valueOf(string: String): Any = Mass.valueOf(string)
    override fun t(): Int = R.string.unit_mass_ton
  }
}

val areas = Areas.values()
val lengths = Lengths.values()
val temperatures = Temperatures.values()
val volumes = Volumes.values()
val conversions = values()
val forces = Forces.values()
val mass = Mass.values()
val units = Arrays.asList(*areas, *lengths, *temperatures, *volumes, *forces, *mass)
val typesMap = arrayListOf(
  Pair<ConversionTypes, Array<out NamedUnit>>(LENGTH, lengths),
  Pair<ConversionTypes, Array<out NamedUnit>>(AREA, areas),
  Pair<ConversionTypes, Array<out NamedUnit>>(VOLUME, volumes),
  Pair<ConversionTypes, Array<out NamedUnit>>(TEMPERATURE, temperatures),
  Pair<ConversionTypes, Array<out NamedUnit>>(FORCES, forces),
  Pair<ConversionTypes, Array<out NamedUnit>>(MASS, mass)
)

fun findUnit(name: String): NamedUnit {
  return units.find { it.toString() == name } as NamedUnit
}
