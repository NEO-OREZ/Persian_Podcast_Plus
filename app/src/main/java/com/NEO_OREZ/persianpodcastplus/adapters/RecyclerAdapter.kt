package com.NEO_OREZ.persianpodcastplus.adapters


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.NEO_OREZ.persianpodcastplus.DataQuery
import com.NEO_OREZ.persianpodcastplus.R
import com.NEO_OREZ.persianpodcastplus.databinding.ItemLayoutBinding

class RecyclerAdapter (private val homeFeed : ArrayList<DataQuery.Data1>): RecyclerView.Adapter<MyViewHolder>() {
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
            Log.d("TTT", selectedID)
            val bundle = Bundle()
            bundle.putString("1",selectedID)
            findNavController(holder.itemView).navigate(R.id.action_mainFragment_to_episodesFragment, bundle)
        }
    }
}

 class MyViewHolder(val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

}