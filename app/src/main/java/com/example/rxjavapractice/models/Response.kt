package com.example.rxjavapractice.models

data class Response<Model>(
    val page: Int? = null,
    val total_results: Int? = null,
    val total_pages: Int? = null,
    val results: List<Model>? = listOf()
)