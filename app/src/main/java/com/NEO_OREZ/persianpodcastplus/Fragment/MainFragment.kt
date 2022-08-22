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

    fun loadData() : String{
        val sharedPreferences = activity?.getSharedPreferences("KeySave", Context.MODE_PRIVATE)
        val key = sharedPreferences?.getString("key0", "default text if null")
        return key.toString()
    }

    suspend fun transit(id:String) {
        Log.d("logMainFrag_03transit", id)
        //val bundle = Bundle()
        //bundle.putString("2", id)
        //val navHostFragment = Navigation.findNavController(requireActivity(),R.id.fragmentContainerViewMfragID)

        Log.d("logMainFrag_04cycle", id)
        findNavControllerSafely().navigate(R.id.episodesFragment)
       // Navigation.findNavController(requireView()).navigate(R.id.episodesFragment)


    }

    fun Fragment.findNavControllerSafely(): NavController {
        return if (isAdded) {
            findNavController()
        } else {  Log.d("logMainFrag_04cycle", "dfghhjcb")  as NavController}
    }


//        val transit = childFragmentManager.beginTransaction()
//        Log.d("logfragMain_transitTo",transit.toString())
//        transit.replace(R.id.episodesFragment, EpisodesFragment())
//        transit.setReorderingAllowed(true)
//        transit.addToBackStack(null)
//        transit.commit()

}