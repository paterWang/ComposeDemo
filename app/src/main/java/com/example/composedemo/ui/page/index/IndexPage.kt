package com.example.composedemo.ui.page.index


import android.text.TextUtils

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.composedemo.R
import com.example.composedemo.bean.Article
import com.example.composedemo.bean.WebData
import com.example.composedemo.theme.HamShapes
import com.example.composedemo.theme.HamTheme
import com.example.composedemo.theme.Teal200
import com.example.composedemo.theme.white1
import com.example.composedemo.ui.HamRouter
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
val webData = WebData(indexBean.title,indexBean.apkLink)
                onSelected(HamRouter.webView, webData)
            },
        shape = HamShapes.medium,
        backgroundColor = HamTheme.colors.onBadge
    ) {

        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(white1)
                    .padding(20.dp)
            ) {
                val (fistText, twoText, timeIcon, timeText, title, flagList, share, follow) = createRefs()
                Text(
                    text = getFirstCharFromName(data = indexBean),
                    modifier = Modifier
                        .width(20.dp)

                        .height(20.dp)
                        .background(Teal200, shape = RoundedCornerShape(20.dp / 2))
                        .padding(vertical = 1.dp)
                        .constrainAs(fistText) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                        },
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp,
                    color = white1
                )


                Text(
                    text = getAuthorName(indexBean),
                    fontSize = 12.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .constrainAs(twoText) {
                            top.linkTo(parent.top)
                            start.linkTo(fistText.end)
                        }
                        .padding(start = 5.dp)

                )
                Text(text = indexBean.niceDate + "",
                    fontSize = 12.sp,
                    maxLines = 1,
                    modifier = Modifier.constrainAs(timeText) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    }
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_time), contentDescription = "",
                    modifier = Modifier
                        .height(10.dp)
                        .width(10.dp)
                        .constrainAs(timeIcon) {
                            top.linkTo(parent.top, margin = 4.dp)
                            end.linkTo(timeText.start, margin = 3.dp)
                        }
                )
                Text(
                    text = indexBean.title.toString(),
                    modifier = Modifier.constrainAs(title) {
                        top.linkTo(fistText.bottom,margin = 5.dp)
                        start.linkTo(parent.start)
                    },
                    fontSize = 13.sp,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )

                Icon(painter = painterResource(id = R.drawable.ic_share), contentDescription = "",
                    modifier = Modifier.constrainAs(share) {
                        top.linkTo(title.bottom,margin = 5.dp)
                        end.linkTo(follow.start,margin = 5.dp)


                    }
                )
                Icon(painter = painterResource(id = R.drawable.ic_star), contentDescription = "",
                    modifier = Modifier.constrainAs(follow) {
                        top.linkTo(title.bottom,margin = 5.dp)
                        end.linkTo(parent.end,margin = 5.dp)


                    }
                )

            }


        }


    }

}

fun getAuthorName(data: Article): String {
    val emptyAuthor = "SuperHam"
    return if (TextUtils.isEmpty(data.author)) {
        data.shareUser ?: emptyAuthor
    } else {
        data.author ?: emptyAuthor
    }
}

fun getFirstCharFromName(data: Article): String {
    return getAuthorName(data).trim().substring(0, 1)
}
