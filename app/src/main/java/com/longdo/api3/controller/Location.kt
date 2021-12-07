package com.longdo.api3.controller

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material.icons.rounded.ArrowDropUp
import androidx.compose.material.icons.rounded.ArrowLeft
import androidx.compose.material.icons.rounded.ArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.longdo.api3.LongdoMap
import com.longdo.api3.viewModel.LongdoMapViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun LocationMenuItem(state: ScaffoldState, scope: CoroutineScope) {
    val controller = viewModel<ControllerViewModel>()
    MenuItem(
        onClick = {
            controller.location()
            scope.launch { state.drawerState.close() }
        },
        text = "Location"
    )
}

@Composable
fun TopMoveButton(map: LongdoMap, modifier: Modifier = Modifier) {
    FloatingActionButton(
        onClick = { map.move(y = -100.0) },
        backgroundColor = Color.Black,
        modifier = modifier
    ) {
        Icon(imageVector = Icons.Rounded.ArrowDropUp, contentDescription = null)
    }
}

@Composable
fun BottomMoveButton(map: LongdoMap, modifier: Modifier = Modifier) {
    FloatingActionButton(
        onClick = { map.move(y = 100.0) },
        backgroundColor = Color.Black,
        modifier = modifier
    ) {
        Icon(imageVector = Icons.Rounded.ArrowDropDown, contentDescription = null)
    }
}

@Composable
fun LeftMoveButton(map: LongdoMap, modifier: Modifier = Modifier) {
    FloatingActionButton(
        onClick = { map.move(x = -100.0) },
        backgroundColor = Color.Black,
        modifier = modifier
    ) {
        Icon(imageVector = Icons.Rounded.ArrowLeft, contentDescription = null)
    }
}

@Composable
fun RightMoveButton(map: LongdoMap, modifier: Modifier = Modifier) {
    FloatingActionButton(
        onClick = { map.move(x = 100.0) },
        backgroundColor = Color.Black,
        modifier = modifier
    ) {
        Icon(imageVector = Icons.Rounded.ArrowRight, contentDescription = null)
    }
}

@Composable
fun ArrowGroupButton(map: LongdoMap, modifier: Modifier = Modifier) {
    Box(modifier = modifier.size(size = 160.dp)) {
        TopMoveButton(map = map, modifier = Modifier.align(alignment = Alignment.TopCenter))
        BottomMoveButton(map = map, modifier = Modifier.align(alignment = Alignment.BottomCenter))
        LeftMoveButton(map = map, modifier = Modifier.align(alignment = Alignment.CenterStart))
        RightMoveButton(map = map, modifier = Modifier.align(alignment = Alignment.CenterEnd))
    }
}

@Composable
fun LonTextField() {
    val vm = viewModel<LongdoMapViewModel>()
    val lon = vm.lon.observeAsState("100.0")
    OutlinedTextField(
        value = lon.value,
        onValueChange = { vm.lon.value = it },
        label = { Text(text = "Lon") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier.width(120.dp)
    )
}

@Composable
fun LatTextField() {
    val vm = viewModel<LongdoMapViewModel>()
    val lat = vm.lat.observeAsState("16.0")
    OutlinedTextField(
        value = lat.value,
        onValueChange = { vm.lat.value = it },
        label = { Text(text = "Lat") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier.width(120.dp)
    )
}

@Composable
fun GoLocationButton(map: LongdoMap) {
    val vm = viewModel<LongdoMapViewModel>()
    Button(onClick = {
        map.location(
            lon = vm.lon.value?.toDouble() ?: 100.0,
            lat = vm.lat.value?.toDouble() ?: 16.0,
            animate = vm.animate.value ?: true
        )
    }) {
        Text(text = "GO")
    }
}

@Composable
fun GetLocationButton(map: LongdoMap) {
    val vm = viewModel<LongdoMapViewModel>()
    Button(onClick = {
        map.location { it?.run { vm.location(location = this) } }
    }) {
        Text(text = "GET")
    }
}

@Composable
fun LocationGroupView(map: LongdoMap, modifier: Modifier = Modifier) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        LonTextField()
        Spacer(modifier = modifier.size(16.dp))
        LatTextField()
        Spacer(modifier = modifier.size(16.dp))
        Column {
            GoLocationButton(map = map)
            Spacer(modifier = Modifier.size(8.dp))
            GetLocationButton(map = map)
        }
    }
}

@Composable
fun MinLonTextField() {
    val vm = viewModel<LongdoMapViewModel>()
    val minLon = vm.minLon.observeAsState("100.0")
    OutlinedTextField(
        value = minLon.value,
        onValueChange = { vm.minLon.value = it },
        label = { Text(text = "Min lon") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier.width(120.dp)
    )
}

@Composable
fun MinLatTextField() {
    val vm = viewModel<LongdoMapViewModel>()
    val minLat = vm.minLat.observeAsState("13.0")
    OutlinedTextField(
        value = minLat.value,
        onValueChange = { vm.minLat.value = it },
        label = { Text(text = "Min lat") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier.width(120.dp)
    )
}

@Composable
fun MaxLonTextField() {
    val vm = viewModel<LongdoMapViewModel>()
    val maxLon = vm.maxLon.observeAsState("101.0")
    OutlinedTextField(
        value = maxLon.value,
        onValueChange = { vm.minLon.value = it },
        label = { Text(text = "Max lon") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier.width(120.dp)
    )
}

@Composable
fun MaxLatTextField() {
    val vm = viewModel<LongdoMapViewModel>()
    val maxLat = vm.maxLat.observeAsState("14.0")
    OutlinedTextField(
        value = maxLat.value,
        onValueChange = { vm.maxLat.value = it },
        label = { Text(text = "Max lat") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier.width(120.dp)
    )
}

@Composable
fun BoundaryButton(map: LongdoMap) {
    val vm = viewModel<LongdoMapViewModel>()
    Button(onClick = {
        map.bound(
            minLon = vm.minLon.value?.toDouble() ?: 100.0,
            minLat = vm.minLat.value?.toDouble() ?: 13.0,
            maxLon = vm.maxLon.value?.toDouble() ?: 101.0,
            maxLat = vm.maxLat.value?.toDouble() ?: 14.0
        )
    }) {
        Text(text = "GO")
    }
}

@Composable
fun GetBoundaryButton(map: LongdoMap) {
    val vm = viewModel<LongdoMapViewModel>()
    Button(onClick = {
        map.bound { it?.run { vm.boundary(boundary = this) } }
    }) {
        Text(text = "GET")
    }
}

@Composable
fun BoundaryGroupView(map: LongdoMap, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            MinLonTextField()
            Spacer(modifier = modifier.size(16.dp))
            MinLatTextField()
            Spacer(modifier = modifier.size(16.dp))
            BoundaryButton(map = map)
        }
        Spacer(modifier = Modifier.size(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            MaxLonTextField()
            Spacer(modifier = modifier.size(16.dp))
            MaxLatTextField()
            Spacer(modifier = modifier.size(16.dp))
            GetBoundaryButton(map = map)
        }
    }
}