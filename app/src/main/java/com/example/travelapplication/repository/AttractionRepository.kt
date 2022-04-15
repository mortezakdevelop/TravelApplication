package com.example.travelapplication.repository

import android.content.Context
import com.example.travelapplication.R
import com.example.travelapplication.model.Attraction
import com.example.travelapplication.model.AttractionResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.delay

class AttractionRepository {
    private val moshi: Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

        fun parseAttractions(context:Context): ArrayList<Attraction> {

        val textFormFile =
            context.resources.openRawResource(R.raw.croatia).bufferedReader().use { it.readText() }

        //use moshi to parse json string into list
        //در این جا هم آن را به string تبدیل می کنیم
        val adapter : JsonAdapter<AttractionResponse> =  moshi.adapter(AttractionResponse::class.java)
        return adapter.fromJson(textFormFile)!!.attractions as ArrayList<Attraction>

        //ارور می دهد به خاطر نداشتن کلاس attraction response
//        val type = Types.newParameterizedType(List::class.java,Attraction::class.java)
//        val adapter : JsonAdapter<List<Attraction>> =  moshi.adapter(type)
//        return adapter.fromJson(textFormFile) as? ArrayList<Attraction>?: ArrayList()
    }
}