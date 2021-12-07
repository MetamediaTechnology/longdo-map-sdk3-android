package com.longdo.api3.controller

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.longdo.api3.LongdoMap
import com.longdo.api3.viewModel.LongdoMapViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun RotateMenuItem(state: ScaffoldState, scope: CoroutineScope) {
    val controller = viewModel<ControllerViewModel>()
    MenuItem(
        onClick = {
            controller.rotate()
            scope.launch { state.drawerState.close() }
        },
        text = "Rotate"
    )
}

@Composable
fun RotateSlider(map: LongdoMap) {
    val vm = viewModel<LongdoMapViewModel>()
    val zoomRange = vm.rotate.observeAsState(0F)
    Column {
        Slider(
            value = zoomRange.value,
            valueRange = -180F..180F,
            steps = 7,
            onValueChange = { vm.rotate.value = it },
            onValueChangeFinished = { map.rotate(rotate = vm.rotate.value ?: 0F) }
        )
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(text = "-180")
            Text(
                text = "180",
                modifier = Modifier.align(alignment = Alignment.BottomEnd)
            )
        }
    }
}

@Composable
fun GetRotateButton(map: LongdoMap, modifier: Modifier = Modifier) {
    val vm = viewModel<LongdoMapViewModel>()
    Button(
        onClick = {
            map.rotate { it?.run { vm.rotate(rotate = this) } }
        },
        modifier = modifier
    ) {
        Text(text = "Get rotate")
    }
}