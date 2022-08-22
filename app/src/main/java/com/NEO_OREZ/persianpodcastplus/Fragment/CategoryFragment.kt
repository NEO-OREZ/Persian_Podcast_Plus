package com.NEO_OREZ.persianpodcastplus.Fragment


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
import com.NEO_OREZ.persianpodcastplus.CategoryViewModel
import com.NEO_OREZ.persianpodcastplus.CategoryViewModelFactory
import com.NEO_OREZ.persianpodcastplus.MainActivity
import com.NEO_OREZ.persianpodcastplus.adapters.RecyclerAdapterCat
import com.NEO_OREZ.persianpodcastplus.databinding.CategoryFragmentBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class CategoryFragment : Fragment() {
    private lateinit var viewModel: CategoryViewModel
    private lateinit var bindingCatFrag : CategoryFragmentBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bindingCatFrag = CategoryFragmentBinding.inflate(inflater, container, false)
        return bindingCatFrag.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val factoryCat = CategoryViewModelFactory()
        viewModel = ViewModelProvider(this, factoryCat).get(CategoryViewModel::class.java)

        lifecycleScope.launch(Dispatchers.IO) {
            val selectedCategory = getArguments()?.getString("2")
            Log.d("logfragcat_string",selectedCategory.toString())
            val getToken = MainFragment().loadData()
            val getDataCat = CallRequest().apolloDataCat(getToken,selectedCategory)
            Log.d("logfragfirst_Data", getDataCat.toString())

            withContext(Dispatchers.Main){
                viewModel.CatData(getDataCat)

                viewModel.dataCatLive.observe(viewLifecycleOwner, Observer { it->
                    Log.d("logfragcat_it",it.toString())
                    bindingCatFrag.rvCatfrag.layoutManager = LinearLayoutManager(context)
                    bindingCatFrag.rvCatfrag.adapter = RecyclerAdapterCat(it)
                })
            }
        }
    }
}