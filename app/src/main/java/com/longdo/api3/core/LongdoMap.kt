package com.longdo.api3.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.longdo.api3.LongdoMap

@Composable
fun LongdoMap(map: LongdoMap) {
    AndroidView(factory = {
        map.load()
        map
    })
}