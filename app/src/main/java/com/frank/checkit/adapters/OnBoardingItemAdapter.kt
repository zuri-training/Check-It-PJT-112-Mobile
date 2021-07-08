package com.frank.checkit.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.frank.checkit.R
import com.frank.checkit.model.OnBoardingItem

class OnBoardingItemAdapter(private val onboardingItems: List<OnBoardingItem>):RecyclerView.Adapter<OnBoardingItemAdapter.OnBoardingItemViewHolder>() {
    inner class OnBoardingItemViewHolder(view: View):RecyclerView.ViewHolder(view){
        private val imageOnBoarding = view.findViewById<ImageView>(R.id.imageOnboarding)
        private val titleOnBoarding = view.findViewById<TextView>(R.id.onboardingTitleText)
        private val descriptionOnBoarding = view.findViewById<TextView>(R.id.onboardingDescription)

        fun bindData(data:OnBoardingItem){
            imageOnBoarding.setImageResource(data.onBoardingImage)
            titleOnBoarding.text = data.onBoardingTitle
            descriptionOnBoarding.text = data.onBoardingDescription
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingItemViewHolder {
        return OnBoardingItemViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.on_boarding_item_container,parent,false))
    }

    override fun onBindViewHolder(holder: OnBoardingItemViewHolder, position: Int) {
        holder.bindData(onboardingItems[position])
    }

    override fun getItemCount(): Int {
       return onboardingItems.size
    }
}