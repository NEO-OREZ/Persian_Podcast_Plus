package com.NEO_OREZ.persianpodcastplus.adapters

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.NEO_OREZ.persianpodcastplus.DataQueryHOTQuery
import com.NEO_OREZ.persianpodcastplus.R
import com.NEO_OREZ.persianpodcastplus.databinding.ItemLayoutBinding


class RecyclerAdapterHot (private val homeFeed : ArrayList<DataQueryHOTQuery.Data1>) : RecyclerView.Adapter<MyViewHolderCat>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderCat {
        val bindingH = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolderCat(bindingH)
    }

    override fun getItemCount(): Int {
        return homeFeed.size
    }

    override fun onBindViewHolder(holderHot: MyViewHolderCat, position: Int) {
        val feed2 = homeFeed[position]
        holderHot.bindingH.tvTitle.text = feed2.title
        holderHot.bindingH.tvDescription.text = feed2.description
        val thumbnailOne = holderHot.bindingH.ivImage
        Glide.with(holderHot.itemView).load(feed2.imageUrl).into(thumbnailOne)

        holderHot.bindingH.cvCardView.setOnClickListener {
            val selectedID = feed2.id
            Log.d("TTT", selectedID)
            val bundle = Bundle()
            bundle.putString("1",selectedID)
            Navigation.findNavController(holderHot.itemView)
                .navigate(R.id.action_firsFragment_to_episodesFragment, bundle)
        }
    }
}

class MyViewHolderCat(val bindingH: ItemLayoutBinding) : RecyclerView.ViewHolder(bindingH.root) {

}