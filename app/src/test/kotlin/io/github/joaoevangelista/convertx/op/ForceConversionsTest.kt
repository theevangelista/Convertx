package io.github.joaoevangelista.convertx.op

import io.github.joaoevangelista.convertx.op.Forces.DYNE
import io.github.joaoevangelista.convertx.op.Forces.KILOGRAM_FORCE
import io.github.joaoevangelista.convertx.op.Forces.NEWTON
import io.github.joaoevangelista.convertx.op.Forces.POUND_FORCE
import org.junit.Test
import javax.measure.unit.NonSI
import javax.measure.unit.SI

/**
 * @author Joao Pedro Evangelista
 */
class ForceConversionsTest {

  @Test
  fun shouldConvertDyneIntoKilogramForce() {
    val measure = Conversions.ofForce(10.0, DYNE, KILOGRAM_FORCE)
    assert(measure.unit == NonSI.KILOGRAM_FORCE)
    assert(measure.value == 1.0197162129779282E-5,
      { "${measure.value} is not equal to 1.0197162129779282E-5" })
  }

  @Test
  fun shouldConvertPoundForceToDyne() {
    val measure = Conversions.ofForce(10.0, POUND_FORCE, DYNE)
    assert(measure.unit == NonSI.DYNE)
    assert(measure.value == 4448221.6152605, { "${measure.value} is not equal to 4448221.6152605" })
  }

  @Test
  fun shouldConvertNewtonToDyne() {
    val measure = Conversions.ofForce(10.0, NEWTON, DYNE)
    assert(measure.unit == NonSI.DYNE)
    assert(measure.value == 1000000.0, { "${measure.value} is not equal to 1000000.0" })
  }

  @Test
  fun shouldConvertKilogramForceToNewton() {
    val measure = Conversions.ofForce(10.0, KILOGRAM_FORCE, NEWTON)
    assert(measure.unit == SI.NEWTON)
    assert(measure.value == 98.0665, { "${measure.value} is not equal to 98.0665" })
  }
}
