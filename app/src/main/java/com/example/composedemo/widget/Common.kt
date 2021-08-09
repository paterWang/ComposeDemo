package com.example.composedemo.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composedemo.theme.HamTheme

@Composable
fun TopTapBar(
    index: Int,
    tabTexts: MutableList<String>,
    modifier: Modifier= Modifier,
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
