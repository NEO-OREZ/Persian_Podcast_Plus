package com.NEO_OREZ.persianpodcastplus

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.NEO_OREZ.persianpodcastplus.DataEpisodesQuery

class EpisodesViewModel : ViewModel() {

    var dataEpisodeLive = MutableLiveData<ArrayList<DataEpisodesQuery.Data1>>()
    val dataEpisode = ArrayList<DataEpisodesQuery.Data1> ()

    fun EpisodeData(daTa:ArrayList<DataEpisodesQuery.Data1>) {
        dataEpisode.addAll(daTa)
        dataEpisodeLive.value = dataEpisode

        Log.d("logViewMode30", dataEpisodeLive.toString())
        Log.d("logViewMode31", dataEpisode.toString())
    }
}