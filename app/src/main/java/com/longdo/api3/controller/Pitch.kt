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
fun PitchMenuItem(state: ScaffoldState, scope: CoroutineScope) {
    val controller = viewModel<ControllerViewModel>()
    MenuItem(
        onClick = {
            controller.pitch()
            scope.launch { state.drawerState.close() }
        },
        text = "Pitch"
    )
}

@Composable
fun PitchSlider(map: LongdoMap) {
    val vm = viewModel<LongdoMapViewModel>()
    val pitch = vm.pitch.observeAsState(0F)
    Column {
        Slider(
            value = pitch.value,
            valueRange = 0F..60F,
            steps = 7,
            onValueChange = { vm.pitch.value = it },
            onValueChangeFinished = { map.pitch(pitch = vm.pitch.value ?: 0F) }
        )
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(text = "0")
            Text(
                text = "60",
                modifier = Modifier.align(alignment = Alignment.BottomEnd)
            )
        }
    }
}

@Composable
fun GetPitchButton(map: LongdoMap, modifier: Modifier = Modifier) {
    val vm = viewModel<LongdoMapViewModel>()
    Button(
        onClick = {
            map.pitch { it?.run { vm.pitch(pitch = this) } }
        },
        modifier = modifier
    ) {
        Text(text = "Get pitch")
    }
}