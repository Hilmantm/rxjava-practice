package com.example.rxjavapractice.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rxjavapractice.base.BaseViewModel
import com.example.rxjavapractice.models.Movie
import com.example.rxjavapractice.models.Response
import com.example.rxjavapractice.utils.ErrorHandler
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
                error.postValue(ErrorHandler.handleError(it))
            })
    }

    fun getLoad(): LiveData<Boolean> = load

    fun getError(): LiveData<String> = error

    fun getResults(): LiveData<Response<Movie>> = results

}