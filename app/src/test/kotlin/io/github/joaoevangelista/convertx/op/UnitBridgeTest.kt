package io.github.joaoevangelista.convertx.op

import io.github.joaoevangelista.convertx.op.Lengths.CENTIMETERS
import io.github.joaoevangelista.convertx.op.Lengths.KILOMETERS
import io.github.joaoevangelista.convertx.op.Lengths.METERS
import io.github.joaoevangelista.convertx.op.Lengths.MILES
import io.github.joaoevangelista.convertx.op.Lengths.MILLIMETERS
import io.github.joaoevangelista.convertx.op.Temperatures.CELSIUS
import io.github.joaoevangelista.convertx.op.Temperatures.FAHRENHEIT
import io.github.joaoevangelista.convertx.op.Temperatures.KELVIN
import io.github.joaoevangelista.convertx.op.Volumes.CUBIC_INCH
import io.github.joaoevangelista.convertx.op.Volumes.CUBIC_METRE
import io.github.joaoevangelista.convertx.op.Volumes.LITER
import org.junit.Before
import org.junit.Test
import javax.measure.unit.NonSI
import javax.measure.unit.SI

/**
 * @author Joao Pedro Evangelista
 */
class UnitBridgeTest {

  private lateinit var bridge: UnitBridge

  @Before fun setup() {
    bridge = UnitBridge()
  }

  @Test fun shouldConvertMeasuresUnits() {
    assert(bridge.measureUnit(MILLIMETERS) == SI.MILLIMETER)
    assert(bridge.measureUnit(CENTIMETERS) == SI.CENTIMETER)
    assert(bridge.measureUnit(METERS) == SI.METER)
    assert(bridge.measureUnit(KILOMETERS) == SI.KILOMETER)
    assert(bridge.measureUnit(MILES) == NonSI.MILE)
  }

  @Test fun shouldConvertTemperatures() {
    assert(bridge.temperatureUnit(CELSIUS) == SI.CELSIUS)
    assert(bridge.temperatureUnit(FAHRENHEIT) == NonSI.FAHRENHEIT)
    assert(bridge.temperatureUnit(KELVIN) == SI.KELVIN)
  }

  @Test
  fun shouldConvertVolumes() {
    assert(bridge.volumeUnit(CUBIC_INCH) == NonSI.CUBIC_INCH)
    assert(bridge.volumeUnit(CUBIC_METRE) == SI.CUBIC_METRE)
    assert(bridge.volumeUnit(LITER) == NonSI.LITER)
  }
}
