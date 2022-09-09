package com.NEO_OREZ.persianpodcastplus.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.NEO_OREZ.persianpodcastplus.adapters.ViewPagerAdapter
import com.NEO_OREZ.persianpodcastplus.databinding.FragmentHostBinding
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HostFragment : Fragment() {
    private lateinit var bindingHostFrag : FragmentHostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("logHostFrag_01","Host Frag onCreate Start")


        lifecycleScope.launch(Dispatchers.Main){
            TabLayoutMediator(bindingHostFrag.tabLayoutID, bindingHostFrag.viewPager2ID) {
                    tab, position-> when(position){
                        0->{tab.text = "Main"}
                        1->{tab.text = "First"}
                         2->{tab.text = "Second"}
                    }
            }.attach()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d("logHostFrag_02","Host Frag onCreateView Start")
        bindingHostFrag = FragmentHostBinding.inflate(inflater, container, false)
        val adapter = ViewPagerAdapter(childFragmentManager, lifecycle)
        bindingHostFrag.viewPager2ID.adapter = adapter
        return bindingHostFrag.root
    }
}