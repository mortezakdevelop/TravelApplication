package com.example.travelapplication.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelapplication.model.Attraction
import com.example.travelapplication.repository.AttractionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AttractionViewModel :ViewModel(){
    private val repository = AttractionRepository()

    //HomeFragment
    val attractionListLiveData = MutableLiveData<List<Attraction>>()

    //AttractionDetailFragment
    val selectedAttractionLiveData = MutableLiveData<Attraction>()

    fun init(context: Context){
        viewModelScope.launch(Dispatchers.IO) {
            val attractionList = repository.parseAttractions(context)
            attractionListLiveData.postValue(attractionList)
        }
    }

    fun onAttractionSelected(attractionId:String){
       viewModelScope.launch(Dispatchers.IO) {
           val attraction = attractionListLiveData.value?.find {
               it.id == attractionId
           } ?: return@launch

           selectedAttractionLiveData.postValue(attraction)
       }
    }
}