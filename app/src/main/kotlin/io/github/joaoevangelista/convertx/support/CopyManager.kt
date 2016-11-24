package io.github.joaoevangelista.convertx.support

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import io.github.joaoevangelista.convertx.R.string
import java.text.DecimalFormat

/**
 * @author Joao Pedro Evangelista
 */
class CopyManager(private val context: Context) {

  fun copyToClipboard(text: String, afterCopied: () -> Unit) {
    val manager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val label = context.getString(string.copy_result_label)
    manager.primaryClip = ClipData.newPlainText(label, text)
    afterCopied()
  }

  fun copyFormattedToClipboard(text: String, afterCopied: () -> Unit) {
    val manager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val label = context.getString(string.copy_result_label)
    val formatter = DecimalFormat("##.###")
    val fmtText = formatter.format(text.toDouble())
    manager.primaryClip = ClipData.newPlainText(label, fmtText)
    afterCopied()
  }
}
