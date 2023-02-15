package com.danvento.heartthisatapi.ui.view

import android.media.AudioAttributes
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.danvento.heartthisatapi.R
import com.danvento.heartthisatapi.databinding.FragmentDetailBinding
import com.danvento.heartthisatapi.ui.adapter.ArtistSongsAdapter
import com.danvento.heartthisatapi.ui.viewmodel.DetailFragmentViewModel
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: DetailFragmentViewModel by viewModel()

    private val songsAdapter = ArtistSongsAdapter { setSongURL(it) }
    private lateinit var exoPlayer: ExoPlayer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val artistId = arguments?.getString("artistId") ?: ""
        viewModel.getSongs(artistId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.songsModel.observe(viewLifecycleOwner) {
            binding.apply {
                mainFragmentRecycler.adapter = songsAdapter
                songsAdapter.setSongList(it)
                detailFramentProgressBar.visibility = View.GONE
            }
            configureMediaPlayer()
        }
    }

    /*
    The Media player should be set up in the MainActivity,
    So we have control over it in all screens.
    For now, the player keeps working even if you leave the fragment,
    but you won't be able to control it.
    It just will work until a new song is played
     */
    private fun configureMediaPlayer() {
        exoPlayer = ExoPlayer.Builder(requireContext()).build()
        exoPlayer.apply {
            val attributes = com.google.android.exoplayer2.audio.AudioAttributes.Builder().setUsage(C.USAGE_MEDIA)
                .setContentType(C.AUDIO_CONTENT_TYPE_MUSIC).build()
            setAudioAttributes(attributes, true)
        }
        binding.detailFragmentPlayer.player = exoPlayer

    }

    private fun setSongURL(url: String) {
        val songItem = MediaItem.fromUri(Uri.parse(url))
        exoPlayer.apply {
            setMediaItem(songItem)
            prepare()
            play()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}