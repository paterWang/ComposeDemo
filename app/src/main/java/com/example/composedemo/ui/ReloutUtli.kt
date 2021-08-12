package com.example.composedemo.ui

import android.os.Parcelable
import com.example.composedemo.bean.WebData
import com.example.composedemo.util.Navigator
import kotlinx.android.parcel.Parcelize

sealed class Destination : Parcelable {

    //主页
    @Parcelize
    object Home : Destination()

    //搜索页
    @Parcelize
    object ArticleSearch : Destination()
 //搜索页
    @Parcelize
   data class  WebView(val webData: WebData) : Destination()


}

object HamRouter {
    const val articleSearch = "article_search"
    const val webView = "webview"
}

class RouteActions(navigator: Navigator<Destination>) {
    val selected: (String, Any?) -> Unit = { which, parent ->
        val destination = when (which) {
            HamRouter.articleSearch -> Destination.ArticleSearch
            HamRouter.webView -> Destination.WebView(parent as WebData)
            else -> Destination.Home
        }
        navigator.navigate(destination)

    }

    val backpass: (() -> Unit) = {
        navigator.back()
    }
}