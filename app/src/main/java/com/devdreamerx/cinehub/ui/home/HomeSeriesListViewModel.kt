package com.devdreamerx.cinehub.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.devdreamerx.cinehub.data.ShowsRepository
import com.devdreamerx.cinehub.model.Show
import com.devdreamerx.cinehub.util.singleArgViewModelFactory
import kotlinx.coroutines.flow.Flow

class HomeSeriesListViewModel(private val repository: ShowsRepository) : ViewModel() {

    companion object {
        val FACTORY = singleArgViewModelFactory(::HomeSeriesListViewModel)
    }

    fun refreshShowList(): Flow<PagingData<Show>> {
        return repository.getShows().cachedIn(viewModelScope)
    }
}