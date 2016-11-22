package io.github.joaoevangelista.convertx.op

import io.github.joaoevangelista.convertx.op.Temperatures.CELSIUS
import io.github.joaoevangelista.convertx.op.Temperatures.FAHRENHEIT
import io.github.joaoevangelista.convertx.op.Temperatures.KELVIN
import org.junit.Test
import javax.measure.unit.NonSI
import javax.measure.unit.SI

/**
 * @author Joao Pedro Evangelista
 */
class TemperatureConversionsTest {

  @Test fun shouldConvertKelvinToCelsius() {
    val measure = Conversions.ofTemperature(273.0, KELVIN, CELSIUS)
    assert(measure.value == -0.14999999999997726,
      { "${measure.value} is not equal to -0.14999999999997726" })
    assert(measure.unit == SI.CELSIUS)
  }

  @Test fun shouldConvertCelsiusToFahrenheit() {
    val measure = Conversions.ofTemperature(25.0, CELSIUS, FAHRENHEIT)
    assert(measure.value == 76.99999999999994,
      { "${measure.value} is not equal to 76.99999999999994" })
    assert(measure.unit == NonSI.FAHRENHEIT)
  }
}
