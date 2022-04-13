package com.example.travelapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.travelapplication.R
import com.example.travelapplication.adapter.HomeAdapter
import com.example.travelapplication.adapter.HomeItemClickListener
import com.example.travelapplication.databinding.FragmentHomeBinding
import com.example.travelapplication.model.Attraction


class HomeFragment : BaseFragment(),HomeItemClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val homeAdapter = HomeAdapter{ attractionId ->
            navController.navigate(R.id.action_homeFragment_to_attractionDetailFragment)
            activityViewModel.onAttractionSelected(attractionId)

            // safe args
//            val action = HomeFragmentDirections.actionHomeFragmentToAttractionDetailFragment(attractionId)
//            findNavController().navigate(action)
        }
        binding.recyclerView.adapter = homeAdapter


        activityViewModel.attractionListLiveData.observe(viewLifecycleOwner){attractions ->
            homeAdapter.setData(attractions)
        }


        //create divider between items
        binding.recyclerView.addItemDecoration(DividerItemDecoration(requireContext(),RecyclerView.VERTICAL))

    }


    // best practice onClick
    override fun onItemRVClickListener(attraction: Attraction) {
        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
