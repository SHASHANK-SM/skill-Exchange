package com.rural.skill_exchange.ui.needs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.rural.skill_exchange.MainActivity
import com.rural.skill_exchange.databinding.FragmentNeedsBinding
import com.rural.skill_exchange.model.NeedPost

class NeedsFragment : Fragment() {
    private var _binding: FragmentNeedsBinding? = null
    private val binding get() = _binding!!
    private val adapter = NeedAdapter()
    private var allNeeds = emptyList<NeedPost>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentNeedsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val repository = (requireActivity() as MainActivity).repository
        binding.needsRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.needsRecycler.adapter = adapter

        binding.filterInput.doAfterTextChanged { text -> applyFilter(text?.toString().orEmpty()) }
        repository.observeNeeds { needs ->
            allNeeds = needs
            applyFilter(binding.filterInput.text?.toString().orEmpty())
        }
    }

    private fun applyFilter(skill: String) {
        val filtered = if (skill.isBlank()) {
            allNeeds
        } else {
            allNeeds.filter { it.requiredSkill.contains(skill, ignoreCase = true) }
        }
        adapter.submitList(filtered)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
