package com.gmail.hostov47.developerslife.repositories

import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import com.gmail.hostov47.developerslife.AppConfig
import com.gmail.hostov47.developerslife.data.remote.NetworkService
import com.gmail.hostov47.developerslife.data.remote.RestService
import com.gmail.hostov47.developerslife.data.remote.res.ApiResponse
import com.gmail.hostov47.developerslife.data.remote.res.GifRes
import kotlinx.coroutines.*


object RootRepository {
    private val api : RestService = NetworkService.api

    /*private val errHandler = CoroutineExceptionHandler { _, exception ->
        println("Caught $exception")
        exception.printStackTrace()
    }
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO + errHandler)

    fun getAllGifs(result : (gifs : List<GifRes>) -> Unit) {
        scope.launch {
            var resultList = mutableListOf<GifRes>()

            scope.launch {
                var page = 0
                var isInsert = false
                var apiResponse : ApiResponse = api.latest(page)
                resultList.addAll(apiResponse.result)
            }.join()
            result(resultList)
        }
    }*/
    fun getAllGifs(gifCategory : String,page : Int = 0) : Deferred<ApiResponse> {
        return api.getGifs(gifCategory, page)
    }
}