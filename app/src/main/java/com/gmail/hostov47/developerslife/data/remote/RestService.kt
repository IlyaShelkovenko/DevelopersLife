/**
 * Created by Ilia Shelkovenko on 13.07.2020.
 */
package com.gmail.hostov47.developerslife.data.remote

import com.gmail.hostov47.developerslife.data.remote.res.ApiResponse
import com.gmail.hostov47.developerslife.data.remote.res.GifRes
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestService {
    @GET("latest/{page}?json=true")
    fun latest(@Path("page") page : Int = 0) : Deferred<ApiResponse>

    @GET("hot/?json=true")
    suspend fun hot(@Query("page") page : Int = 0) : List<GifRes>

    @GET("top/?json=true")
    suspend fun top(@Query("") page : Int = 0) : List<GifRes>
}


