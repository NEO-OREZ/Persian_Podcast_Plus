package com.NEO_OREZ.persianpodcastplus.Fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.NEO_OREZ.persianpodcastplus.Apollo.CallRequest
import com.NEO_OREZ.persianpodcastplus.FirstFragViewModel
import com.NEO_OREZ.persianpodcastplus.FirstViewModelFactory
import com.NEO_OREZ.persianpodcastplus.R
import com.NEO_OREZ.persianpodcastplus.adapters.RecyclerAdapterFirst
import com.NEO_OREZ.persianpodcastplus.databinding.FragmentFirsBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class FirsFragment : Fragment() {
    private lateinit var bindingFirstFrag : FragmentFirsBinding
    private lateinit var viewModelHot : FirstFragViewModel
    private var factoryHot = FirstViewModelFactory()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("logFragFirst_00", "First Frag Started")

        viewModelHot = ViewModelProvider(this, factoryHot).get(FirstFragViewModel::class.java)

        val getToken = MainFragment().loadData()
        Log.d("logFragFirst_01Token", getToken)
        lifecycleScope.launch(Dispatchers.IO) {
            val getDataHot = CallRequest().apolloDataHot(getToken)
            Log.d("logFragFirst_02Data", getDataHot.toString())

            withContext(Dispatchers.Main){
                viewModelHot.HotData(getDataHot)

                viewModelHot.dataHotLive.observe(this@FirsFragment, Observer { it->
                   // Log.d("logFirstFrag_03it",it.toString())
                    bindingFirstFrag.rvFragfirst.layoutManager = LinearLayoutManager(context)
                    bindingFirstFrag.rvFragfirst.adapter = RecyclerAdapterFirst(it)
                })
            }
        }
    }
    fun transit(id:String) {
        findNavController().navigate(R.id.action_mainFragment_to_episodesFragment)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bindingFirstFrag = FragmentFirsBinding.inflate(inflater, container, false)
        return bindingFirstFrag.root
    }
}