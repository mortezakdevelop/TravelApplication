package com.example.travelapplication.ui

import androidx.fragment.app.Fragment
import com.example.travelapplication.MainActivity
import com.example.travelapplication.model.Attraction
import com.example.travelapplication.viewmodel.AttractionViewModel

abstract class BaseFragment: Fragment() {
    protected val navController by lazy {
        (activity as MainActivity).navController
    }
    
    protected val activityViewModel:AttractionViewModel
        get()  = (activity as MainActivity).viewModel
}