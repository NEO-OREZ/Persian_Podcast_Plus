package com.NEO_OREZ.persianpodcastplus.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.NEO_OREZ.persianpodcastplus.ViewModel.GridModel
import com.NEO_OREZ.persianpodcastplus.R
import com.NEO_OREZ.persianpodcastplus.SecondFragViewModel
import com.NEO_OREZ.persianpodcastplus.SecondFragViewModelFactory
import com.NEO_OREZ.persianpodcastplus.adapters.GridAdapter
import com.NEO_OREZ.persianpodcastplus.databinding.FragmentSecondBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.ArrayList


class SecondFragment : Fragment() {
    private lateinit var bindingSecFrag: FragmentSecondBinding
    private lateinit var ListA: MutableList<GridModel>
    private lateinit var viewModel: SecondFragViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ListA = ArrayList()
        ListA.add(GridModel("Arts",R.drawable.arts))
        ListA.add(GridModel("Animation",R.drawable.animation100))
        ListA.add(GridModel("Books",R.drawable.book))
        ListA.add(GridModel("Business",R.drawable.business))
        ListA.add(GridModel("Education",R.drawable.education))
        ListA.add(GridModel("Games",R.drawable.game))
        ListA.add(GridModel("Comedy",R.drawable.comedy))
        ListA.add(GridModel("Kids",R.drawable.kid))
        ListA.add(GridModel("Music",R.drawable.music))
        ListA.add(GridModel("Religion",R.drawable.religion))
        ListA.add(GridModel("Society",R.drawable.society))
        ListA.add(GridModel("Sports",R.drawable.sports))
        ListA.add(GridModel("Technology",R.drawable.technology))
        ListA.add(GridModel("Travel",R.drawable.travel))

        val factory = SecondFragViewModelFactory()
        viewModel = ViewModelProvider(this, factory).get(SecondFragViewModel::class.java)


        lifecycleScope.launch(Dispatchers.IO) {

            withContext(Dispatchers.Main){
                viewModel.SecondData(ListA as ArrayList<GridModel>)

                viewModel.dataSecLive.observe(viewLifecycleOwner, Observer { it ->
                    Log.d("logFragSec_00it", it.toString())
                    bindingSecFrag.gridViewID.adapter = GridAdapter(it, requireContext())
                    bindingSecFrag.gridViewID.onItemClickListener =
                    AdapterView.OnItemClickListener { _, _, position, _ ->
                        Toast.makeText(requireContext(),
                            ListA[position].gridText + " selected", Toast.LENGTH_SHORT).show()

                        val txt = ListA[position].gridText
                        val bundle = Bundle()
                        bundle.putString("0", txt)
                       // findNavController().navigate(R.id.action_secondFragment_to_categoryFragment, bundle)

                    }
                })
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bindingSecFrag = FragmentSecondBinding.inflate(inflater, container, false)
        return bindingSecFrag.root
    }
}