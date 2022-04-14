package com.example.travelapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.travelapplication.R
import com.example.travelapplication.adapter.HomeController
import com.example.travelapplication.adapter.HomeItemClickListener
import com.example.travelapplication.databinding.FragmentHomeBinding
import com.example.travelapplication.model.Attraction


class HomeFragment : BaseFragment(), HomeItemClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val epoxyController = HomeController { attractionId ->
            navController.navigate(R.id.action_homeFragment_to_attractionDetailFragment)
            activityViewModel.onAttractionSelected(attractionId)

            // safe args
//            val action = HomeFragmentDirections.actionHomeFragmentToAttractionDetailFragment(attractionId)
//            findNavController().navigate(action)
        }

        binding.EpoxyRecyclerView.setController(epoxyController)
        //create divider between items
        binding.EpoxyRecyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                RecyclerView.VERTICAL
            )
        )
        // binding.recyclerView.adapter = homeController.adapter


        activityViewModel.attractionListLiveData.observe(viewLifecycleOwner) { attractions ->
            epoxyController.attractions = attractions
        }


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
