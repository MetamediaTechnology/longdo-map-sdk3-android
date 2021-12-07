package com.longdo.api3.controller

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.longdo.api3.viewModel.LongdoMapViewModel

@Composable
fun MenuItem(
    onClick: () -> Unit,
    text: String
) {
    TextButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
    ) {
        Text(
            text = text,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Snackbar() {
    val vm = viewModel<LongdoMapViewModel>()
    val zoomLevel = vm.snackBar.observeAsState("")
    AnimatedVisibility(visible = zoomLevel.value != "") {
        Snackbar { Text(text = zoomLevel.value) }
    }
}