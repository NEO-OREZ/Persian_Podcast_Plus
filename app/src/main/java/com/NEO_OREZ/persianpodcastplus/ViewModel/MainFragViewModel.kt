package com.NEO_OREZ.persianpodcastplus

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.NEO_OREZ.persianpodcastplus.DataQuery


class MainFragViewModel : ViewModel(){

    var dataListLive = MutableLiveData<ArrayList<DataQuery.Data1>>()
    val dataList = ArrayList<DataQuery.Data1> ()

    fun xData(daTa:ArrayList<DataQuery.Data1>){
        dataList.addAll(daTa)
        dataListLive.value = dataList

        Log.d("logViewModel0",dataListLive.toString())
        Log.d("logViewModel1",dataList.toString())
    }
}

