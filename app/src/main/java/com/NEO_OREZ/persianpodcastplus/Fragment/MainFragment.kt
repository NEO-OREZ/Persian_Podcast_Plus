package com.NEO_OREZ.persianpodcastplus.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.NEO_OREZ.persianpodcastplus.Apollo.CallRequest
import com.NEO_OREZ.persianpodcastplus.MainFragViewModel
import com.NEO_OREZ.persianpodcastplus.MainViewModelFactory
import com.NEO_OREZ.persianpodcastplus.adapters.RecyclerAdapter
import com.NEO_OREZ.persianpodcastplus.databinding.FragmentMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainFragment : Fragment() {
    lateinit var bindingMainFrag : FragmentMainBinding
    lateinit var viewModel : MainFragViewModel
    var factory = MainViewModelFactory()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, factory).get(MainFragViewModel::class.java)
        GlobalScope.launch(Dispatchers.IO) {
            val getToken = CallRequest().apolloToken()
            val getData = CallRequest().apolloDataMain(getToken)
            Log.d("logfrag_Data",getData.toString())

            withContext(Dispatchers.Main){
                viewModel.xData(getData)
                viewModel.dataListLive.observe(this@MainFragment, Observer { it->
                    Log.d("logfrag_it",it.toString())
                    bindingMainFrag.rvFragmain.layoutManager = LinearLayoutManager(context)
                    bindingMainFrag.rvFragmain.adapter = RecyclerAdapter(it)

                })
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bindingMainFrag = FragmentMainBinding.inflate(inflater,container,false)
        return  bindingMainFrag.root
    }
}