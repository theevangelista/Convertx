package io.github.joaoevangelista.convertx.op;

import android.support.annotation.NonNull;
import javax.measure.Measure;
import javax.measure.unit.Unit;
import org.jetbrains.annotations.NotNull;

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

  @NonNull public static Measure ofLength(Double value, Lengths unit, Lengths target) {
    Measure measure = Measure.valueOf(value, unitBridge.measureUnit(unit));
    //noinspection unchecked
    return measure.to(unitBridge.measureUnit(target));
  }

  @NonNull
  public static Measure ofTemperature(Double value, Temperatures unit, Temperatures target) {
    Measure measure = Measure.valueOf(value, unitBridge.temperatureUnit(unit));
    //noinspection unchecked
    return measure.to(unitBridge.temperatureUnit(target));
  }

  @NonNull public static Measure ofArea(Double value, Areas unit, Areas target) {
    Measure measure = Measure.valueOf(value, unitBridge.areaUnit(unit));
    //noinspection unchecked
    return measure.to(unitBridge.areaUnit(target));
  }

  @NotNull public static Measure ofVolume(Double value, Volumes unit, Volumes target) {
    Measure measure = Measure.valueOf(value, unitBridge.volumeUnit(unit));
    //noinspection unchecked
    return measure.to(unitBridge.volumeUnit(target));
  }

  @SuppressWarnings("unchecked")
  public static Measure ofForce(Double value, Forces unit, Forces target) {
    Measure measure = Measure.valueOf(value, unitBridge.forceUnit(unit));
    return measure.to(unitBridge.forceUnit(target));
  }
}
