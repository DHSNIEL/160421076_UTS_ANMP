package com.daniel.a160421076_uts.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.daniel.a160421076_uts.R
import com.daniel.a160421076_uts.databinding.FragmentDetailBinding
import com.squareup.picasso.Picasso


class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding

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
            val contentAdapter = DetailFragmentAdapter(context, kontenBerita)
            viewPager.adapter = contentAdapter
        }

        return root
    }

}