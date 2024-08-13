package com.longdo.api3.tutorial

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.longdo.api3.LongdoMapViewModel

@Composable
fun ReverseGEO() {
    val vm = viewModel<LongdoMapViewModel>()
    Button(onClick = {
//        vm.call.value = mapOf(
//            LongdoMapViewModel.METHOD to "Search.address",
//            LongdoMapViewModel.ARGS to listOf(vm.call.value = mapOf(LongdoMapViewModel.METHOD to "location")
//        ))
    }) {
        Text(text = "ReverseGEO")
    }
}