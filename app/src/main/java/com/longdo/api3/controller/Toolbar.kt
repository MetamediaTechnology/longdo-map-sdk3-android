package com.longdo.api3.controller

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.Composable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ControllerToolbar(state: ScaffoldState, scope: CoroutineScope) {
    TopAppBar(
        title = { Text(text = "Longdo Map API3") },
        navigationIcon = {
            IconButton(onClick = { scope.launch { state.drawerState.open() } }) {
                Icon(
                    imageVector = Icons.Rounded.Menu,
                    contentDescription = null
                )
            }
        }
    )
}