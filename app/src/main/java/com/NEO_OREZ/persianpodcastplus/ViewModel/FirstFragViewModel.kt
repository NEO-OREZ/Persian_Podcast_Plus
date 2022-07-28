package com.NEO_OREZ.persianpodcastplus

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.NEO_OREZ.persianpodcastplus.DataQueryHOTQuery


class FirstFragViewModel : ViewModel() {

    var dataHotLive = MutableLiveData<ArrayList<DataQueryHOTQuery.Data1>>()
    val dataHot = ArrayList<DataQueryHOTQuery.Data1> ()

    fun HotData(daTa:ArrayList<DataQueryHOTQuery.Data1>){
        dataHot.addAll(daTa)
        dataHotLive.value = dataHot

        Log.d("logViewModel0", dataHotLive.toString())
        Log.d("logViewModel1", dataHot.toString())
    }
}