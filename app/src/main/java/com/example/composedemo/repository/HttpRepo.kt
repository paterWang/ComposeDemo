package com.example.composedemo.repository

import androidx.paging.Pager
import androidx.paging.PagingData
import coil.request.Disposable
import com.blankj.utilcode.util.LogUtils
import com.example.composedemo.bean.*
import com.example.composedemo.http.HttpService
import com.example.composedemo.http.paging.IndexPagingSource
import com.example.composedemo.http.paging.PagingFactory
import com.example.composedemo.ui.page.index.IndexPage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.Dispatcher

import javax.inject.Inject

class HttpRepo @Inject constructor(private val apiService: HttpService) {

    suspend fun getBanners(): Flow<ListBean<BannerBean>> = flowable(apiService.getBanners())

    suspend fun getTopArticles(): Flow<ListBean<Article>> = flowable(apiService.getTopArticles())

    suspend fun getHotkeys(): Flow<ListBean<Hotkey>> = flowable(apiService.getHotkeys())

    suspend fun getSystemList(): Flow<ListBean<ParentBean>> = flowable(apiService.getSystemList())

    suspend fun getNavigationList(): Flow<ListBean<NaviWrapper>> = flowable(apiService.getNavigationList())

    suspend fun getPublicInformation(): Flow<ListBean<ParentBean>> = flowable(apiService.getPublicInformation())

    suspend fun getProjectCategory(): Flow<ListBean<ParentBean>> = flowable(apiService.getProjectCategory())

    suspend fun getWelfareData(page: Int): Flow<WelfareBean> =
        flowable(apiService.getWelfareList("福利", 20, page))

    private fun <T> flowable(data: T): Flow<T> = flow { emit(data) }.flowOn(Dispatchers.IO)

    fun getIndexData(): Flow<PagingData<Article>> {
        return Pager(PagingFactory().pagingConfig) {
            IndexPagingSource(apiService)
        }.flow
    }


}