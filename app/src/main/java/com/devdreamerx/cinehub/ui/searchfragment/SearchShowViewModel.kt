package com.devdreamerx.cinehub.ui.searchfragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devdreamerx.cinehub.data.ShowsRepository
import com.devdreamerx.cinehub.model.SearchSpecificShow
import com.devdreamerx.cinehub.util.singleArgViewModelFactory


class SearchShowViewModel(private val repository: ShowsRepository) : ViewModel() {

    companion object {
        val FACTORY = singleArgViewModelFactory(::SearchShowViewModel)
    }

    var listGroup = MutableLiveData<List<SearchSpecificShow?>>()
    suspend fun updateShows(query: String) {
        listGroup.postValue(repository.searchShow(query))
    }
}