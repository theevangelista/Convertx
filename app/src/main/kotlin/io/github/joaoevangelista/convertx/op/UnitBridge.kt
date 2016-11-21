package io.github.joaoevangelista.convertx.op

import javax.measure.unit.Unit

/**
 * @author Joao Pedro Evangelista
 */
class UnitBridge {
  /**
   * Return our the Measure type of spec from the unit
   */
  fun measureUnit(measure: MeasureUnits) = Unit.valueOf(measure.unit)!!

  fun temperatureUnit(temperatures: Temperatures) = Unit.valueOf(temperatures.unit)!!
}
