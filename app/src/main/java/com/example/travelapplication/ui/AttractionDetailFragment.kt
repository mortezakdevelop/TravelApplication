package com.example.travelapplication.ui

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.travelapplication.R
import com.example.travelapplication.databinding.FragmentAttractionDetailBinding
import com.example.travelapplication.epoxy.ContentEpoxyController
import com.example.travelapplication.model.Attraction
import java.lang.StringBuilder


class AttractionDetailFragment : BaseFragment() {

    private var _binding: FragmentAttractionDetailBinding? = null
    private val fragmentAttractionDetailBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //set menu in detail fragment
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAttractionDetailBinding.inflate(inflater, container, false)
        activityViewModel.selectedAttractionLiveData.observe(viewLifecycleOwner) { attraction ->

            fragmentAttractionDetailBinding.textViewTitle.text = attraction.title

            //epoxy adapter for under image
            fragmentAttractionDetailBinding.contentEpoxyRecyclerView.setControllerAndBuildModels(
                ContentEpoxyController(attraction)
            )
            Glide.with(fragmentAttractionDetailBinding.root.context)
                .load(attraction.image_url)
                .into(fragmentAttractionDetailBinding.imageView)


            //            fragmentAttractionDetailBinding.textViewDescription.text = attraction.description
//            fragmentAttractionDetailBinding.tvAllYear.text = attraction.months_to_visit
//            fragmentAttractionDetailBinding.tvFact.text = "${attraction.facts.size} facts"
//
//            fragmentAttractionDetailBinding.tvFact.setOnClickListener {
//                val stringBuilder = StringBuilder("")
//                attraction.facts.forEach {
//                    stringBuilder.append("\u2022 $it")
//                    stringBuilder.append("\n\n")
//                }
//
//                //logic for get facts
//                val message =
//                    stringBuilder.toString().substring(0, stringBuilder.toString().lastIndexOf("\n\n"))
//                //alert dialog
//                AlertDialog.Builder(requireContext()).setTitle("${attraction.title} facts")
//                    .setMessage(message).setPositiveButton("Yes"){ dialog, which ->
//                        dialog.dismiss()
//                    }
//                    .setNegativeButton("No"){dialog,which ->
//                        dialog.dismiss()
//                    }
//                    .setCancelable(false)
//                    .show()
//            }
        }
        return fragmentAttractionDetailBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_fragment_detail, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.menuItemLocation -> {
                val attraction = activityViewModel.selectedAttractionLiveData.value ?: return true
                activityViewModel.locationSelectedLiveData.postValue(attraction)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}