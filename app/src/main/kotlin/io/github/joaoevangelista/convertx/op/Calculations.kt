package io.github.joaoevangelista.convertx.op

import java.math.BigDecimal

import javax.measure.Measure

/**
 * Perform calculations based on the type of operation, matching the available
 * into the designed module.
 *
 * @author Joao Pedro Evangelista
 * @since 1.0.0
 */
class Calculations {

  val matcher = Matcher()

  fun ofMeasures(commited: Pair<Double, MeasureUnits>, to: MeasureUnits) =
    Measure.valueOf(commited.first, matcher.measureUnit(commited.second))
      .to(matcher.measureUnit(to))

  fun ofTemperature(value: BigDecimal, from: Temperatures, to: Temperatures): Double {
    return 0.0
  }

}
