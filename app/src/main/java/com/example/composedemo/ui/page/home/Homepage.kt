package com.example.composedemo.ui.page.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composedemo.theme.HamTheme
import com.example.composedemo.theme.green3
import com.example.composedemo.theme.white1
import com.example.composedemo.R
import com.example.composedemo.ui.RouteActions
import com.example.composedemo.ui.page.index.IndexPage
import com.example.composedemo.widget.TopTapBar
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomePage(
    homeIndex: Int = 0,
    onPageSelected: (position: Int) -> Unit,
    actions: RouteActions
) {
    Scaffold(
        modifier = Modifier.background(HamTheme.colors.background),
        content = {
            Column {
                searchBar()

                val titles = mutableListOf(" 首页", "广场", "知识体系", "导航", "公众号", "项目", "问答")
                val pagerState = rememberPagerState(titles.size, homeIndex)
                val scopeState = rememberCoroutineScope()
                TopTapBar(
                    index = pagerState.currentPage,
                    tabTexts = titles,
                ) { index ->
                    scopeState.launch {
                        pagerState.scrollToPage(index)
                    }

                }
                HorizontalPager(state = pagerState) { page ->
                    //LogUtils.e("当前HomeIndex = ${pagerState.currentPage}")
                    onPageSelected(pagerState.currentPage)
                    when (page) {
                        0->{ IndexPage(onSelected = actions.selected)}

                    }
                }
            }
        }
    )

}


@Composable
fun searchBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(HamTheme.colors.themeUi)
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .height(25.dp)
                .align(alignment = Alignment.Top)
                .weight(1f)
                .background(
                    color = white1,
                    shape = RoundedCornerShape(12.5.dp)
                )
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(5.dp)
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_search_24),
                contentDescription = "搜索",
                tint = HamTheme.colors.themeUi,
                modifier = Modifier
                    .size(25.dp)
                    .padding(horizontal = 5.dp)
                    .align(Alignment.CenterVertically)
            )
        }
    }

}