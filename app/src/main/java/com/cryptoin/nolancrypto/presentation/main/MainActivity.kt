package com.cryptoin.nolancrypto.presentation.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.cryptoin.nolancrypto.R
import com.cryptoin.nolancrypto.databinding.ActivityMainBinding
import com.cryptoin.nolancrypto.presentation.intro.MyAppIntroActivity
import com.cryptoin.nolancrypto.presentation.login.LoginActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        checkFirstRun()
        setupBottomNav()
    }

    private fun setupBottomNav() {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        binding.navView.setupWithNavController(navController)
        binding.navView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_tab_profile -> {
                    if (!viewModel.isLoggedIn()) {
                        navigateToLogin()
                        navController.popBackStack(R.id.menu_tab_home, false)
                        return@setOnItemSelectedListener false
                    }
                }
                R.id.market_icon -> {
                    Toast.makeText(this, "Under Maintenance", Toast.LENGTH_SHORT).show()
                    return@setOnItemSelectedListener false
                }
            }
            navController.navigate(item.itemId)
            true
        }
    }

    private fun navigateToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
    }

    private fun checkFirstRun() {
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val isFirstRun = sharedPreferences.getBoolean("isFirstRun", true)

        // delete negasi, Intro just showing at the first time run
        if (isFirstRun) {
            startActivity(Intent(this, MyAppIntroActivity::class.java))
            // Set isFirstRun to false to indicate that the app has been launched before
            sharedPreferences.edit().putBoolean("isFirstRun", false).apply()
        }
    }
}
