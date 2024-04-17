package com.daniel.a160421076_uts.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.daniel.a160421076_uts.R
import com.daniel.a160421076_uts.databinding.ActivityLoginBinding
import com.daniel.a160421076_uts.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.btnLogin.setOnClickListener {
            val username = binding.txtUsernameLogin.editText?.text.toString()
            val password = binding.txtPasswordLogin.editText?.text.toString()

            loginViewModel.login(username, password)
        }

        loginViewModel.loginResult.observe(this, {result->
            if(result){
                Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }else{
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
            }
        })

        binding.btnReg.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}