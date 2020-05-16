package com.example.rxjavapractice.base

import androidx.lifecycle.ViewModel
import com.example.rxjavapractice.utils.AppShared

abstract class BaseViewModel: ViewModel() {
    val dataRepository = AppShared.getDataRepository()
}