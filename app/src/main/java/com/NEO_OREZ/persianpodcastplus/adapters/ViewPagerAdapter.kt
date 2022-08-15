package com.NEO_OREZ.persianpodcastplus.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.NEO_OREZ.persianpodcastplus.Fragment.FirsFragment
import com.NEO_OREZ.persianpodcastplus.Fragment.MainFragment
import com.NEO_OREZ.persianpodcastplus.Fragment.SecondFragment


class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle){

    override fun getItemCount(): Int {
        return 3

    }

    override fun createFragment(position: Int): Fragment {
      return  when(position){
            0->{MainFragment()}
            1->{FirsFragment()}
            2->{SecondFragment()}
            else->{Fragment()}
        }
    }
}