package com.danvento.heartthisatapi.ui.adapter

import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.danvento.heartthisatapi.R
import com.danvento.heartthisatapi.data.model.PopularArtist
import com.danvento.heartthisatapi.databinding.ArtistListItemBinding

class TopArtistsAdapter:
    RecyclerView.Adapter<TopArtistsAdapter.TopArtistsViewHolder>(){

    private var artistsList = mutableListOf<PopularArtist>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopArtistsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ArtistListItemBinding.inflate(layoutInflater, parent, false)

        return TopArtistsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopArtistsViewHolder, position: Int) {
        val artist = artistsList[position]

        holder.binding.apply {
            artistItemNameTv.text = artist.name
            artistItemPlaybackCountTv.text = artist.playbackCount.toString()
            artistItemFavCountTv.text = artist.favCount.toString()
            Glide.with(root.context).load(artist.avatarUrl).circleCrop().into(artistItemIv)
        }

    }

    override fun getItemCount(): Int = artistsList.size

    fun setArtistList(list: List<PopularArtist>) {
        this.artistsList = list.toMutableList()
        notifyItemRangeInserted(0, list.size - 1)
    }

    inner class TopArtistsViewHolder(
        val binding: ArtistListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.artistItemCc.setOnClickListener {
                val artist = artistsList[bindingAdapterPosition]
                goToArtistDetail(artist)
            }
        }

        private fun goToArtistDetail(artistItem: PopularArtist) {
            val artistBundle = bundleOf(
                "artistId" to artistItem.userId,
                "artistName" to artistItem.name
            )
            binding.root.findNavController().navigate(R.id.DetailFragment, artistBundle)
        }
    }
}