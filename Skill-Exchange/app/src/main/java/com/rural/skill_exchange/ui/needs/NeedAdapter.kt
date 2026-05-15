package com.rural.skill_exchange.ui.needs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rural.skill_exchange.databinding.ItemNeedBinding
import com.rural.skill_exchange.model.NeedPost

class NeedAdapter : RecyclerView.Adapter<NeedAdapter.NeedViewHolder>() {
    private val items = mutableListOf<NeedPost>()

    fun submitList(needs: List<NeedPost>) {
        items.clear()
        items.addAll(needs)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NeedViewHolder {
        val binding = ItemNeedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NeedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NeedViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class NeedViewHolder(private val binding: ItemNeedBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NeedPost) = with(binding) {
            titleText.text = item.title
            metaText.text = "${item.requiredSkill} - ${item.location} - Posted by ${item.postedBy}"
            descriptionText.text = item.description
            pointsText.text = "${item.hours} hour = ${item.skillPoints} skill points"
        }
    }
}
