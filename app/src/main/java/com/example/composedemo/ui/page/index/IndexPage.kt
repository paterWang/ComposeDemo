package com.example.composedemo.ui.page.index


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.composedemo.bean.Article
import com.example.composedemo.bean.WebData
import com.example.composedemo.theme.HamShapes
import com.example.composedemo.theme.HamTheme
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState


@Composable
fun IndexPage(
    onSelected: (routeName: String, data: WebData) -> Unit,
    viewModel: IndexViewModel = viewModel(IndexViewModel::class.java)
) {
    viewModel.start()
    val homeData = viewModel.pagingData.value?.collectAsLazyPagingItems()
    val refreshing = viewModel.isRefreshing.observeAsState()
    val topArticle = viewModel.topArticles.observeAsState()
    val swipeRefreshState = rememberSwipeRefreshState(refreshing.value!!)
    SwipeRefresh(state = swipeRefreshState, onRefresh = {
        viewModel.isRefreshing.value = true
        viewModel.pagingData.value = null
        viewModel.start()
    }) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            contentPadding = PaddingValues(top = 10.dp)
        ) {
            if (homeData != null) {
                items(homeData) { indexBean ->
                    IndexItem(indexBean!!, onSelected = onSelected)
                }
            }
        }

    }


}

@Composable
fun IndexItem(
    indexBean: Article,
    isTop: Boolean = false,
    onSelected: (routeName: String, data: WebData) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .wrapContentWidth()
            .clickable {
                //onSelected(HamRouter.webView, webData)
            },
        shape = HamShapes.medium,
        backgroundColor = HamTheme.colors.onBadge
    ) {

        Box {


        }


    }

}
