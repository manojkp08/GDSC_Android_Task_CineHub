package com.devdreamerx.cinehub.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.devdreamerx.cinehub.model.Episode
import com.devdreamerx.cinehub.model.SearchSpecificShow
import com.devdreamerx.cinehub.model.Show
import com.devdreamerx.cinehub.model.ShowDAO
import com.devdreamerx.cinehub.network.ShowService
import kotlinx.coroutines.flow.Flow
import java.io.Serializable

class ShowsRepository(private val showService: ShowService, private val showDAO: ShowDAO) :
    ShowsRemoteDataSource, Serializable {

    override fun getShows(): Flow<PagingData<Show>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false,
                initialLoadSize = 249
            ),
            pagingSourceFactory = {
                AllShowsPagingSource(service = showService, showDAO = showDAO)
            }
        ).flow
    }

    suspend fun searchShow(query: String): List<SearchSpecificShow?> {
        return showService.searchSpecificShow(query)
    }

    suspend fun getEpisodes(id: String): List<Episode?> {
        return showService.getEpisodes(id)
    }
}