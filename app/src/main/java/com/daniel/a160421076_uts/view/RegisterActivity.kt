package com.daniel.a160421076_uts.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.daniel.a160421076_uts.R
import com.daniel.a160421076_uts.databinding.ActivityRegisterBinding
import com.daniel.a160421076_uts.viewmodel.RegisterViewModel

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registerViewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        binding.btnRegist.setOnClickListener {
            val username = binding.txtUsernameRegist.editText?.text.toString()
            val namaDepan = binding.txtNamaDepanRegist.editText?.text.toString()
            val namaBelakang = binding.txtNamaBelakangRegist.editText?.text.toString()
            val email = binding.txtEmailRegist.editText?.text.toString()
            val password = binding.txtPasswordRegist.editText?.text.toString()
            val confPassword = binding.txtConfPasswordRegist.editText?.text.toString()

            registerViewModel.register(username, namaDepan, namaBelakang, email, password, confPassword)
        }

        registerViewModel.registerResult.observe(this, {result->
            if (result) {
                Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show()
            }
        })

        binding.btnLoginReg.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}