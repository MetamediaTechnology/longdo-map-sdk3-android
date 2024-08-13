package com.longdo.api3.tutorial

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.longdo.api3.LongdoMapViewModel
import com.longdo.sdk3.LongdoMap

@Composable
fun AddPolyline() {
    val vm = viewModel<LongdoMapViewModel>()
    Button(onClick = {
        vm.call.value = mapOf(
            LongdoMapViewModel.METHOD to "Overlays.add",
            LongdoMapViewModel.ARGS to listOf(LongdoMap.LongdoObject(
                type = "Polyline",
                args = listOf(
                    listOf(
                        mapOf(
                            "lon" to 100,
                            "lat" to 14
                        ),
                        mapOf(
                            "lon" to 101,
                            "lat" to 15
                        ),
                        mapOf(
                            "lon" to 102,
                            "lat" to 14
                        )
                    ),
                    mapOf(
                        "title" to "Polyline",
                        "detail" to "-",
                        "label" to "Polyline",
                        "lineWidth" to 4,
                        "lineColor" to "rgba(255, 0, 0, 0.8)"
                    )
                )
            )
        ))
    }) {
        Text(text = "Polyline")
    }
}

@Composable
fun AddDashline() {
    val vm = viewModel<LongdoMapViewModel>()
    Button(onClick = {
        vm.call.value = mapOf(
            LongdoMapViewModel.METHOD to "Overlays.add",
            LongdoMapViewModel.ARGS to LongdoMap.LongdoObject(
                type = "Polyline",
                args = listOf(
                    listOf(
                        mapOf(
                            "lon" to 99,
                            "lat" to 14
                        ),
                        mapOf(
                            "lon" to 100,
                            "lat" to 15
                        ),
                        mapOf(
                            "lon" to 101,
                            "lat" to 14
                        )
                    ),
                    mapOf(
                        "title" to "Dashline",
                        "detail" to "-",
                        "label" to "Dashline",
                        "lineWidth" to 4,
                        "lineColor" to "rgba(255, 0, 0, 0.8)",
                        "lineStyle" to LongdoMap.LongdoStatic("LineStyle", "Dashed"),
                        "pointer" to true
                    )
                )
            )
        )
    }) {
        Text(text = "Dashline")
    }
}

@Composable
fun AddPolygon() {
    val vm = viewModel<LongdoMapViewModel>()
    Button(onClick = {
        vm.call.value = mapOf(
            LongdoMapViewModel.METHOD to "Overlays.add",
            LongdoMapViewModel.ARGS to LongdoMap.LongdoObject(
                type = "Polygon",
                args = listOf(
                    listOf(
                        mapOf(
                            "lon" to 99,
                            "lat" to 14
                        ),
                        mapOf(
                            "lon" to 100,
                            "lat" to 13
                        ),
                        mapOf(
                            "lon" to 102,
                            "lat" to 13
                        ),
                        mapOf(
                            "lon" to 103,
                            "lat" to 14
                        )
                    ),
                    mapOf(
                        "title" to "Polygon",
                        "detail" to "-",
                        "label" to "Polygon",
                        "lineWidth" to 2,
                        "lineColor" to "rgba(0, 0, 0, 1)",
                        "fillColor" to "rgba(255, 0, 0, 0.4)",
                        "visibleRange" to mapOf(
                            "min" to 5,
                            "max" to 18
                        ),
                        "editable" to true,
                        "weight" to LongdoMap.LongdoStatic("OverlayWeight", "Top")
                    )
                )
            )
        )
    }) {
        Text(text = "Polygon")
    }
}

@Composable
fun AddCircle() {
    val vm = viewModel<LongdoMapViewModel>()
    Button(onClick = {
        vm.call.value = mapOf(
            LongdoMapViewModel.METHOD to "Overlays.add",
            LongdoMapViewModel.ARGS to LongdoMap.LongdoObject(
                type = "Circle",
                args = listOf(
                    mapOf(
                        "lon" to 101,
                        "lat" to 15
                    ),
                    1,
                    mapOf(
                        "title" to "Geom 3",
                        "detail" to "-",
                        "lineWidth" to 2,
                        "lineColor" to "rgba(255, 0, 0, 0.8)",
                        "fillColor" to "rgba(255, 0, 0, 0.4)",
                    )
                )
            )
        )
    }) {
        Text(text = "Circle")
    }
}

