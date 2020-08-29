/**
 * Created by Ilia Shelkovenko on 29.08.2020.
 */

package com.gmail.hostov47.developerslife.overview

import android.util.Log
import androidx.lifecycle.*
import com.gmail.hostov47.developerslife.data.remote.res.GifRes
import com.gmail.hostov47.developerslife.repositories.RootRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

enum class DevLifeApiStatus { LOADING, ERROR, DONE }

class GifCategoryViewModel(private val gifCategory: String) : ViewModel() {

    private val repository = RootRepository

    private var _listOfGifs = MutableLiveData<List<GifRes>>()
    val listOfGifs: LiveData<List<GifRes>>
        get() = _listOfGifs

    var currImageIndex: MutableLiveData<Int> = MutableLiveData<Int>(0)

    var currImageUrl = Transformations.map(listOfGifs) {
        //currImageDescription = it[currImageIndex.value!!].description
        it[currImageIndex.value!!].gifURL
    } as MutableLiveData

    /*var currImageDescription = Transformations.map(currImageIndex){
        listOfGifs.value?.get(it)?.description
    }*/

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _status = MutableLiveData<DevLifeApiStatus>()
    val status: LiveData<DevLifeApiStatus>
        get() = _status

    init {
        getGifs(gifCategory,0)
    }

    fun getGifs(gifCategory: String, page: Int){
        coroutineScope.launch {
            var getListOfImagesDeferred = repository.getAllGifs(gifCategory,page)
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

    fun handleForwardClick(){
        currImageIndex.value = currImageIndex.value?.inc()
        currImageUrl.value = listOfGifs.value?.get(currImageIndex.value!!)?.gifURL
    }
    fun handleBackClick() {
        currImageIndex.value = currImageIndex.value!!.dec()
        currImageUrl.value = listOfGifs.value?.get(currImageIndex.value!!)?.gifURL
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


}

class GifCategoryViewModelFactory(private val gifCategory: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(GifCategoryViewModel::class.java)){
            return GifCategoryViewModel(gifCategory) as T
        }
        throw  IllegalArgumentException("unknown ViewModel class")
    }
}
