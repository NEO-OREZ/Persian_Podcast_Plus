package com.NEO_OREZ.persianpodcastplus.Fragment

import android.app.ActionBar
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.NEO_OREZ.persianpodcastplus.Apollo.CallRequest
import com.NEO_OREZ.persianpodcastplus.EpisodesViewModel
import com.NEO_OREZ.persianpodcastplus.EpisodesViewModelFactory
import com.NEO_OREZ.persianpodcastplus.MainActivity
import com.NEO_OREZ.persianpodcastplus.adapters.RecyclerAdapterEpisode
import com.NEO_OREZ.persianpodcastplus.databinding.EpisodesFragmentBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.annotations.NotNull


class EpisodesFragment : Fragment() {

    private lateinit var bindingEpisode : EpisodesFragmentBinding
    private lateinit var viewModel: EpisodesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bindingEpisode = EpisodesFragmentBinding.inflate(inflater, container, false)
        return bindingEpisode.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val factoryEpi = EpisodesViewModelFactory()
        viewModel = ViewModelProvider(this, factoryEpi).get(EpisodesViewModel::class.java)
        ActionBar.DISPLAY_HOME_AS_UP

        lifecycleScope.launch(Dispatchers.IO) {

            val selectedID  =  getArguments()?.getString("1") as String
            Log.d("logEpiFrag_01selectedID", selectedID)
            val getToken = loadData()
            val getDataCat =CallRequest().apolloDataEpisode(getToken, selectedID)
            Log.d("logEpiFrag_02Data", getDataCat.toString())


            withContext(Dispatchers.Main){
                viewModel.EpisodeData(getDataCat)
                viewModel.dataEpisodeLive.observe(viewLifecycleOwner, Observer { it->
                    Log.d("logEpiFrag_03it",it.toString())
                    bindingEpisode.rvEpisodeFrag.layoutManager = LinearLayoutManager(context)
                    bindingEpisode.rvEpisodeFrag.adapter = RecyclerAdapterEpisode(it)
                })
            }
        }
    }

    private fun loadData() : String {
        val sharedPreferences = activity?.getSharedPreferences("KeySave", Context.MODE_PRIVATE)
        val key = sharedPreferences?.getString("key01","Key is null")
        Log.d("logEpiFrag_04Key",key.toString())
        return key.toString()
    }
}