@Composable
fun AddDot() {
    val vm = viewModel<LongdoMapViewModel>()
    Button(onClick = {
        vm.call.value = mapOf(
            LongdoMapViewModel.METHOD to "Overlays.add",
            LongdoMapViewModel.ARGS to LongdoMap.LongdoObject(
                type = "Dot",
                args = listOf(
                    mapOf(
                        "lon" to 100.540574,
                        "lat" to 13.714765
                    ),
                    mapOf(
                        "lineWidth" to 20,
                        "draggable" to true
                    )
                )
            )
        )
    }) {
        Text(text = "Dot")
    }
}

@Composable
fun AddDonut() {
    val vm = viewModel<LongdoMapViewModel>()
    Button(onClick = {
        vm.call.value = mapOf(
            LongdoMapViewModel.METHOD to "Overlays.add",
            LongdoMapViewModel.ARGS to LongdoMap.LongdoObject(
                type = "Polygon",
                args = listOf(
                    listOf(
                        mapOf(
                            "lon" to 101,
                            "lat" to 15
                        ),
                        mapOf(
                            "lon" to 105,
                            "lat" to 15
                        ),
                        mapOf(
                            "lon" to 103,
                            "lat" to 12
                        ),
                        null,
                        mapOf(
                            "lon" to 103,
                            "lat" to 14.9
                        ),
                        mapOf(
                            "lon" to 102.1,
                            "lat" to 13.5
                        ),
                        mapOf(
                            "lon" to 103.9,
                            "lat" to 13.5
                        )
                    ),
                    mapOf(
                        "label" to true,
                        "clickable" to true
                    )
                )
            )
        )
    }) {
        Text(text = "Donut")
    }
}

@Composable
fun AddRectangle() {
    val vm = viewModel<LongdoMapViewModel>()
    Button(onClick = {
        vm.call.value = mapOf(
            LongdoMapViewModel.METHOD to "Overlays.add",
            LongdoMapViewModel.ARGS to LongdoMap.LongdoObject(
                type = "Rectangle",
                args = listOf(
                    mapOf(
                        "lon" to 100.617,
                        "lat" to 13.896
                    ),
                    mapOf(
                        "width" to 2,
                        "height" to 1
                    ),
                    mapOf(
                        "editable" to true
                    )
                )
            )
        )
    }) {
        Text(text = "Rectangle")
    }
}

@Composable
fun GetGeometryArea() {
    val vm = viewModel<LongdoMapViewModel>()
    Button(onClick = {
        vm.objectCall.value = mapOf(
            LongdoMapViewModel.OBJECT to LongdoMap.LongdoObject(
                type = "Polygon",
                args = listOf(
                    listOf(
                        mapOf(
                            "lon" to 99,
                            "lat" to 14
                        ),
                        mapOf(
                            "lon" to 100,
                            "lat" to 13
                        ),
                        mapOf(
                            "lon" to 102,
                            "lat" to 13
                        ),
                        mapOf(
                            "lon" to 103,
                            "lat" to 14
                        )
                    )
                )
            ),
            LongdoMapViewModel.METHOD to "size"
        )
    }) {
        Text(text = "Geometry Area")
    }
}

@Composable
fun GetFormatDistancePolyline() {
    val vm = viewModel<LongdoMapViewModel>()
    Button(onClick = {
        vm.objectCall.value = mapOf(
            LongdoMapViewModel.OBJECT to LongdoMap.LongdoObject(
                type = "Polyline",
                args = listOf(
                    listOf(
                        mapOf(
                            "lon" to 100,
                            "lat" to 14
                        ),
                        mapOf(
                            "lon" to 101,
                            "lat" to 15
                        ),
                        mapOf(
                            "lon" to 102,
                            "lat" to 14
                        )
                    ),
                    mapOf(
                        "title" to "Polyline",
                        "detail" to '-',
                        "label" to "Polyline",
                        "lineWidth" to 4,
                        "lineColor" to "rgba(255, 0, 0, 0.8)"
                    )
                )
            ),
            LongdoMapViewModel.METHOD to "size",
            LongdoMapViewModel.ARGS to "'th'"
        )
    }) {
        Text(text = "Format Distance Polyline")
    }
}
