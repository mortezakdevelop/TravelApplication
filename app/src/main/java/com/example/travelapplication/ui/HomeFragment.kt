package com.example.travelapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.travelapplication.R
import com.example.travelapplication.adapter.HomeAdapter
import com.example.travelapplication.adapter.HomeItemClickListener
import com.example.travelapplication.databinding.FragmentHomeBinding
import com.example.travelapplication.model.Attraction


class HomeFragment : BaseFragment(),HomeItemClickListener {

    lateinit var fragmentHomeBinding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        fragmentHomeBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_home, container, false)

//        fragmentHomeBinding..setOnClickListener {
//            findNavController().navigate(R.id.action_homeFragment_to_attractionDetailFragment)
//            //Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_attractionDetailFragment);
//        }
        return fragmentHomeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // safe args
        val homeAdapter = HomeAdapter{ attractionId ->
            val action = HomeFragmentDirections.actionHomeFragmentToAttractionDetailFragment(attractionId)
            findNavController().navigate(action)
        }
        fragmentHomeBinding.recyclerView.adapter = homeAdapter
        homeAdapter.setData(attraction)

        //create divider between items
        fragmentHomeBinding.recyclerView.addItemDecoration(DividerItemDecoration(requireContext(),RecyclerView.VERTICAL))

    }


    // best practice onClick
    override fun onItemRVClickListener(attraction: Attraction) {
        TODO("Not yet implemented")
    }
}
