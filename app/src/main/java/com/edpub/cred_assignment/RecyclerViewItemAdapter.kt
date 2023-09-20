package com.edpub.cred_assignment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewItemAdapter(private val layoutManager: GridLayoutManager): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    enum class ViewType {
        SMALL,
        DETAILED
    }

    inner class SimpleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        constructor(parent: ViewGroup): this(LayoutInflater.from(parent.context).inflate(R.layout.rv_descriptive_items, parent, false))
    }

    inner class DetailedViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        constructor(parent: ViewGroup): this(LayoutInflater.from(parent.context).inflate(R.layout.rv_descriptive_items, parent, false))
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ViewType.DETAILED.ordinal -> DetailedViewHolder(parent)
            else -> SimpleViewHolder(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.apply {
            
        }
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun getItemCount() = 5
}