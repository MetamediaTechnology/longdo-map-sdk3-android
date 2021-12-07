package com.longdo.api3.controller

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.longdo.api3.LongdoMap
import com.longdo.api3.viewModel.LongdoMapViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ZoomMenuItem(state: ScaffoldState, scope: CoroutineScope) {
    val controller = viewModel<ControllerViewModel>()
    MenuItem(
        onClick = {
            controller.zoom()
            scope.launch { state.drawerState.close() }
        },
        text = "Zoom"
    )
}

@Composable
fun ZoomInButton(map: LongdoMap) {
    val vm = viewModel<LongdoMapViewModel>()
    FloatingActionButton(
        onClick = { map.zoom(`in` = true, animate = vm.animate.value ?: true) },
        backgroundColor = Color.Black
    )
    { Icon(imageVector = Icons.Rounded.Add, contentDescription = null) }
}

@Composable
fun ZoomOutButton(map: LongdoMap) {
    val vm = viewModel<LongdoMapViewModel>()
    FloatingActionButton(
        onClick = { map.zoom(`in` = false, animate = vm.animate.value ?: true) },
        backgroundColor = Color.Black
    )
    { Icon(imageVector = Icons.Rounded.Remove, contentDescription = null) }
}

@Composable
fun ZoomLevelTextField(map: LongdoMap) {
    val vm = viewModel<LongdoMapViewModel>()
    val zoomLevel = vm.zoomLevel.observeAsState("")
    OutlinedTextField(
        value = zoomLevel.value,
        onValueChange = { vm.zoomLevel.value = it },
        label = { Text(text = "Zoom Level") },
        keyboardActions = KeyboardActions(onGo = {
            try {
                map.zoom(
                    level = (zoomLevel.value ?: "").toFloat(),
                    animate = vm.animate.value ?: true
                )
            } catch (_: NumberFormatException) {
            }
        }),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Go
        ),
        modifier = Modifier.width(120.dp)
    )
}

@Composable
fun GetZoomButton(map: LongdoMap) {
    val vm = viewModel<LongdoMapViewModel>()
    Button(onClick = { map.zoom { it?.run { vm.zoomLevel(this) } } }) {
        Text(text = "Get zoom level")
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ZoomRangeSlider(map: LongdoMap) {
    val vm = viewModel<LongdoMapViewModel>()
    val zoomRange = vm.zoomRange.observeAsState(1F..22F)
    Column {
        RangeSlider(
            values = zoomRange.value,
            valueRange = 1F..22F,
            steps = 20,
            onValueChange = { vm.zoomRange.value = it },
            onValueChangeFinished = {
                val zoomRange = vm.zoomRange.value
                map.zoomRange(
                    min = zoomRange?.start ?: 1F,
                    max = zoomRange?.endInclusive ?: 22F
                )
            }
        )
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(text = "1")
            Text(
                text = "22",
                modifier = Modifier.align(alignment = Alignment.BottomEnd)
            )
        }
    }
}

@Composable
fun GetZoomRangeButton(map: LongdoMap) {
    val vm = viewModel<LongdoMapViewModel>()
    Button(onClick = { map.zoomRange { it?.run { vm.zoomRange(this) } } }) {
        Text(text = "Get zoom range level")
    }
}