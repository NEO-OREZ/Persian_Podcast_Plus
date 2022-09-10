package com.NEO_OREZ.persianpodcastplus.Fragment


import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.*
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.NEO_OREZ.persianpodcastplus.Apollo.CallRequest
import com.NEO_OREZ.persianpodcastplus.MainFragViewModel
import com.NEO_OREZ.persianpodcastplus.MainViewModelFactory
import com.NEO_OREZ.persianpodcastplus.R
import com.NEO_OREZ.persianpodcastplus.adapters.RecyclerAdapter
import com.NEO_OREZ.persianpodcastplus.databinding.FragmentMainBinding
import kotlinx.coroutines.*


class MainFragment : Fragment() {
    lateinit var bindingMainFrag : FragmentMainBinding
    lateinit var viewModel : MainFragViewModel
    var factory = MainViewModelFactory()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, factory).get(MainFragViewModel::class.java)

        lifecycleScope.launch(Dispatchers.IO) {
            delay(500)
            val getToken = loadData()
            val getData = CallRequest().apolloDataMain(getToken)
            Log.d("logMainFrag_00Data",getData.toString())

            withContext(Dispatchers.Main){
                viewModel.xData(getData)
                viewModel.dataListLive.observe(this@MainFragment, Observer { it->
                    Log.d("logMainFrag_01it",it.toString())
                    bindingMainFrag.rvFragmain.layoutManager = LinearLayoutManager(context)
                    bindingMainFrag.rvFragmain.adapter = RecyclerAdapter(getData)
                })
            }
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bindingMainFrag = FragmentMainBinding.inflate(inflater,container,false)
        return  bindingMainFrag.root
    }

    private fun loadData() : String {
        val sharedPreferences = activity?.getSharedPreferences("KeySave", Context.MODE_PRIVATE)
        val key = sharedPreferences?.getString("key01","Key is null")
        Log.d("logMainFrag_03Key",key.toString())
        return key.toString()
    }

}