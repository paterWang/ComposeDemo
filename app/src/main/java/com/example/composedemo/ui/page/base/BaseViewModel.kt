package com.example.composedemo.ui.page.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

abstract class BaseViewModel<T> : ViewModel(),IViewContract {
    //数据列表
    var list = MutableLiveData(mutableListOf<T>())

    fun async(block: suspend ()-> Unit) {
        viewModelScope.launch {
            block()
        }
    }
    abstract fun start()
    override fun loadContent() {

    }

    override fun stopLoading() {

    }
}