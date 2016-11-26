package io.github.joaoevangelista.convertx.op

import javax.measure.quantity.Quantity
import javax.measure.unit.Unit

/**
 * @author Joao Pedro Evangelista
 */
class UnitBridge {
  /**
   * Return our the Measure type of spec from the unit
   */
  fun measureUnit(measure: Lengths): Unit<out Quantity>? = Unit.valueOf(measure.unit)

  fun temperatureUnit(temperatures: Temperatures): Unit<out Quantity>? = Unit.valueOf(temperatures.unit)

  fun areaUnit(areas: Areas): Unit<out Quantity>? = Unit.valueOf(areas.unit)

  fun volumeUnit(volumes: Volumes): Unit<out Quantity>? = Unit.valueOf(volumes.unit)

  fun forceUnit(forces: Forces): Unit<out Quantity>? = Unit.valueOf(forces.unit)

  fun massUnit(mass: Mass): Unit<out Quantity>? = Unit.valueOf(mass.unit)
}
