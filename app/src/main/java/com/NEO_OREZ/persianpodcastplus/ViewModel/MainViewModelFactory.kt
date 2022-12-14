package com.NEO_OREZ.persianpodcastplus

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModelFactory : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(MainFragViewModel::class.java)){
                return MainFragViewModel() as T

            }
            throw IllegalArgumentException ("UnknownViewModel")
        }
}

