package com.daniel.a160421076_uts.view


import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.daniel.a160421076_uts.databinding.FragmentProfileBinding
import com.daniel.a160421076_uts.viewmodel.ProfileViewModel


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = requireActivity().getSharedPreferences("myPref", MODE_PRIVATE)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        fetchUserData()

        binding.confirmEdit.setOnClickListener {
            val username = sharedPreferences.getString("username", "")
            val namaDepan = binding.editNamaDepan.editText?.text.toString()
            val namaBelakang = binding.editNamaBelakang.editText?.text.toString()
            val password = binding.editPassword.editText?.text.toString()

            viewModel.update(username ?: "", namaDepan, namaBelakang, password)
            viewModel.updateResult.observe(viewLifecycleOwner, {result ->
                if(result){
                    Toast.makeText(requireContext(), "Data berhasil diperbaharui", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(requireContext(), "Nama depan perlu diisi", Toast.LENGTH_SHORT).show()
                }
            })


        }

        binding.logOutBtn.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()

            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }

    private fun fetchUserData(){
        val username = sharedPreferences.getString("username", "")
        viewModel.getUserData(username ?:"")

        viewModel.userData.observe(viewLifecycleOwner, {user->
            binding.editNamaDepan.editText?.setText(user?.namaDepan)
            binding.editNamaBelakang.editText?.setText(user?.namaBelakang)
        })

    }
}