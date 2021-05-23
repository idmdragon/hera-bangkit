package com.hera.bangkit.data.repositories

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.hera.bangkit.data.NetworkBoundResource
import com.hera.bangkit.data.response.ReportEntity
import com.hera.bangkit.data.response.StoryResponse
import com.hera.bangkit.data.source.remote.RemoteDataSource
import com.idm.moviedb.data.source.remote.RemoteResponse
import com.idm.moviedb.vo.Resource
import javax.inject.Inject

class DefaultRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
) : DataSource {

    override fun insertStory(story: StoryResponse) {
        remoteDataSource.insertStory(story)
    }

    override fun getListStory(): LiveData<Resource<PagedList<StoryResponse>>> {
        TODO("Not yet implemented")
    }

    override fun insertReport(report: ReportEntity) {
        remoteDataSource.insertReport(report)
    }

    override fun getListReport(): LiveData<Resource<PagedList<ReportEntity>>> {
        return object : NetworkBoundResource<PagedList<ReportEntity>, ReportEntity>() {

            val listReportItems = ArrayList<ReportEntity>()

            override fun loadFromDB(): LiveData<PagedList<ReportEntity>> {
                TODO("Not yet implemented")
            }

            override fun shouldFetch(data: PagedList<ReportEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<RemoteResponse<ReportEntity>> {
                return remoteDataSource.getUserReport()
            }

            override fun saveCallResult(data: ReportEntity) {
                TODO("Not yet implemented")
            }
//            val listNowPlayingMovie = ArrayList<ReportEntity>()
//            override fun loadFromDB(): LiveData<PagedList<ReportEntity>> {
//                val config = PagedList.Config.Builder()
//                    .setInitialLoadSizeHint(10)
//                    .setPageSize(10)
//                    .build()
//                return LivePagedListBuilder(localDataSource.getMovieList(), config).build()
//            }
//
//            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
//                data == null || data.isEmpty()
//
//            public override fun createCall(): LiveData<ApiResponse<MovieResponse>> =
//                remoteDataSource.getNowPlaying()
//
//            override fun saveCallResult(data: MovieResponse) {
//                for (item in data.results) {
//                    val movieItem = MovieEntity(
//                        item.backdrop_path,
//                        0,
//                        genres = listOf(
//                            Genres(""),
//                        ),
//                        item.id,
//                        "",
//                        "",
//                        "",
//                        item.poster_path,
//                        item.release_date,
//                        0,
//                        "",
//                        "",
//                        item.title,
//                        item.vote_average,
//                        false
//
//                    )
//                    listNowPlayingMovie.add(movieItem)
//                }
//                CoroutineScope(Dispatchers.IO).launch {
//                    localDataSource.insertMovieList(listNowPlayingMovie)
//                }
//            }
        }.asLiveData()

    }
}