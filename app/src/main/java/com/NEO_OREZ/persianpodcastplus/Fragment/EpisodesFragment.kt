package com.NEO_OREZ.persianpodcastplus.Fragment

import android.app.ActionBar
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

//          val selectedID  =  getArguments()?.getString("1") as String
            val getToken = MainFragment().loadData()
            val getDataCat =CallRequest().apolloDataEpisode(getToken, "687224")
            Log.d("logfragfirst_Data", getDataCat.toString())

            withContext(Dispatchers.Main){
                viewModel.EpisodeData(getDataCat)
                viewModel.dataEpisodeLive.observe(viewLifecycleOwner, Observer { it->
                    Log.d("logfragfirst_it",it.toString())
                    bindingEpisode.rvEpisodeFrag.layoutManager = LinearLayoutManager(context)
                    bindingEpisode.rvEpisodeFrag.adapter = RecyclerAdapterEpisode(it)
                })
            }
        }
    }
}