package com.example.composedemo.ui.page.index

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.composedemo.bean.Article
import com.example.composedemo.bean.BannerBean
import com.example.composedemo.bean.WebData
import com.example.composedemo.repository.HttpRepo
import com.example.composedemo.ui.page.base.BaseViewModel
import com.example.composedemo.ui.page.base.IViewContract
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion

class IndexViewModel @ViewModelInject constructor(
    private var repo: HttpRepo,
): BaseViewModel<BannerBean>(), IViewContract.IHomeView  {

    var pagingData = MutableLiveData<Flow<PagingData<Article>>>()
    var images = MutableLiveData(mutableListOf<String>())
    var isRefreshing = MutableLiveData(true)
    val topArticles = MutableLiveData(mutableListOf<Article>())

    override fun start() {
        if (pagingData.value==null) {
            pagingData.value = homeData()
            isRefreshing.value = pagingData.value==null
        }

    }

    //paging3分页加载
    private fun homeData() = repo.getIndexData()
        .cachedIn(viewModelScope)
        .catch { stopLoading() }
        .onCompletion {
            stopLoading()
        }

    override fun loadBanners() {

    }

    override fun loadTopArticles() {

    }
}