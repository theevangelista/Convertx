package io.github.joaoevangelista.convertx

import android.app.Application
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.answers.Answers
import io.fabric.sdk.android.Fabric

/**
 * @author Joao Pedro Evangelista
 */
class ConvertxApplication : Application() {

  override fun onCreate() {
    super.onCreate()
    enableReleaseOnly()
  }

  private fun enableReleaseOnly() {
    if (!BuildConfig.DEBUG) {
      Fabric.with(this, Crashlytics(), Answers())
    }
  }
}
