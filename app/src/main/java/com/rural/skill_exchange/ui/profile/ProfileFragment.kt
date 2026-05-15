package com.rural.skill_exchange.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rural.skill_exchange.MainActivity
import com.rural.skill_exchange.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val repository = (requireActivity() as MainActivity).repository
        repository.observeProfile { profile ->
            binding.nameText.text = profile.name
            binding.locationText.text = profile.village
            binding.skillsText.text = profile.skills.joinToString(" - ")
            binding.walletText.text = "${profile.skillPoints} skill points available"
            binding.trustDetailsText.text = "Trust score: ${profile.trustScore}%\nCompleted swaps: ${profile.completedSwaps}\nScore improves after both people confirm a completed swap."
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
