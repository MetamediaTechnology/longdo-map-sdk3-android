package com.longdo.api3.tutorial

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.longdo.api3.LongdoMapViewModel

@Composable
fun UIDPad() {
    val vm = viewModel<LongdoMapViewModel>()
    Button(onClick = {
        vm.call.value = mapOf(
            LongdoMapViewModel.METHOD to "Ui.DPad.visible",
            LongdoMapViewModel.ARGS to listOf(false)
        )
    }) {
        Text(text = "DPad")
    }
}

@Composable
fun UIZoombar() {
    val vm = viewModel<LongdoMapViewModel>()
    Button(onClick = {
        vm.call.value = mapOf(
            LongdoMapViewModel.METHOD to "Ui.Zoombar.visible",
            LongdoMapViewModel.ARGS to listOf(false)
        )
    }) {
        Text(text = "Zoombar")
    }
}

@Composable
fun UILayerSelector() {
    val vm = viewModel<LongdoMapViewModel>()
    Button(onClick = {
        vm.call.value = mapOf(
            LongdoMapViewModel.METHOD to "Ui.LayerSelector.visible",
            LongdoMapViewModel.ARGS to listOf(false)
        )
    }) {
        Text(text = "LayerSelector")
    }
}

@Composable
fun UIScale() {
    val vm = viewModel<LongdoMapViewModel>()
    Button(onClick = {
        vm.call.value = mapOf(
            LongdoMapViewModel.METHOD to "Ui.Scale.visible",
            LongdoMapViewModel.ARGS to listOf(false)
        )
    }) {
        Text(text = "Scale")
    }
}

@Composable
fun eventDrag(){
    val vm = viewModel<LongdoMapViewModel>()
    Button(onClick = {
        vm.call.value = mapOf(
            LongdoMapViewModel.METHOD to "Ui.Mouse.enableDrag",
            LongdoMapViewModel.ARGS to listOf(false)
        )

    }) {
        Text(text = "eventDrag")
    }
}

@Composable
fun SetBound(){
    val vm = viewModel<LongdoMapViewModel>()
    Button(onClick = {
        val mapArgs = listOf(
            mapOf(
                "minLon" to 100,
                "minLat" to 13,
                "maxLon" to 101,
                "maxLat" to 14
            )
        )

        vm.call.value = mapOf(
            LongdoMapViewModel.METHOD to "bound",
            LongdoMapViewModel.ARGS to mapArgs
        )
    }) {
        Text(text = "setBound")
    }
}

@Composable
fun GetBound(){
    val vm = viewModel<LongdoMapViewModel>()
    Button(onClick = {
        vm.call.value = mapOf(LongdoMapViewModel.METHOD to "bound")
    }) {
        Text(text = "getBound")
    }
}

@Composable
fun SetZoomRange(){
    val vm = viewModel<LongdoMapViewModel>()
    Button(onClick = {
        val zoomRangeArgs = listOf(
                mapOf(
                "min" to 1, "max" to 5
            )
        )

        vm.call.value = mapOf(
            LongdoMapViewModel.METHOD to "zoomRange",
            LongdoMapViewModel.ARGS to zoomRangeArgs
        )
    }) {
        Text(text = "setZoomRange")
    }
}
