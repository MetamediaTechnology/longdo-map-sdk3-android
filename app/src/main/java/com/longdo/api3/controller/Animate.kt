package com.longdo.api3.controller

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.longdo.api3.viewModel.LongdoMapViewModel

@Composable
fun AnimateCheckbox(modifier: Modifier = Modifier) {
    val vm = viewModel<LongdoMapViewModel>()
    val animate = vm.animate.observeAsState(true)
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Checkbox(
            checked = animate.value,
            colors = CheckboxDefaults.colors(checkedColor = Color.Black),
            onCheckedChange = { vm.animate.value = it }
        )
        Text(
            text = "Animate",
            color = Color.Black,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(start = 8.dp)
        )
    }
}