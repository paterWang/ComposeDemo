package com.example.composedemo.http.paging

import com.example.composedemo.bean.Article
import com.example.composedemo.bean.ListWrapperBean
import com.example.composedemo.http.HttpService
import javax.inject.Inject

class IndexPagingSource @Inject constructor(private val apiService: HttpService) : BasePagingSource<Article>() {
    override suspend fun call(page: Int): ListWrapperBean<Article> = apiService.getIndexList(page)
}