package com.example.travelapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.example.travelapplication.R
import com.example.travelapplication.databinding.FragmentAttractionDetailBinding


class AttractionDetailFragment : Fragment() {

    lateinit var fragmentAttractionDetailBinding: FragmentAttractionDetailBinding

    //هر پارامتری که به navigation خود در argument ارسال کرده بودیم را در این جا تزریق می شود
    private val safeArgs :AttractionDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentAttractionDetailBinding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_attraction_detail,container,false)
        return fragmentAttractionDetailBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //دیتایی که به adapter داریم مثلا id یا title را در این جا هنگام نویگیت کردن دریافت می کنیم
        fragmentAttractionDetailBinding.tvDetail.text = safeArgs.attractionId
    }

}