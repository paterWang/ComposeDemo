package com.example.composedemo.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.composedemo.HamApp
import com.example.composedemo.R
import com.example.composedemo.theme.HamColors
import com.example.composedemo.theme.HamTheme
import com.example.composedemo.theme.white1

@Composable
fun TopTapBar(
    index: Int,
    tabTexts: MutableList<String>,
    modifier: Modifier = Modifier,
    bgColor: Color = HamTheme.colors.themeUi,
    contentColor: Color = Color.White,
    onTabSelected: ((index: Int) -> Unit)? = null
) {
    var offset: Float by remember { mutableStateOf(0f) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(bgColor)
            .horizontalScroll(state = rememberScrollState())
    ) {
        tabTexts.forEachIndexed { i, s ->

            Text(
                text = s,
                fontSize = if (index == i) 20.sp else 15.sp,
                fontWeight = if (index == i) FontWeight.SemiBold else FontWeight.Normal,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(horizontal = 10.dp)
                    .clickable {
                        if (onTabSelected != null) {
                            onTabSelected(i)
                        }
                    },
                color = contentColor

            )

        }

    }

}

@Composable
fun HamTabBar(
    title: String,
    rightTitle: String? = null,
    onBack: (() -> Unit)? = null,
    onSearch: (() -> Unit)? = null
) {

    Box {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(Color(HamApp.CONTEXT.resources.getColor(R.color.teal_200)))
        ) {

            val (titlew, leftIcon) = createRefs()
            Text(text = title,
                fontSize = 12.sp,
                color = white1
                ,
                modifier = Modifier.constrainAs(titlew) {
                    centerVerticallyTo(parent)
                    centerHorizontallyTo(parent)
                }

            )
            Icon(
                painter = painterResource(id = R.drawable.icon_back_white),
                contentDescription = "ccc",
                modifier = Modifier.height(20.dp).width(20.dp)
                    .constrainAs(leftIcon) {
                        centerVerticallyTo(parent)
                        start.linkTo(parent.start,margin = 5.dp)

                    }
                    .clickable(onClick = onBack!! ),

                )
        }
    }

}