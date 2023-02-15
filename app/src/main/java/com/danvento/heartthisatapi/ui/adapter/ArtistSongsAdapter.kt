package com.danvento.heartthisatapi.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.danvento.heartthisatapi.data.model.ArtistSong
import com.danvento.heartthisatapi.databinding.SongListItemBinding
import java.util.*

class ArtistSongsAdapter(
    private val mediaUrlCallback: ((String) -> Unit)
) :
    RecyclerView.Adapter<ArtistSongsAdapter.ArtistSongsViewHolder>() {

    private var songsList = mutableListOf<ArtistSong>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistSongsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = SongListItemBinding.inflate(layoutInflater, parent, false)

        return ArtistSongsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArtistSongsViewHolder, position: Int) {
        val song = songsList[position]

        holder.binding.apply {
            songItemTitleTv.text = song.title
            songItemDurationTv.text = getFormattedDuration(song.duration)
            Glide.with(root.context).load(song.artworkUrl).into(songItemIv)
        }

    }

    override fun getItemCount(): Int = songsList.size

    fun setSongList(list: List<ArtistSong>) {
        this.songsList = list.toMutableList()
        notifyItemRangeInserted(0, list.size - 1)
    }

    private fun getFormattedDuration(duration: Int): String {
        return String.format(
            "%02dh %02dm %02ds",
            duration / 3600,
            duration / 3600 % 60,
            duration % 60,
        )
    }

    inner class ArtistSongsViewHolder(
        val binding: SongListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.songItemCc.setOnClickListener {
                val selectedSongURL = songsList[bindingAdapterPosition].streamUrl
                mediaUrlCallback.invoke(selectedSongURL)
            }
        }
    }
}