package com.NEO_OREZ.persianpodcastplus

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class EpisodesViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EpisodesViewModel::class.java)) {
            return EpisodesViewModel() as T
        }
        throw IllegalArgumentException("UnknownViewModel")
    }
}