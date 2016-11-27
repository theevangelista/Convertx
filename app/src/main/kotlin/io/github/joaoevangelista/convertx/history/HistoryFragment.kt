package io.github.joaoevangelista.convertx.history

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.joaoevangelista.convertx.R
import io.github.joaoevangelista.convertx.R.layout
import io.github.joaoevangelista.convertx.support.bindView
import io.realm.Realm
import io.realm.RealmChangeListener
import io.realm.RealmResults

/**
 * @author Joao Pedro Evangelista
 */
class HistoryFragment : Fragment() {

  val recyclerView: RecyclerView by bindView(R.id.history_view)

  val realm: Realm? = Realm.getDefaultInstance()

  var adapter: EntriesAdapter? = null

  var results: RealmResults<Entry>? = null

  var listener: RealmChangeListener<RealmResults<Entry>>? = null

  override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
    savedInstanceState: Bundle?): View? {
    return inflater?.inflate(layout.fragment_history, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    results = realm?.where(Entry::class.java)?.findAllSorted("timestamp")
    setAdapter(results)
  }

  private fun setAdapter(list: List<Entry>?) {
    val entries = list?.toMutableList() ?: emptyList<Entry>().toMutableList()
    adapter = EntriesAdapter(activity, entries)
    recyclerView.adapter = this.adapter
    recyclerView.layoutManager = LinearLayoutManager(activity)
  }

  override fun onResume() {
    super.onResume()
    listener = RealmChangeListener<RealmResults<Entry>> { adapter?.addAll(it) }
    results?.addChangeListener(listener!!)
  }

  override fun onPause() {
    super.onPause()
    results?.removeChangeListener(listener)
  }

  companion object {
    fun newInstance(): HistoryFragment {
      return HistoryFragment()
    }
  }
}
