package com.NEO_OREZ.persianpodcastplus


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.NEO_OREZ.persianpodcastplus.DataCategoriesQuery


class CategoryViewModel : ViewModel() {

    var dataCatLive = MutableLiveData<ArrayList<DataCategoriesQuery.Data1>>()
    val dataCat = ArrayList<DataCategoriesQuery.Data1> ()

    fun CatData(daTa:ArrayList<DataCategoriesQuery.Data1>) {
        dataCat.addAll(daTa)
        dataCatLive.value = dataCat
    }
}