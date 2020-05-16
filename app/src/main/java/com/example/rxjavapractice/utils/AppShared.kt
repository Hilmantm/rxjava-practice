package com.example.rxjavapractice.utils

import com.example.rxjavapractice.network.TheMovieDBRepository

class AppShared {

    companion object {

        fun getDataRepository(): TheMovieDBRepository {
            if(this.dataRepository == null) {
                this.dataRepository = TheMovieDBRepository()
            }
            return this.dataRepository!!
        }

        private var dataRepository: TheMovieDBRepository? = null

    }

}