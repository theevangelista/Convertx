package io.github.joaoevangelista.convertx.history

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.github.joaoevangelista.convertx.R
import io.github.joaoevangelista.convertx.op.findUnit
import java.text.DecimalFormat

/**
 * @author Joao Pedro Evangelista
 */
class EntriesAdapter(private val context: Context, private var list: MutableList<Entry>) :
  RecyclerView.Adapter<EntriesAdapter.ViewHolder>() {

  init {
    list.reverse()
  }

  var onClickListener: ((View, Entry) -> Unit)? = null

  val formatter = DecimalFormat("##.###")

  override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(context).inflate(R.layout.fragment_history_item, parent, false)
    return ViewHolder(view)
  }

  override fun getItemCount(): Int = list.size

  override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
    val item = list[position]
    holder?.itemView?.setOnClickListener { onClickListener?.invoke(it, item) }
    holder?.fromUnitView?.text = item.fromUnit?.let { context.getString(findUnit(it).t()) }
    holder?.toUnitView?.text = item.toUnit?.let { context.getString(findUnit(it).t()) }
    holder?.fromValueView?.text = item.input?.let { formatter.format(it) }
    holder?.toValueView?.text = item.output?.let { formatter.format(it) }
  }

  fun addAll(list: MutableList<Entry>) {
    this.list = list.asReversed()
    notifyItemRangeChanged(0, list.size)
  }

  fun remove(entry: Entry) {
    val index = list.indexOf(entry)
    list.removeAt(index)
    notifyItemRemoved(index)

  }

  class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val fromUnitView: TextView
    val toUnitView: TextView
    val toValueView: TextView
    val fromValueView: TextView

    init {
      fromUnitView = view.findViewById(R.id.from_unit) as TextView
      toUnitView = view.findViewById(R.id.to_unit) as TextView
      toValueView = view.findViewById(R.id.to_value) as TextView
      fromValueView = view.findViewById(R.id.from_value) as TextView
    }
  }
}
