package com.example.composedemo.ui.page.webview

import android.annotation.SuppressLint
import android.content.Context
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.FrameLayout
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.*


import androidx.compose.ui.viewinterop.AndroidView
import com.example.composedemo.bean.WebData
import com.example.composedemo.widget.HamTabBar

@SuppressLint("UseCompatLoadingForDrawables")
@Composable
fun WebViewPage(
    webData: WebData,
    backPress: () -> Unit,
) {
    var ctrl: WebViewCtrl?  =null

    Box {
        AndroidView(factory = { context: Context ->
            FrameLayout(context).apply {
                layoutParams = FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT
                )

                val webView = WebView(context).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
                    )
                }

                addView(webView)
                ctrl = WebViewCtrl(this, webData.url.toString(), onWebCall = {

                })
                ctrl?.initSettings()

            }


        })
        HamTabBar("caaa",onBack = {
            backPress()
        })
    }


}