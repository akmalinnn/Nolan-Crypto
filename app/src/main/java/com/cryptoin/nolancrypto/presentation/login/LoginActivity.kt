package com.cryptoin.nolancrypto.presentation.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.cryptoin.nolancrypto.databinding.ActivityLoginBinding
import com.cryptoin.nolancrypto.presentation.main.MainActivity
import com.cryptoin.nolancrypto.presentation.register.RegisterActivity
import com.cryptoin.nolancrypto.utils.highLightWord
import com.cryptoin.nolancrypto.utils.proceedWhen
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {
    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setClickListener()
        setupForm()
    }

    private fun setClickListener() {
        binding.btnLogin.setOnClickListener {
            inputLogin()
        }
        binding.tvNavToRegister.highLightWord("Register Here") {
            navigateRegister()
        }
    }

    private fun inputLogin() {
        val email = binding.layoutForm.etEmail.text.toString().trim()
        val password = binding.layoutForm.etPassword.text.toString().trim()
        doLogin(email, password)
    }

    private fun doLogin(
        email: String,
        password: String,
    ) {
        viewModel.doLogin(email, password).observe(this) { result ->
            result.proceedWhen(
                doOnSuccess = {
                    binding.pbLoading.isVisible = false
                    binding.pbLoading.isEnabled = true
                    Toast.makeText(
                        this,
                        "Login Success",
                        Toast.LENGTH_SHORT,
                    ).show()
                    navigateToMain()
                },
                doOnLoading = {
                    binding.pbLoading.isVisible = true
                    binding.pbLoading.isEnabled = false
                },
                doOnError = {
                    binding.pbLoading.isVisible = false
                    binding.pbLoading.isEnabled = true
                    Toast.makeText(
                        this,
                        "Login Failed",
                        Toast.LENGTH_SHORT,
                    ).show()
                },
            )
        }
    }

    private fun setupForm() {
        with(binding.layoutForm) {
            tilPassword.isVisible = true
            tilEmail.isVisible = true
        }
    }

    private fun navigateToMain() {
        startActivity(
            Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            },
        )
    }

    private fun navigateRegister() {
        startActivity(
            Intent(this, RegisterActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
            },
        )
    }
}
