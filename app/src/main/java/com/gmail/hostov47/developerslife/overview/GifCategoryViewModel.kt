/**
 * Created by Ilia Shelkovenko on 29.08.2020.
 */

package com.gmail.hostov47.developerslife.overview

import androidx.lifecycle.*
import com.gmail.hostov47.developerslife.App
import com.gmail.hostov47.developerslife.data.local.database.getDatabase
import com.gmail.hostov47.developerslife.data.local.domain.Gif
import com.gmail.hostov47.developerslife.repositories.RootRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*

enum class DevLifeApiStatus { LOADING, ERROR, DONE }

class GifCategoryViewModel(private val gifCategory: String) : ViewModel() {
    private val database = getDatabase(App.applicationContext())
    private val repository = RootRepository(database, gifCategory)
    private var page = 0

    val listOfGifs = repository.gifs

    private var _currImageIndex = MutableLiveData<Int>(0)
    val currImageIndex: LiveData<Int>
        get() = _currImageIndex


    val sortedListFromDb = Transformations.map(listOfGifs){
        if(it.isNotEmpty()) {
            val list = mutableListOf(*listOfGifs.value?.toTypedArray()!!)
            Collections.sort(list, Collections.reverseOrder())
            list
        }else
            mutableListOf()
    }
    var currImageUrl = Transformations.map(sortedListFromDb) {
        if(it.isNotEmpty()) {
            it[_currImageIndex.value!!].gifURL
        }else ""

    } as MutableLiveData


    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _status = repository.status
    val status: LiveData<DevLifeApiStatus>
        get() = _status

    init {
        viewModelScope.launch {
            repository.loadGifs(gifCategory,page)
        }

    }

    fun handleForwardClick(){
        _currImageIndex.value = _currImageIndex.value?.inc()
        currImageUrl.value = sortedListFromDb.value?.get(_currImageIndex.value!!)?.gifURL
        if(currImageIndex.value == listOfGifs.value?.size?.minus(1)){
            viewModelScope.launch {
                repository.loadGifs(gifCategory, ++page)
            }
        }
    }
    fun handleBackClick() {
        _currImageIndex.value = _currImageIndex.value!!.dec()
        currImageUrl.value = sortedListFromDb.value?.get(_currImageIndex.value!!)?.gifURL
    }

    fun handleRepeatDownLoadClick() {
        viewModelScope.launch {
            repository.loadGifs(gifCategory,        page)
        }
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


