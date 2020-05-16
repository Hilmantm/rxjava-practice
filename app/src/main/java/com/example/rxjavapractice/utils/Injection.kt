package com.example.rxjavapractice.utils

import com.example.rxjavapractice.viewmodels.MainViewModelFactory

object Injection {

    fun proviewMainViewModelFactory(): MainViewModelFactory {
        return MainViewModelFactory()
    }

}