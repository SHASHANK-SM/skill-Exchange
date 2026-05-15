package com.rural.skill_exchange.ui.offers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rural.skill_exchange.databinding.ItemOfferBinding
import com.rural.skill_exchange.model.SwapOffer

class OfferAdapter(
    private val onChat: (SwapOffer) -> Unit,
    private val onConfirm: (SwapOffer) -> Unit
) : RecyclerView.Adapter<OfferAdapter.OfferViewHolder>() {
    private val items = mutableListOf<SwapOffer>()

    fun submitList(offers: List<SwapOffer>) {
        items.clear()
        items.addAll(offers)
        notifyDataSetChanged()
    }

    fun replaceOffer(updated: SwapOffer) {
        val index = items.indexOfFirst { it.id == updated.id }
        if (index >= 0) {
            items[index] = updated
            notifyItemChanged(index)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        val binding = ItemOfferBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OfferViewHolder(binding, onChat, onConfirm)
    }

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class OfferViewHolder(
        private val binding: ItemOfferBinding,
        private val onChat: (SwapOffer) -> Unit,
        private val onConfirm: (SwapOffer) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SwapOffer) = with(binding) {
            titleText.text = item.needTitle
            statusText.text = item.status
            detailText.text = "${item.fromUser} offers ${item.offeredSkill} for ${item.hours} hour(s) of ${item.requestedSkill}. Value: ${item.skillPoints} skill points."
            chatButton.setOnClickListener { onChat(item) }
            confirmButton.isEnabled = item.status != "Completed"
            confirmButton.text = if (item.status == "Completed") "Confirmed" else "Confirm"
            confirmButton.setOnClickListener { onConfirm(item) }
        }
    }
}
