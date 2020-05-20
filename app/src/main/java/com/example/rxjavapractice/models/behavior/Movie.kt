package com.example.rxjavapractice.models.behavior

object Movie {

    fun getOverview(overview: String): String {
        return overview.split(".")[0]
    }

}