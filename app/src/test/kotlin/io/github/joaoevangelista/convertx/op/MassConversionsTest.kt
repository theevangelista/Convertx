package io.github.joaoevangelista.convertx.op

import io.github.joaoevangelista.convertx.op.Mass.GRAMS
import io.github.joaoevangelista.convertx.op.Mass.KILOGRAM
import org.junit.Test
import javax.measure.unit.NonSI
import javax.measure.unit.SI


/**
 * @author Joao Pedro Evangelista
 */
class MassConversionsTest {

  @Test
  fun shouldConvertKilogramToTon() {
    val measure = Conversions.ofMass(1000.0, Mass.KILOGRAM, Mass.TON)
    assert(measure.unit == NonSI.METRIC_TON)
    assert(measure.value == 1.0, { "${measure.value} is not equal to 1.0" })
  }

  @Test
  fun shouldConvertGramToKilogram() {
    val measure = Conversions.ofMass(1000.0, GRAMS, KILOGRAM)
    assert(measure.unit == SI.KILOGRAM)
    assert(measure.value == 1.0, { "${measure.value} is not equal to 1.0" })
  }
}
