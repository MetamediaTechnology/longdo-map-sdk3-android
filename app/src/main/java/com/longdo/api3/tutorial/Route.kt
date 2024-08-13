package com.longdo.api3.tutorial

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.longdo.api3.LongdoMapViewModel
import com.longdo.sdk3.LongdoMap

@Composable
fun AddStartingPoint() {
    val vm = viewModel<LongdoMapViewModel>()
    Button(onClick = {
        vm.call.value = mapOf(
            LongdoMapViewModel.METHOD to "Route.add",
            LongdoMapViewModel.ARGS to listOf(
                LongdoMap.LongdoObject(
                    type = "Marker",
                    args = listOf(
                        mapOf(
                            "lon" to 100.538316,
                            "lat" to 13.764953
                        ),
                        mapOf(
                            "title" to "Victory monument",
                            "detail" to "I'm here"
                        )
                    )
                )
            )
        )
    }) {
        Text(text = "Start")
    }
}

@Composable
fun AddEndingPoint() {
    val vm = viewModel<LongdoMapViewModel>()
    Button(onClick = {
        vm.call.value = mapOf(
            LongdoMapViewModel.METHOD to "Route.add",
            LongdoMapViewModel.ARGS to listOf(
                LongdoMap.LongdoObject(
                    type = "Marker",
                    args = listOf(
                        mapOf(
                            "lon" to 100,
                            "lat" to 15
                        )
                    )
                )
            )
        )
    }) {
        Text(text = "End")
    }
}

@Composable
fun RouteSearch() {
    val vm = viewModel<LongdoMapViewModel>()
    Button(onClick = {
        vm.call.value = mapOf(
            LongdoMapViewModel.METHOD to "Route.search",
            LongdoMapViewModel.ARGS to listOf<Any>(),
        )
    }) {
        Text(text = "getRoute")
    }
}