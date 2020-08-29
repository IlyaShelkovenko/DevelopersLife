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
    @GET("{gifCategory}/{page}?json=true")
    fun getGifs(@Path("gifCategory") gifCategory : String, @Path("page") page : Int = 0) : Deferred<ApiResponse>
}



