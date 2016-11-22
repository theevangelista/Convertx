package io.github.joaoevangelista.convertx.op;

import javax.measure.Measure;
import javax.measure.unit.Unit;

/**
 * Main class that do conversion of units
 * It is written as Java since Jscience use {@link Measure#to(Unit)}
 * and it clashes with Kotlin built-in {@link kotlin.TuplesKt#to(Object, Object)}
 *
 * @author Joao Pedro Evangelista
 */
public class Conversions {

  private static final UnitBridge unitBridge = new UnitBridge();

  private Conversions() {
    //no instance
  }

  public static Measure ofLength(Double value, Lengths unit, Lengths target) {
    Measure measure = Measure.valueOf(value, unitBridge.measureUnit(unit));
    //noinspection unchecked
    return measure.to(unitBridge.measureUnit(target));
  }

  public static Measure ofTemperature(Double value, Temperatures unit, Temperatures target) {
    Measure measure = Measure.valueOf(value, unitBridge.temperatureUnit(unit));
    //noinspection unchecked
    return measure.to(unitBridge.temperatureUnit(target));
  }
}
