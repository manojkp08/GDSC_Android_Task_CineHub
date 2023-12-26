package com.devdreamerx.cinehub.ui.episode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devdreamerx.cinehub.model.Episode
import com.devdreamerx.cinehub.model.Image
import com.devdreamerx.cinehub.model.Links
import com.devdreamerx.cinehub.model.Rating
import com.devdreamerx.cinehub.model.Self

class EpisodeViewModel : ViewModel() {

    private val _episode = MutableLiveData<Episode>().apply { value = emptyEpisode }
    val episode: LiveData<Episode>
        get() = _episode

    fun setEpisode(episode: Episode) {
        this._episode.postValue(episode)
    }

    private val emptyEpisode = Episode(
        0,
        "",
        "",
        0,
        0,
        "",
        "",
        "",
        "",
        0,
        Rating(0.0),
        Image("", ""),
        "",
        Links(Self(""))
    )
}