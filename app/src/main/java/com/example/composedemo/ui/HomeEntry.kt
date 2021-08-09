package com.example.composedemo.ui

import androidx.activity.OnBackPressedDispatcher
import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.composedemo.theme.HamTheme
import com.example.composedemo.ui.page.home.HomePage
import com.example.composedemo.util.Navigator
import com.google.accompanist.pager.ExperimentalPagerApi


@Composable
fun HomeEntry(backDispatcher: OnBackPressedDispatcher) {
//记录当前状态
    val navigator: Navigator<Destination> = rememberSaveable(
        saver = Navigator.saver(backDispatcher)
    ) {
        Navigator(Destination.Home, backDispatcher)
    }
    val actions = remember(navigator) {
        RouteActions(navigator)
    }
    var homeIndex = remember { 0 }
    HamTheme {
        Crossfade(navigator.current) { destination ->
            when (destination) {
                Destination.Home -> HomePage(homeIndex,{homeIndex =it},actions)
//                Destination.ArticleSearch -> SearchPage(actions)
            }
        }
    }

}