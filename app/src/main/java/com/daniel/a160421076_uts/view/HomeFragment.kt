package com.daniel.a160421076_uts.view

import android.content.Intent
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
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        newsAdapter = NewsAdapter{onItemClick(it)}

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = newsAdapter
        }

        homeViewModel.getBeritaLiveData().observe(viewLifecycleOwner) { newsList ->
            newsAdapter.updateNewsList(newsList)
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
//region old onItemClick
//    private fun onItemClick(berita: Berita){
//        val intent = Intent(requireContext(), DetailFragment::class.java).apply {
//            putExtra("GAMBAR_BERITA", berita.gambar)
//            putExtra("JUDUL_BERITA", berita.judul  )
//            putExtra("USERNAME ",berita.username)
//            putExtra("DESKRIPSI_BERITA ",berita.deskripsi)
//            putExtra("KONTEN_BERITA", berita.konten)
////            putStringArrayListExtra("KONTEN_BERITA", ArrayList<String>(berita.konten))
//        }
////        startActivity(intent)
//        findNavController().navigate(R.id.action_navigation_home_to_detailFragment, bund)
//    }
//    endregion

    override fun onDestroyView() {
        super.onDestroyView()
    }
}