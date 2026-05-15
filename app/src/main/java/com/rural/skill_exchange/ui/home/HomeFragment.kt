package com.rural.skill_exchange.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.rural.skill_exchange.MainActivity
import com.rural.skill_exchange.databinding.FragmentHomeBinding
import com.rural.skill_exchange.ui.needs.NeedAdapter

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val needAdapter = NeedAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val repository = (requireActivity() as MainActivity).repository
        binding.nearbyNeedsRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.nearbyNeedsRecycler.adapter = needAdapter

        repository.observeProfile { profile ->
            binding.greetingText.text = "Namaste, ${profile.name.substringBefore(' ')}"
            binding.pointsText.text = profile.skillPoints.toString()
            binding.trustText.text = "${profile.trustScore}%"
        }
        repository.observeNeeds { needs -> needAdapter.submitList(needs.take(3)) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
