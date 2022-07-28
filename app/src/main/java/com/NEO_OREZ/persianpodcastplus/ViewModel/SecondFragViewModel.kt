package com.NEO_OREZ.persianpodcastplus

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.NEO_OREZ.persianpodcastplus.GridModel

class SecondFragViewModel : ViewModel() {

    var dataSecLive = MutableLiveData<ArrayList<GridModel>>()
    val dataSec = ArrayList<GridModel> ()

    fun SecondData(daTa:ArrayList<GridModel>) {
        dataSec.addAll(daTa)
        dataSecLive.value = dataSec
    }
}