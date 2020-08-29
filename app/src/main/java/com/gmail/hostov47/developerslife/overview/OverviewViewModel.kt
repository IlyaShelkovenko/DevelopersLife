/**
 * Created by Ilia Shelkovenko on 29.08.2020.
 */

package com.gmail.hostov47.developerslife.overview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.gmail.hostov47.developerslife.data.remote.res.GifRes
import com.gmail.hostov47.developerslife.repositories.RootRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

enum class DevLifeApiStatus { LOADING, ERROR, DONE }

class OverviewViewModel : ViewModel() {

    private val repository = RootRepository

    private var _listOfGifs = MutableLiveData<List<GifRes>>()
    val listOfGifs : LiveData<List<GifRes>>
    get() = _listOfGifs

    private var currImageIndex = 0
    val currImageUrl = Transformations.map(listOfGifs ) {
        it[currImageIndex].gifURL
    }
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _status = MutableLiveData<DevLifeApiStatus>()
    val status: LiveData<DevLifeApiStatus>
        get() = _status

    init {
        getGifs(0)
    }

    fun getGifs(page: Int){
        coroutineScope.launch {
            var getListOfImagesDeferred = repository.getAllGifs()
            try {
                _status.value = DevLifeApiStatus.LOADING
                val apiResponse = getListOfImagesDeferred.await()
                _listOfGifs.value = apiResponse.result
                _status.value = DevLifeApiStatus.DONE
            } catch (e: Exception) {
                _status.value = DevLifeApiStatus.ERROR
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}