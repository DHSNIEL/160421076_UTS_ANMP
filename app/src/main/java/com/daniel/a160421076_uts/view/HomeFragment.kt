package com.daniel.a160421076_uts.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.daniel.a160421076_uts.R
import com.daniel.a160421076_uts.databinding.FragmentHomeBinding
import com.daniel.a160421076_uts.model.Berita
import com.daniel.a160421076_uts.viewmodel.HomeViewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var homeAdapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        homeAdapter = HomeAdapter{onItemClick(it)}

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = homeAdapter
        }

        homeViewModel.getBeritaLiveData().observe(viewLifecycleOwner) { newsList ->
            homeAdapter.updateNewsList(newsList)
        }

        homeViewModel.refresh()
        return root
    }

    private fun onItemClick(berita: Berita) {
        val bundle = Bundle().apply {
            putString("GAMBAR_BERITA", berita.gambar)
            putString("JUDUL_BERITA", berita.judul)
            putString("USERNAME", berita.username)
            putString("DESKRIPSI_BERITA", berita.deskripsi)
            putString("KONTEN_BERITA", berita.konten)
        }
        findNavController().navigate(R.id.action_navigation_home_to_detailFragment, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}