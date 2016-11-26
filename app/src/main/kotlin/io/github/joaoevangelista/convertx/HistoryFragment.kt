package io.github.joaoevangelista.convertx

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.joaoevangelista.convertx.support.bindView

/**
 * @author Joao Pedro Evangelista
 */
class HistoryFragment : Fragment() {

  val recyclerView: RecyclerView by bindView(R.id.history_view)

  override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
    savedInstanceState: Bundle?): View? {
    return inflater?.inflate(R.layout.fragment_history, container, false)
  }

  companion object {
    fun newInstance(): HistoryFragment {
      return HistoryFragment()
    }
  }
}
