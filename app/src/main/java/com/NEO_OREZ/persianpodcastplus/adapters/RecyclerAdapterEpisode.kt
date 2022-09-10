package com.NEO_OREZ.persianpodcastplus.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.NEO_OREZ.persianpodcastplus.DataQEpisodeQuery
import com.bumptech.glide.Glide
import com.NEO_OREZ.persianpodcastplus.databinding.ItemLayoutBinding


class RecyclerAdapterEpisode (private val homeFeed : ArrayList<DataQEpisodeQuery.Data1>)
    : RecyclerView.Adapter<MyViewHolderEpisode>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderEpisode {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolderEpisode(binding)
    }

    override fun getItemCount(): Int {
        return homeFeed.size
    }

    override fun onBindViewHolder(holder: MyViewHolderEpisode, position: Int) {
        val feed2 = homeFeed[position]
        holder.binding.tvTitle.text = feed2.title
        holder.binding.tvDescription.text = feed2.description
        val thumbnailOne = holder.binding.ivImage
        Glide.with(holder.itemView).load(feed2.imageUrl).into(thumbnailOne)
    }
}

class MyViewHolderEpisode(val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

//    init {
//        binding.cvCardView.setOnClickListener {
//            findNavController(itemView).navigate(R.id.webViewFragment)
//        }
//   }
}