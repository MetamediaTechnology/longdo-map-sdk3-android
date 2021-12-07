package com.longdo.api3.screen

import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.longdo.api3.LongdoMap
import com.longdo.api3.controller.*
import com.longdo.api3.core.LongdoMap
import com.longdo.api3.theme.AppTheme

class Main : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val map = longdoMap(this, "")
        setContent {
            val state = rememberScaffoldState()
            val scope = rememberCoroutineScope()
            val controller = viewModel<ControllerViewModel>()
            val zoom = controller.zoom.observeAsState()
            val location = controller.location.observeAsState()
            val rotate = controller.rotate.observeAsState()
            val pitch = controller.pitch.observeAsState()
            AppTheme {
                Scaffold(
                    scaffoldState = state,
                    topBar = { ControllerToolbar(state, scope) },
                    drawerContent = {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .height(64.dp)
                                .padding(start = 16.dp)
                        ) {
                            Text(
                                text = "Map",
                                fontSize = 30.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        AnimateCheckbox(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(64.dp)
                                .padding(start = 16.dp)
                        )
                        Divider()
                        ZoomMenuItem(state = state, scope = scope)
                        Divider()
                        LocationMenuItem(state = state, scope = scope)
                        Divider()
                        RotateMenuItem(state = state, scope = scope)
                        Divider()
                        PitchMenuItem(state = state, scope = scope)
                        Divider()
                    },
                    drawerGesturesEnabled = state.drawerState.isOpen,
                    snackbarHost = { Snackbar() }
                ) {
                    Box {
                        LongdoMap(map = map)
                        if (zoom.value == true) {
                            Column(
                                modifier = Modifier
                                    .align(Alignment.BottomEnd)
                                    .padding(
                                        end = 16.dp,
                                        bottom = 56.dp,
                                        start = 16.dp
                                    ),
                                horizontalAlignment = Alignment.End
                            ) {
                                ZoomInButton(map = map)
                                Spacer(modifier = Modifier.size(8.dp))
                                ZoomOutButton(map = map)
                                Spacer(modifier = Modifier.size(8.dp))
                                ZoomLevelTextField(map = map)
                                Spacer(modifier = Modifier.size(8.dp))
                                GetZoomButton(map = map)
                                Spacer(modifier = Modifier.size(8.dp))
                                ZoomRangeSlider(map = map)
                                Spacer(modifier = Modifier.size(8.dp))
                                GetZoomRangeButton(map = map)
                            }
                        }
                        if (location.value == true) {
                            ArrowGroupButton(
                                map = map,
                                modifier = Modifier
                                    .align(alignment = Alignment.BottomEnd)
                                    .padding(
                                        end = 16.dp,
                                        bottom = 56.dp,
                                        start = 16.dp
                                    )
                            )
                            LocationGroupView(
                                map = map,
                                modifier = Modifier
                                    .align(alignment = Alignment.Center)
                                    .padding(top = 16.dp)
                            )
                            BoundaryGroupView(
                                map = map,
                                modifier = Modifier
                                    .align(alignment = Alignment.TopCenter)
                                    .padding(top = 16.dp)
                            )
                        }
                        if (rotate.value == true) {
                            Column(
                                modifier = Modifier
                                    .align(alignment = Alignment.BottomCenter)
                                    .padding(
                                        horizontal = 16.dp,
                                        vertical = 56.dp
                                    ),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                RotateSlider(map = map)
                                Spacer(modifier = Modifier.size(8.dp))
                                GetRotateButton(map = map)
                            }
                        }
                        if (pitch.value == true) {
                            Column(
                                modifier = Modifier
                                    .align(alignment = Alignment.BottomCenter)
                                    .padding(
                                        horizontal = 16.dp,
                                        vertical = 56.dp
                                    ),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                PitchSlider(map = map)
                                Spacer(modifier = Modifier.size(8.dp))
                                GetPitchButton(map = map)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun longdoMap(
        context: Context,
        apiKey: String
    ) = LongdoMap(context).apply {
        layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        this.apiKey = apiKey
    }

}