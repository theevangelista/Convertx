package io.github.joaoevangelista.convertx.support

import com.crashlytics.android.answers.AddToCartEvent
import com.crashlytics.android.answers.Answers
import com.crashlytics.android.answers.ContentViewEvent
import com.crashlytics.android.answers.CustomEvent
import com.crashlytics.android.answers.InviteEvent
import com.crashlytics.android.answers.LevelEndEvent
import com.crashlytics.android.answers.LevelStartEvent
import com.crashlytics.android.answers.LoginEvent
import com.crashlytics.android.answers.PredefinedEvent
import com.crashlytics.android.answers.PurchaseEvent
import com.crashlytics.android.answers.RatingEvent
import com.crashlytics.android.answers.SearchEvent
import com.crashlytics.android.answers.ShareEvent
import com.crashlytics.android.answers.SignUpEvent
import com.crashlytics.android.answers.StartCheckoutEvent

/**
 * Wrapper around Answers to avoid pushing metrics on debug builds
 * @author Joao Pedro Evangelista
 */
object Analytics {

  val a: Answers? = Answers.getInstance()

  fun log(event: PredefinedEvent<*>) {
    if (!io.github.joaoevangelista.convertx.BuildConfig.DEBUG) {
      logInternal(event)
    }
  }

  private fun logInternal(event: PredefinedEvent<*>) {
    when (event) {
      is CustomEvent -> a?.logCustom(event)
      is LoginEvent -> a?.logLogin(event)
      is AddToCartEvent -> a?.logAddToCart(event)
      is ContentViewEvent -> a?.logContentView(event)
      is InviteEvent -> a?.logInvite(event)
      is LevelEndEvent -> a?.logLevelEnd(event)
      is LevelStartEvent -> a?.logLevelStart(event)
      is PurchaseEvent -> a?.logPurchase(event)
      is RatingEvent -> a?.logRating(event)
      is SearchEvent -> a?.logSearch(event)
      is ShareEvent -> a?.logShare(event)
      is SignUpEvent -> a?.logSignUp(event)
      is StartCheckoutEvent -> a?.logStartCheckout(event)
    }
  }

}
