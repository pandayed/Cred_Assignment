package com.edpub.cred_assignment

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewItemAdapter(private val layoutManager: GridLayoutManager, private val rvItems: MutableList<RvItemClass>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    enum class ViewType {
        SMALL,
        DETAILED
    }

    inner class CompactViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        lateinit var ivItem : ImageView
        lateinit var tvTitle : TextView

        constructor(parent: ViewGroup): this(LayoutInflater.from(parent.context).inflate(R.layout.rv_compact_items, parent, false)){
            Log.i("RecyclerViewInit", "Compact")

            ivItem = itemView.findViewById(R.id.ivItem)
            tvTitle = itemView.findViewById(R.id.tvTitle)
        }
    }

    inner class DetailedViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        lateinit var ivItem : ImageView
        lateinit var tvTitle : TextView
        lateinit var tvDescription : TextView
        constructor(parent: ViewGroup): this(LayoutInflater.from(parent.context).inflate(R.layout.rv_descriptive_items, parent, false)){
            Log.i("RecyclerViewInit", "Detailed")
            ivItem = itemView.findViewById(R.id.ivItem)
            tvTitle = itemView.findViewById(R.id.tvTitle)
            tvDescription = itemView.findViewById(R.id.tvDescription)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.i("RecyclerViewInit", viewType.toString())
        return when (viewType) {
            ViewType.DETAILED.ordinal -> DetailedViewHolder(parent)
            else -> CompactViewHolder(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.apply {
            when (this) {
                is CompactViewHolder -> {
                    ivItem.setImageResource(rvItems[position].image)
                    tvTitle.text = rvItems[position].title
                }
                is DetailedViewHolder -> {
                    ivItem.setImageResource(rvItems[position].image)
                    tvTitle.text = rvItems[position].title
                    tvDescription.text = rvItems[position].description
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (layoutManager.spanCount == 1) ViewType.DETAILED.ordinal
        else ViewType.SMALL.ordinal
    }

    override fun getItemCount() = rvItems.size
}