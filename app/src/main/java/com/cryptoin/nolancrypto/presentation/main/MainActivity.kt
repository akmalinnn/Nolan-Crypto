package com.cryptoin.nolancrypto.presentation.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.cryptoin.nolancrypto.R
import com.cryptoin.nolancrypto.databinding.ActivityMainBinding
import com.cryptoin.nolancrypto.presentation.login.LoginActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupBottomNav()
    }

    private fun setupBottomNav() {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        binding.navView.setupWithNavController(navController)
        setupMenuClickListener(binding.navView)
    }

    private fun setupMenuClickListener(bottomNavigationView: BottomNavigationView) {
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_tab_profile -> {
                    if (!viewModel.isLoggedIn()) {
                        navigateToLogin()
                        return@setOnNavigationItemSelectedListener false
                    }
                }
                R.id.market_icon -> {
                    val marketUnavailableMessage = getString(R.string.market_unavailable_message)
                    Toast.makeText(this, marketUnavailableMessage, Toast.LENGTH_SHORT).show()
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }
    private fun navigateToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
    }
}
