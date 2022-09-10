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
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class FirsFragment : Fragment() {
    private lateinit var bindingFirstFrag : FragmentFirsBinding
    private lateinit var viewModelHot : FirstFragViewModel
    private var factoryHot = FirstViewModelFactory()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("logFirstFrag_00", "First Frag Started")

        viewModelHot = ViewModelProvider(this, factoryHot).get(FirstFragViewModel::class.java)

        val getToken = loadData()
        Log.d("logFirstFrag_01Token", getToken)
        lifecycleScope.launch(Dispatchers.IO) {
            val getDataHot = CallRequest().apolloDataHot(getToken)
            Log.d("logFirstFrag_02Data", getDataHot.toString())

            withContext(Dispatchers.Main){
                viewModelHot.HotData(getDataHot)
                viewModelHot.dataHotLive.observe(this@FirsFragment, Observer { it->
                    bindingFirstFrag.rvFragfirst.layoutManager = LinearLayoutManager(context)
                    bindingFirstFrag.rvFragfirst.adapter = RecyclerAdapterFirst(it)
                })
            }
        }
    }

    private fun loadData() : String {
        val sharedPreferences = activity?.getSharedPreferences("KeySave", Context.MODE_PRIVATE)
        val key = sharedPreferences?.getString("key01","Key is null")
        Log.d("logFirstFrag_03Key",key.toString())
        return key.toString()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bindingFirstFrag = FragmentFirsBinding.inflate(inflater, container, false)
        return bindingFirstFrag.root
    }
}