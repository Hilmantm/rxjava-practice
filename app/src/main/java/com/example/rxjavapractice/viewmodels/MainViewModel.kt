package com.example.rxjavapractice.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rxjavapractice.base.BaseViewModel
import com.example.rxjavapractice.models.Movie
import com.example.rxjavapractice.models.Response
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel: BaseViewModel() {

    private val load: MutableLiveData<Boolean> = MutableLiveData()
    private val results: MutableLiveData<Response<Movie>> = MutableLiveData()
    private val error: MutableLiveData<String> = MutableLiveData()

    fun getPopularMovie() {
        dataRepository.getPopularMovie()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.single())
            .doOnError {it.printStackTrace()}
            .doOnTerminate {
                load.postValue(false)
            }
            .subscribe({
                results.postValue(it)
            }, {
                error.postValue(it.localizedMessage)
            })
    }

    fun getLoad(): LiveData<Boolean> = load

    fun getResults(): LiveData<Response<Movie>> = results

}