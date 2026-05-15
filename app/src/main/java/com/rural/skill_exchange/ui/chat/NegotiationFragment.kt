package com.rural.skill_exchange.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.rural.skill_exchange.MainActivity
import com.rural.skill_exchange.R
import com.rural.skill_exchange.databinding.FragmentNegotiationBinding

class NegotiationFragment : Fragment() {
    private var _binding: FragmentNegotiationBinding? = null
    private val binding get() = _binding!!
    private val adapter = MessageAdapter()
    private val offerId: String by lazy { requireArguments().getString(ARG_OFFER_ID).orEmpty() }
    private val title: String by lazy { requireArguments().getString(ARG_TITLE).orEmpty() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentNegotiationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val repository = (requireActivity() as MainActivity).repository
        binding.toolbar.title = title.ifBlank { "Negotiation" }
        binding.toolbar.navigationIcon = AppCompatResources.getDrawable(requireContext(), androidx.appcompat.R.drawable.abc_ic_ab_back_material)
        binding.toolbar.setNavigationOnClickListener { parentFragmentManager.popBackStack() }

        binding.messagesRecycler.layoutManager = LinearLayoutManager(requireContext()).apply {
            stackFromEnd = true
        }
        binding.messagesRecycler.adapter = adapter
        repository.observeMessages(offerId.ifBlank { "offer_1" }) { messages ->
            adapter.submitList(messages)
            binding.messagesRecycler.scrollToPosition((messages.size - 1).coerceAtLeast(0))
        }

        binding.sendButton.setOnClickListener {
            val body = binding.messageInput.text?.toString().orEmpty().trim()
            if (body.isNotEmpty()) {
                repository.sendMessage(offerId.ifBlank { "offer_1" }, body)
                binding.messageInput.setText("")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_OFFER_ID = "offer_id"
        private const val ARG_TITLE = "title"

        fun newInstance(offerId: String, title: String) = NegotiationFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_OFFER_ID, offerId)
                putString(ARG_TITLE, title)
            }
        }
    }
}
