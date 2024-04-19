package com.daniel.a160421076_uts.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup

import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController

import androidx.viewpager2.widget.ViewPager2
import com.daniel.a160421076_uts.R
import com.daniel.a160421076_uts.databinding.FragmentDetailBinding
import com.squareup.picasso.Picasso


class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var adapter: DetailFragmentAdapter
    private var currentPage: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Retrieve data from arguments
        val gambarBerita = arguments?.getString("GAMBAR_BERITA")
        val judulBerita = arguments?.getString("JUDUL_BERITA")
        val username = arguments?.getString("USERNAME")
        val deskripsiBerita = arguments?.getString("DESKRIPSI_BERITA")
        val kontenBerita = arguments?.getString("KONTEN_BERITA")

        // Set data to views
        binding.apply {
            // Set image
            Picasso.get().load(gambarBerita).into(binding.imageView)
            Picasso.get().setLoggingEnabled(true)

            // Set text
            txtJudul.text = judulBerita
            txtUsername.text = username
            txtDeskripsi.text = deskripsiBerita

            // Initialize ViewPager and set adapter
//            val contentAdapter = DetailFragmentAdapter(context, kontenBerita)
            adapter = DetailFragmentAdapter(kontenBerita?: "")
            viewPager.adapter = adapter
        }


        binding.btnNext.setOnClickListener {
            if (currentPage < adapter.itemCount - 1) {
                currentPage++
                binding.viewPager.setCurrentItem(currentPage, true)
            }
        }

        binding.btnPrev.setOnClickListener {
            if (currentPage > 0) {
                currentPage--
                binding.viewPager.setCurrentItem(currentPage, true)
            }
        }

        binding.btnPrev.isEnabled = false

        binding.viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                currentPage = position
                binding.btnPrev.isEnabled = position != 0
                binding.btnNext.isEnabled = position != adapter.itemCount -1
            }
        })

        return root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        val navController = findNavController()
//        val appBarConfiguration = AppBarConfiguration(navController.graph)
//        binding.toolbar
//            .setupWithNavController(navController, appBarConfiguration)
//    }
}