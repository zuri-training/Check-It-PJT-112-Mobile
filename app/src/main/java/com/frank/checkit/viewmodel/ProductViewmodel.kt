package com.frank.checkit.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.frank.checkit.model.CategoryData
import com.frank.checkit.model.FakersData
import com.frank.checkit.repository.ProductRepository

class ProductViewmodel: ViewModel() {
    private var fakersLiveData : MutableLiveData<List<FakersData>>? = null


    fun getFakersData(): MutableLiveData<List<FakersData>> {
        fakersLiveData = ProductRepository.loadData()
        return fakersLiveData as MutableLiveData<List<FakersData>>

    }





}