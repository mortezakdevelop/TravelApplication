package com.example.travelapplication.ui

import androidx.fragment.app.Fragment
import com.example.travelapplication.MainActivity
import com.example.travelapplication.model.Attraction

abstract class BaseFragment: Fragment() {
    protected val navController by lazy {
        (activity as MainActivity).navController
    }

    protected val attractions:List<Attraction>
        get() = (activity as MainActivity).attractionList
}