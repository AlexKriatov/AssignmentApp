package com.kriatov.alex.assignmentapp.ui.recycler

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.TextView
import com.kriatov.alex.assignmentapp.R
import com.kriatov.alex.assignmentapp.ui.model.CheckableItem
import com.kriatov.alex.assignmentapp.utils.Mappers
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Alex Kriatov on 12/21/18
 */
class CheckableItemAdapter  : RecyclerView.Adapter<CheckableItemAdapter.CheckableItemViewHolder>() {

        private var data: ArrayList<CheckableItem>? = null
        private val listeners = LinkedList<OnCheckedItemCountChangedListener>()

        fun addOnCheckedItemCountChangedListener(listener: OnCheckedItemCountChangedListener) {
            listeners.add(listener)
        }

        fun removeOnCheckedItemCountChangedListener(listener: OnCheckedItemCountChangedListener) {
            listeners.remove(listener)
        }

        fun removeAllListeners() {
            listeners.clear()
        }

        fun setData(data: List<CheckableItem>) {
            this.data = ArrayList(data)
            notifyDataSetChanged()
        }
    fun addData(data: List<CheckableItem>) {
        this.data?.addAll(data)
        notifyDataSetChanged()
    }

        private fun deselectAllItems() {
            data?.forEach { it.isChecked = false }
        }

        private fun checkItem(position: Int) {
            data?.get(position)?.let {
               it.isChecked = !it.isChecked
                notifyItemChanged(position)
            }
            listeners.forEach { listener -> listener.onCheckedItemCountChanged(data?.filter { it.isChecked }?.size ?: 0)}
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckableItemViewHolder {
            return CheckableItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_checkable_hit, parent, false))
        }

        override fun getItemCount(): Int {
            return data?.size ?: 0
        }

        override fun onBindViewHolder(holder: CheckableItemViewHolder, position: Int) {
            holder.bindView(data?.get(position))
        }

        interface OnCheckedItemCountChangedListener {
            fun onCheckedItemCountChanged (count: Int)
        }

        inner class CheckableItemViewHolder(var v: View) : RecyclerView.ViewHolder(v) {
            fun bindView(model: CheckableItem?) {
                if (model == null) return
                v.findViewById<TextView>(R.id.tvTitle).text = model.hit.title
                v.findViewById<TextView>(R.id.tvCreatedAt).text = Mappers.formatTime(model.hit.createdAtI?.toLong()?:0)
                v.findViewById<CheckBox>(R.id.checkbox).isChecked = model.isChecked
                v.findViewById<CheckBox>(R.id.checkbox).setOnClickListener {
                    checkItem(adapterPosition)
                }
        }
    }
}