package com.daniel.a160421076_uts.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup

import androidx.viewpager2.widget.ViewPager2

import com.daniel.a160421076_uts.databinding.FragmentDetailBinding
import com.squareup.picasso.Picasso


class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var adapter: DetailAdapter
    private var currentPage: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val gambarBerita = arguments?.getString("GAMBAR_BERITA")
        val judulBerita = arguments?.getString("JUDUL_BERITA")
        val username = arguments?.getString("USERNAME")
        val kontenBerita = arguments?.getString("KONTEN_BERITA")

        binding.apply {
            Picasso.get().load(gambarBerita).into(binding.imageView)
            Picasso.get().setLoggingEnabled(true)

            txtJudul.text = judulBerita
            txtUsername.text = "@$username"

            adapter = DetailAdapter(kontenBerita?: "")
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
}