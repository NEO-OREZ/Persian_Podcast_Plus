package com.NEO_OREZ.persianpodcastplus

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class SecondFragViewModelFactory : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SecondFragViewModel::class.java)){
            return SecondFragViewModel() as T

        }
        throw IllegalArgumentException ("UnknownViewModel")
    }
}