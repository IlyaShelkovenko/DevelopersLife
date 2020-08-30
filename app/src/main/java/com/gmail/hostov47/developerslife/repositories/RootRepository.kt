package com.gmail.hostov47.developerslife.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.gmail.hostov47.developerslife.data.local.database.GifsDatabase
import com.gmail.hostov47.developerslife.data.local.domain.Gif
import com.gmail.hostov47.developerslife.data.remote.NetworkService
import com.gmail.hostov47.developerslife.data.remote.RestService
import com.gmail.hostov47.developerslife.data.remote.res.ApiResponse
import com.gmail.hostov47.developerslife.data.remote.res.GifRes
import com.gmail.hostov47.developerslife.extensions.*
import com.gmail.hostov47.developerslife.overview.DevLifeApiStatus
import com.gmail.hostov47.developerslife.overview.GifsType
import kotlinx.coroutines.*


class RootRepository(private val database: GifsDatabase, private var gifCategory : String) {
    private val api : RestService = NetworkService.api
    val status : MutableLiveData<DevLifeApiStatus> = MutableLiveData(DevLifeApiStatus.LOADING)
    val gifs: LiveData<List<Gif>> =
        when(gifCategory){
            GifsType.LATEST.apiTitle -> Transformations.map(database.gifDao.getLatestGifs()){
                it.fromLatestToDomainModel()
            }
            GifsType.TOP.apiTitle -> Transformations.map(database.gifDao.getTopGifs()){
                it.fromTopToDomainModel()
            }
            GifsType.HOT.apiTitle -> Transformations.map(database.gifDao.getHotGifs()){
                it.fromHotToDomainModel()
            }
            else -> Transformations.map(database.gifDao.getLatestGifs()){
                it.fromLatestToDomainModel()
            }
        }

    suspend fun loadGifs(gifCategory : String,page : Int = 0){
        withContext(Dispatchers.IO){
            status.postValue(DevLifeApiStatus.LOADING)
            var apiResponse = ApiResponse(mutableListOf<GifRes>(),0)
            try {
                apiResponse = api.getGifs(gifCategory, page).await()
                status.postValue(DevLifeApiStatus.DONE)
            }catch (e: Exception) {
                Log.d("GifCategoryViewModel","${e.message}")
                status.postValue(DevLifeApiStatus.ERROR)
            }

            when(gifCategory){
                GifsType.LATEST.apiTitle -> database.gifDao.insertAllLatest(apiResponse.result.asDatabaseModelLatest())
                GifsType.TOP.apiTitle -> database.gifDao.insertAllTop(apiResponse.result.asDatabaseModelTop())
                GifsType.HOT.apiTitle -> database.gifDao.insertAllHot(apiResponse.result.asDatabaseModelHot())
            }
        }
    }
}