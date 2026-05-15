package com.rural.skill_exchange

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.rural.skill_exchange.data.FirebaseRepository
import com.rural.skill_exchange.databinding.ActivityMainBinding
import com.rural.skill_exchange.ui.home.HomeFragment
import com.rural.skill_exchange.ui.needs.NeedsFragment
import com.rural.skill_exchange.ui.offers.OffersFragment
import com.rural.skill_exchange.ui.profile.ProfileFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val repository = FirebaseRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        repository.ensureSignedIn()
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> showFragment(HomeFragment())
                R.id.nav_needs -> showFragment(NeedsFragment())
                R.id.nav_offers -> showFragment(OffersFragment())
                R.id.nav_profile -> showFragment(ProfileFragment())
            }
            true
        }

        if (savedInstanceState == null) {
            binding.bottomNavigation.selectedItemId = R.id.nav_home
        }
    }

    fun showFragment(fragment: Fragment, addToBackStack: Boolean = false) {
        val transaction = supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
        if (addToBackStack) transaction.addToBackStack(fragment::class.java.simpleName)
        transaction.commit()
    }
}
