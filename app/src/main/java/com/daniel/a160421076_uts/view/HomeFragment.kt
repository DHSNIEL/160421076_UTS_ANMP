package com.daniel.a160421076_uts.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.daniel.a160421076_uts.databinding.FragmentHomeBinding
import com.daniel.a160421076_uts.model.Berita
import com.daniel.a160421076_uts.viewmodel.HomeViewModel
import com.squareup.picasso.Picasso

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Initialize the HomeViewModel
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        // Initialize the NewsAdapter
        newsAdapter = NewsAdapter(::onItemClick)


        // Setup RecyclerView
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = newsAdapter
        }

        // Observe the list of news from HomeViewModel
        homeViewModel.getBeritaLiveData().observe(viewLifecycleOwner) { newsList ->
            // Update the NewsAdapter with the new list of news
            newsAdapter.updateNewsList(newsList)
        }

        // Refresh news data
        homeViewModel.refresh()
        return root
    }

    private fun onItemClick(berita: Berita){

//        val action = HomeFragmentDirections.actionHometoDetail(berita.judul)
//        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}