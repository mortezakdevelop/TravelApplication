package com.example.travelapplication.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.travelapplication.R
import com.example.travelapplication.databinding.FragmentAttractionDetailBinding
import com.example.travelapplication.model.Attraction


class AttractionDetailFragment : BaseFragment() {

    private var _binding: FragmentAttractionDetailBinding? = null
    private val fragmentAttractionDetailBinding get() = _binding!!

    //هر پارامتری که به navigation خود در argument ارسال کرده بودیم را در این جا تزریق می شود
    private val safeArgs by navArgs<AttractionDetailFragmentArgs>()

    //access to id for find image(safe args)
    //با این روش ما view های خود را settext کردیم
    private val attraction: Attraction by lazy {
        attractions.find { it.id == safeArgs.attractionId }!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_attraction_detail,
            container,
            false
        )

            fragmentAttractionDetailBinding.textViewTitle.text = attraction.title
            fragmentAttractionDetailBinding.textViewDescription.text = attraction.description
            Glide.with(fragmentAttractionDetailBinding.root.context)
                .load(attraction.image_url)
                .into(fragmentAttractionDetailBinding.imageView)
            fragmentAttractionDetailBinding.tvAllYear.text = attraction.months_to_visit
        fragmentAttractionDetailBinding.tvFact.text = "${attraction.facts.size} facts"

        fragmentAttractionDetailBinding.tvFact.setOnClickListener {
        }
        return fragmentAttractionDetailBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}