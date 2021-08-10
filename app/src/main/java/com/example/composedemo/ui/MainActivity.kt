package com.example.composedemo.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import com.example.composedemo.ui.page.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeEntry(onBackPressedDispatcher)
        }

    }
}