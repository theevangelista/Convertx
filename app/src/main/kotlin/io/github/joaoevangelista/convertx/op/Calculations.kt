package io.github.joaoevangelista.convertx.op

import javax.measure.Measure

/**
 * Perform calculations based on the type of operation, matching the available
 * into the designed module.
 *
 * @author Joao Pedro Evangelista
 */
class Calculations {

  private val unitBridge = UnitBridge()

  fun ofMeasures(committed: Pair<Double, MeasureUnits>, to: MeasureUnits) = Measure.valueOf(
    committed.first, unitBridge.measureUnit(committed.second)).to(unitBridge.measureUnit(to))

  fun ofTemperature(committed: Pair<Double, Temperatures>, to: Temperatures) = Measure.valueOf(
    committed.first, unitBridge.temperatureUnit(committed.second)).to(unitBridge.temperatureUnit(to))
}
