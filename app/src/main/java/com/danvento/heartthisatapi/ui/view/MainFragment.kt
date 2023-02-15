package com.danvento.heartthisatapi.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.danvento.heartthisatapi.databinding.FragmentMainBinding
import com.danvento.heartthisatapi.ui.adapter.TopArtistsAdapter
import com.danvento.heartthisatapi.ui.viewmodel.MainFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: MainFragmentViewModel by viewModel()

    private val artistsAdapter = TopArtistsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getArtists()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            mainFragmentSwipeRefresh.isRefreshing = true
            mainFragmentSwipeRefresh.setOnRefreshListener {
                viewModel.getArtists()
            }
        }

        viewModel.artistsModel.observe(viewLifecycleOwner) {
            binding.apply {
                mainFragmentRecycler.adapter = artistsAdapter
                artistsAdapter.setArtistList(it)
                mainFragmentSwipeRefresh.isRefreshing = false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}