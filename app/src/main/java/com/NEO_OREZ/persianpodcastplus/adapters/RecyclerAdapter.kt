package com.NEO_OREZ.persianpodcastplus.adapters


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.NEO_OREZ.persianpodcastplus.DataQuery
import com.NEO_OREZ.persianpodcastplus.Fragment.EpisodesFragment
import com.NEO_OREZ.persianpodcastplus.Fragment.MainFragment
import com.NEO_OREZ.persianpodcastplus.MainActivity
import com.NEO_OREZ.persianpodcastplus.R
import com.NEO_OREZ.persianpodcastplus.databinding.ItemLayoutBinding

class RecyclerAdapter (private val homeFeed : ArrayList<DataQuery.Data1> ): RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return homeFeed.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val feed2 = homeFeed[position]
        holder.binding.tvTitle.text = feed2.title
        holder.binding.tvDescription.text = feed2.description
        val thumbnailOne = holder.binding.ivImage
        Glide.with(holder.itemView).load(feed2.imageUrl).into(thumbnailOne)

        holder.binding.cvCardView.setOnClickListener {
            val selectedID = feed2.id
            MainFragment().transit(selectedID)



           // val x = fragment.childFragmentManager.beginTransaction()
          //  x.replace(android.R.id.content, EpisodesFragment()).commit()

//            val transaction0 = activity.supportFragmentManager.beginTransaction()
//            transaction0.replace(android.R.id.content, EpisodesFragment())
            //findNavController(holder.itemView).navigate(R.id.action_global_episodesFragment, bundle)
        }
    }
}

 class MyViewHolder(val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

}