package com.devdreamerx.cinehub.data

import androidx.paging.PagingData
import com.devdreamerx.cinehub.model.Show
import kotlinx.coroutines.flow.Flow

interface ShowsRemoteDataSource {
    fun getShows(): Flow<PagingData<Show>>
}
