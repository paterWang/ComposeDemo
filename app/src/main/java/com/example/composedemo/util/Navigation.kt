package com.example.composedemo.util

import android.os.Parcelable
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.toMutableStateList

class Navigator<T : Parcelable> private constructor(
    initialBackStack: List<T>,
    backDispatcher: OnBackPressedDispatcher
) {
    constructor(
        inital: T,
        backDispatcher: OnBackPressedDispatcher
    ) : this(listOf(inital), backDispatcher)

    //退回栈
    private val backStack = initialBackStack.toMutableStateList()
    private val backCallBack = object : OnBackPressedCallback(canGoBack()) {
        override fun handleOnBackPressed() {
            back()
        }

    }.also {
        backDispatcher.addCallback(it)
    }
    val current: T get() = backStack.last()
     fun back() {
        backStack.removeAt(backStack.lastIndex)
        backCallBack.isEnabled = canGoBack()
    }

    private fun canGoBack(): Boolean = backStack.size > 1

    fun navigate(destination: T) {
        backStack += destination
        backCallBack.isEnabled = canGoBack()
    }

    companion object {
        fun <T : Parcelable> saver(backDispatcher: OnBackPressedDispatcher) =
            listSaver<Navigator<T>, T>(
                save = { navigator -> navigator.backStack.toList() },
                restore = { backstack -> Navigator(backstack, backDispatcher) }

            )

    }
}