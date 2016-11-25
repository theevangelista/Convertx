package io.github.joaoevangelista.convertx.activity;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.ImageView;
import io.github.joaoevangelista.convertx.R;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.PositionAssertions.isRightOf;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static io.github.joaoevangelista.convertx.R.id;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * @author Joao Pedro Evangelista
 */

@RunWith(AndroidJUnit4.class) public class MasterActivityInstrumentedTest {

  private static final String TEMPERATURE = "Temperature";

  @Rule public ActivityTestRule<MasterActivity> activityTestRule =
    new ActivityTestRule<>(MasterActivity.class);

  @Test public void shouldLoadInitialStateOfView() throws Exception {
    onView(withId(id.conversion_spinner_selector)).check(isRightOf(withId(id.toolbar_title)));
    onView(withId(id.spinner_to_unit)).check(matches(withSpinnerText(R.string.unit_length_meters)));
    onView(withId(id.spinner_from_unit)).check(
      matches(withSpinnerText(R.string.unit_length_meters)));
    onView(withId(id.conversion_indicator)).check(matches(isAssignableFrom(ImageView.class)));
    onView(withId(id.result_card_view)).check(matches(not(isDisplayed())));
  }

  @Test public void shouldChangeUnitSpinnersOnTypeChange() throws Exception {
    // click on spinner
    onView(withId(id.conversion_spinner_selector)).perform(click());
    // select a option from the list
    onData(allOf(is(instanceOf(String.class)), is(TEMPERATURE))).perform(click());
    // both are set to the same
    onView(withId(id.spinner_from_unit)).check(
      matches(withSpinnerText(R.string.unit_temperature_celsius)));
    onView(withId(id.spinner_to_unit)).check(
      matches(withSpinnerText(R.string.unit_temperature_celsius)));
  }

  @Test public void shouldShowResultViewWhenWeTypeOnInput() throws Exception {
    // assert first we do not see the result card
    onView(withId(id.result_card_view)).check(matches(not(isDisplayed())));
    // type a number into input
    onView(withId(id.data_input)).perform(typeText("20"));
    // assert the result is shown
    Thread.sleep(500); // sleep until the animation finish
    onView(withId(id.result_card_view)).check(matches(isCompletelyDisplayed()));
    // assert has result in the text view
    onView(withId(id.result_text_view)).check(matches(
      withText(is(not(equalTo("20")))))); // is 20 because we start the conversions using same units
  }
}
