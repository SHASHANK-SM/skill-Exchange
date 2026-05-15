package com.rural.skill_exchange.ui.offers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.rural.skill_exchange.MainActivity
import com.rural.skill_exchange.databinding.FragmentOffersBinding
import com.rural.skill_exchange.ui.chat.NegotiationFragment

class OffersFragment : Fragment() {
    private var _binding: FragmentOffersBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: OfferAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentOffersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val activity = requireActivity() as MainActivity
        adapter = OfferAdapter(
            onChat = { offer -> activity.showFragment(NegotiationFragment.newInstance(offer.id, offer.needTitle), true) },
            onConfirm = { offer ->
                activity.repository.confirmSwap(offer) { updated, trustScore ->
                    adapter.replaceOffer(updated)
                    Toast.makeText(requireContext(), "Swap confirmed. Trust score is now $trustScore%.", Toast.LENGTH_LONG).show()
                }
            }
        )
        binding.offersRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.offersRecycler.adapter = adapter
        activity.repository.observeOffers { offers -> adapter.submitList(offers) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
