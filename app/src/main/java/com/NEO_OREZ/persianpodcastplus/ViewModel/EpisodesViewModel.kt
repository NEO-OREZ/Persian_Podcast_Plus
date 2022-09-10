package com.NEO_OREZ.persianpodcastplus

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EpisodesViewModel : ViewModel() {

    var dataEpisodeLive = MutableLiveData<ArrayList<DataQEpisodeQuery.Data1>>()
    val dataEpisode = ArrayList<DataQEpisodeQuery.Data1> ()

    fun EpisodeData(daTa:ArrayList<DataQEpisodeQuery.Data1>) {
        dataEpisode.addAll(daTa)
        dataEpisodeLive.value = dataEpisode

        Log.d("logViewMode30", dataEpisodeLive.toString())
        Log.d("logViewMode31", dataEpisode.toString())
    }
}