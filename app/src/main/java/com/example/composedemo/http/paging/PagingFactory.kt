package com.example.composedemo.http.paging

import androidx.paging.PagingConfig

class PagingFactory {
    val pagingConfig = PagingConfig(
        //页数大小
        pageSize = 20,
        // 开启占位符
        enablePlaceholders = true,
        //预刷新的距离，距离最后一个item的距离进行加载数据
        prefetchDistance = 4,
        //初始化加载数据，默认为pagesize3
        initialLoadSize = 20


    )
}