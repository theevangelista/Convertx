package io.github.joaoevangelista.convertx.op

import io.github.joaoevangelista.convertx.op.Lengths.CENTIMETERS
import io.github.joaoevangelista.convertx.op.Lengths.KILOMETERS
import io.github.joaoevangelista.convertx.op.Lengths.METERS
import io.github.joaoevangelista.convertx.op.Lengths.MILES
import io.github.joaoevangelista.convertx.op.Lengths.MILLIMETERS
import org.junit.Test
import javax.measure.unit.NonSI
import javax.measure.unit.SI

/**
 * @author Joao Pedro Evangelista
 */
class LengthConversionsTest {

  @Test fun shouldConvertMetersToKilometers() {
    val measure = Conversions.ofLength(1000.toDouble(), METERS, KILOMETERS)
    assert(measure.value == 1.0, { "${measure.value} is not equal to 1.0" })
    assert(measure.unit == SI.KILOMETER)
  }

  @Test fun shouldConvertKilometersToMillimeters() {
    val measure = Conversions.ofLength(1.0, KILOMETERS, MILLIMETERS)
    assert(measure.value == 1000000.0, { "${measure.value} is not equal to 1000000.0" })
    assert(measure.unit == SI.MILLIMETER)
  }

  @Test fun shouldConvertMetersToMiles() {
    val measure = Conversions.ofLength(1.0, METERS, MILES)
    assert(measure.value == 6.213711922373339E-4,
      { "${measure.value} is not equal to 6.213711922373339E-4" })
    assert(measure.unit == NonSI.MILE)
  }

  @Test fun shouldConvertMetersToCentimeters() {
    val measure = Conversions.ofLength(1.0, METERS, CENTIMETERS)
    assert(measure.value == 100.0, { "${measure.value} is not equal to 100" })
    assert(measure.unit == SI.CENTIMETER)
  }

}
