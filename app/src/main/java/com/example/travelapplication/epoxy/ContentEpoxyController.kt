package com.example.travelapplication.epoxy

import com.airbnb.epoxy.EpoxyController
import com.example.travelapplication.R
import com.example.travelapplication.databinding.ModelDesctiptionBinding
import com.example.travelapplication.databinding.ModelFactBinding
import com.example.travelapplication.databinding.ModelFactHeaderBinding
import com.example.travelapplication.databinding.ModelMonthsToVisitBinding
import com.example.travelapplication.model.Attraction

class ContentEpoxyController(
    private val attraction: Attraction,
) : EpoxyController() {


    override fun buildModels() {
        MonthsToVisitEpoxyModel(attraction.months_to_visit).id("months_to_visit").addTo(this)

        DescriptionEpoxyModel(attraction.description).id("description").addTo(this)

        FactHeaderEpoxyModel("${attraction.facts.size} facts").id("facts_header").addTo(this)

        attraction.facts.forEachIndexed { index, fact ->
            FactEpoxyModel(fact).id("fact_$index").addTo(this)


        }

    }

    data class MonthsToVisitEpoxyModel(
        val monthToVisit: String
    ) : ViewBindingKotlinModel<ModelMonthsToVisitBinding>(R.layout.model_months_to_visit) {
        override fun ModelMonthsToVisitBinding.bind() {
            textView.text = monthToVisit
        }

        override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
            return totalSpanCount
        }
    }

    data class DescriptionEpoxyModel(
        val description: String,
    ) : ViewBindingKotlinModel<ModelDesctiptionBinding>(R.layout.model_desctiption) {
        override fun ModelDesctiptionBinding.bind() {
            textView.text = description
        }
        override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
            return totalSpanCount
        }
    }

    data class FactHeaderEpoxyModel(
        val factsText: String,

    ) : ViewBindingKotlinModel<ModelFactHeaderBinding>(R.layout.model_fact_header) {
        override fun ModelFactHeaderBinding.bind() {
            factsTextView.text = factsText

        }
        override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
            return totalSpanCount
        }

    }

    data class FactEpoxyModel(
        val fact: String
    ) : ViewBindingKotlinModel<ModelFactBinding>(R.layout.model_fact) {
        override fun ModelFactBinding.bind() {
            textView.text = fact
        }
    }
}