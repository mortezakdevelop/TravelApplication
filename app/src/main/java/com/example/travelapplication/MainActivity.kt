package com.example.travelapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.travelapplication.model.Attraction
import com.example.travelapplication.model.AttractionResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController
    val attractionList: List<Attraction> by lazy {
        parseAttractions()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.navController
    }

    //parsing raw resource json file
    //با اجزای این کد, تمام فایل json ما به این متغیر تزریق و ریخته میشود
    //سپس می توانیم moshi خود را build کنیم
    private fun parseAttractions(): List<Attraction> {
        val textFormFile =
            resources.openRawResource(R.raw.croatia).bufferedReader().use { it.readText() }
        val moshi: Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

        //use moshi to parse json string into list
        //در این جا هم آن را به string تبدیل می کنیم
        val adapter : JsonAdapter<AttractionResponse> =  moshi.adapter(AttractionResponse::class.java)
        return adapter.fromJson(textFormFile)!!.attractions

        //ارور می دهد به خاطر نداشتن کلاس attraction response
//        val type = Types.newParameterizedType(List::class.java,Attraction::class.java)
//        val adapter : JsonAdapter<List<Attraction>> =  moshi.adapter(type)
//        return adapter.fromJson(textFormFile) as? ArrayList<Attraction>?: ArrayList()
    }
}