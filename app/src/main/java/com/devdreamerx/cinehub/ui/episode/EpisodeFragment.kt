package com.devdreamerx.cinehub.ui.episode

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import com.bumptech.glide.Glide
import com.devdreamerx.cinehub.R
import com.devdreamerx.cinehub.databinding.EpisodeFragmentBinding
import com.devdreamerx.cinehub.model.Episode
import com.devdreamerx.cinehub.ui.mainactivity.MainActivityViewModel

class EpisodeFragment : Fragment() {

    private val mainViewModel: MainActivityViewModel by activityViewModels()

    private var _binding: EpisodeFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<EpisodeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = EpisodeFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getArgumentsEpisode()
        initializeObservers(view)
    }

    private fun initializeObservers(view: View) {
        observeEpisode(view)
        observeSearchView()
    }

    private fun observeSearchView() {
        mainViewModel.query.observe(viewLifecycleOwner) {
            if (hasSomethingToSearch(it)) {
                NavHostFragment.findNavController(this)
                    .navigate(R.id.action_episodeFragment_to_searchShowFragment)
            }
        }
    }

    private fun hasSomethingToSearch(it: String?) = !it.isNullOrBlank()

    private fun observeEpisode(view: View) {
        viewModel.episode.observe(viewLifecycleOwner) {episode ->
            episode?.let {
                setImage(episode, view)
                setSummary(episode)
                binding.tvEpisodeName.append(" ${episode.number}:\n${episode.name}")
                binding.tvSeasonNumber.append(" ${episode.season}")
            }
        }
    }

    private fun setSummary(episode: Episode) {
        episode.summary?.let { htmlSummary ->
            val summaryString = Html.fromHtml(htmlSummary, Html.FROM_HTML_MODE_COMPACT)
            binding.tvSummaryEpisode.append(summaryString)
        }
    }

    private fun setImage(episode: Episode, view: View) {
        episode.image?.let { image ->
            Glide.with(view)
                .load(image.original)
                .into(binding.ivBackgroundEpisode)
        }
    }

    private fun getArgumentsEpisode() {
        arguments?.get("episode")?.let { episode ->
            viewModel.setEpisode(episode as Episode)
        }
    }
}