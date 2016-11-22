package io.github.joaoevangelista.convertx.activity

import io.github.joaoevangelista.convertx.op.Areas
import io.github.joaoevangelista.convertx.op.Conversions
import io.github.joaoevangelista.convertx.op.Lengths
import io.github.joaoevangelista.convertx.op.Temperatures

/**
 * @author Joao Pedro Evangelista
 */
class ConversionExecutor {

  fun execute(input: String,
    onResult: (result: Double) -> Unit): Unit {

    if (!input.isBlank()) {
      val number = input.toDouble()
      val from = UnitsSelectedHolder.fromUnit
      val to = UnitsSelectedHolder.toUnit
      when (from) {
        is Lengths -> onResult(Conversions.ofLength(number, from, to as Lengths?).value as Double)
        is Temperatures -> onResult(
          Conversions.ofTemperature(number, from, to as Temperatures?).value as Double)
        is Areas -> onResult(Conversions.ofArea(number, from, to as Areas?).value as Double)
      }
    }

  }
}
