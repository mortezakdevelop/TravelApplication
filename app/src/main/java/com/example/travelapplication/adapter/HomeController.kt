package com.example.travelapplication.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.EpoxyController
import com.bumptech.glide.Glide
import com.example.travelapplication.R
import com.example.travelapplication.databinding.AttractionItemsBinding
import com.example.travelapplication.epoxy.LoadingEpoxyModel
import com.example.travelapplication.epoxy.ViewBindingKotlinModel
import com.example.travelapplication.model.Attraction
import com.squareup.picasso.Picasso

//به خاطر argument که در navigation به detail دادیم , پارامتر string را به onClick می دهیم
//با این کار به ایدی attraction دسترسی داریم
class HomeController(private val onClickCallback: (String) -> Unit) : EpoxyController() {

    //setter
    //if value is false build model
    var isLoading: Boolean = false
        set(value) {
            field = value
            if (field) {
                requestModelBuild()
            }
        }
    var attractions = ArrayList<Attraction>()
        set(value) {
            field = value
            isLoading = false
            requestModelBuild()
        }

    override fun buildModels() {
        if (isLoading) {
            LoadingEpoxyModel().id("loading_state").addTo(this)
            return
        }
        if (attractions.isEmpty()) {
            //todo show empty state
            return
        }

        attractions.forEach { attraction ->
            AttractionEpoxyModel(attraction, onClickCallback)
                .id(attraction.id)
                .addTo(this)
        }
    }

    data class AttractionEpoxyModel(
        val attraction: Attraction,
        val onClicked: (String) -> Unit
    ) : ViewBindingKotlinModel<AttractionItemsBinding>(R.layout.attraction_items) {
        override fun AttractionItemsBinding.bind() {
            tvTitle.text = attraction.title
            Glide.with(root.context)
                .load(attraction.image_url)
                .into(ivImage)
            tvAllYear.text = attraction.months_to_visit

            root.setOnClickListener {
                onClicked(attraction.id)
            }
        }
    }
}


//second way without epoxy
////به خاطر argument که در navigation به detail دادیم , پارامتر string را به onClick می دهیم
////با این کار به ایدی attraction دسترسی داریم
//class HomeAdapter(private val onClickCallback:(String)-> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//    private val attractions = ArrayList<Attraction>()
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        return AttractionViewHolder(parent)
//    }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        (holder as AttractionViewHolder).onBind(attractions[position],onClickCallback)
//    }
//
//    override fun getItemCount(): Int {
//        return attractions.size
//    }
//
//    @SuppressLint("NotifyDataSetChanged")
//    fun setData(attractions: List<Attraction>) {
//        this.attractions.clear()
//        this.attractions.addAll(attractions)
//        notifyDataSetChanged()
//    }
//
//    inner class AttractionViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
//        LayoutInflater.from(parent.context).inflate(R.layout.attraction_items, parent, false)
//    ) {
//        private val binding = AttractionItemsBinding.bind(itemView)
//        fun onBind(attraction: Attraction, onClick:(String)->Unit) {
//            binding.tvTitle.text = attraction.title
//            Glide.with(binding.root.context)
//                .load(attraction.image_url)
//                .into(binding.ivImage)
//            binding.tvAllYear.text = attraction.months_to_visit
//
//            binding.root.setOnClickListener {
//                onClick(attraction.id)
//            }
//        }
//    }
//
//
//


//class HomeAdapter(var data: ArrayList<Attraction>,private val listener:HomeItemClickListener):RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
//
//    private val attraction = ArrayList<Attraction>()
//
//    inner class HomeViewHolder(private val binding:AttractionItemsBinding): RecyclerView.ViewHolder(binding.root) {
//        fun bind(attraction: Attraction,listener: HomeItemClickListener) {
//
//            binding.apply {
//                this.tvTitle.setTextFuture(
//                    PrecomputedTextCompat.getTextFuture(
//                        attraction.title,
//                        binding.tvTitle.textMetricsParamsCompat,
//                        null
//                    )
//                )
//                Picasso.get().load(attraction.image_urls[position]).into(binding.ivImage)
//                this.tvAllYear.setTextFuture(
//                    PrecomputedTextCompat.getTextFuture(
//                        attraction.months_to_visit,
//                        binding.tvAllYear.textMetricsParamsCompat,
//                        null
//                    )
//                )
//            }
//
//            binding.ivImage.setOnClickListener {
//                listener.onItemRVClickListener(attraction)
//            }
//        }
//    }
//
//    @SuppressLint("NotifyDataSetChanged")
//    fun setData(attraction:List<Attraction>){
//        this.attraction.clear()
//        this.attraction.addAll(attraction)
//        notifyDataSetChanged()
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
//        val attractionItemsBinding:AttractionItemsBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context)
//        , R.layout.attraction_items,parent,false)
//        return HomeViewHolder(attractionItemsBinding)
//    }
//
//    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
//        holder.bind(data[position],listener)
//    }
//
//    override fun getItemCount(): Int {
//        return attraction.size
//    }
//}
//
