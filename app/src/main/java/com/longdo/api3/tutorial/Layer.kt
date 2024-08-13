package com.longdo.api3.tutorial

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.longdo.api3.LongdoMapViewModel
import com.longdo.sdk3.LongdoMap

@Composable
fun SetLayer(
    button: String,
    name: String,
    args: Map<String, Any>
) {
    val vm = viewModel<LongdoMapViewModel>()
    Button(onClick = {
        vm.call.value = mapOf(
            LongdoMapViewModel.METHOD to "Layers.add",
            LongdoMapViewModel.ARGS to listOf(LongdoMap.LongdoObject(
                type = "Layer",
                args = listOf(name, args)
            ))
        )
    }) {
        Text(text = button)
    }
}

@Composable
fun SetWMSLayer() {
    SetLayer(
        button = "WMS",
        name = "roadnet2:Road_FGDS",
        args = mapOf(
            "type" to LongdoMap.LongdoStatic("LayerType", "WMS"),
            "url" to "https://apix.longdo.com/vector/test-tile.php",
            "zoomRange" to mapOf("min" to 1, "max" to 9),
            "refresh" to 180,
            "opacity" to 0.5,
            "weight" to 10,
            "bound" to mapOf(
                "minLon" to 100,
                "minLat" to 10,
                "maxLon" to 105,
                "maxLat" to 20
            )
        )
    )
}

@Composable
fun SetTMSLayer() {
    SetLayer(
        button = "TMS",
        name = "roadnet2:Road_FGDS@EPSG:900913@png",
        args = mapOf(
            "type" to LongdoMap.LongdoStatic("LayerType", "TMS"),
            "url" to "https://apix.longdo.com/vector/test-tile.php?tms=",
            "zoomOffset" to 0
        )
    )
}

@Composable
fun SetWMTSLayer() {
    SetLayer(
        button = "WMTS",
        name = "roadnet2:Road_FGDS",
        args = mapOf(
            "type" to LongdoMap.LongdoStatic("LayerType", "WMTS"),
            "url" to "https://apix.longdo.com/vector/test-tile.php",
            "srs" to "EPSG:900913",
            "tileMatrix" to "(z) => 'EPSG :900913:' + z"
        )
    )
}

@Composable
fun SetWMTSRESTLayer() {
    SetLayer(
        button = "WMTS REST",
        name = "bluemarble_terrain",
        args = mapOf(
            "type" to LongdoMap.LongdoStatic("LayerType", "WMTS_REST"),
            "url" to "https://ms.longdo.com/mapproxy/wmts",
            "srs" to "GLOBAL_WEBMERCATOR"
        )
    )
}

@Composable
fun SetClusterMarkerLayer() {
    val vm = viewModel<LongdoMapViewModel>()
    Button(onClick = {
        vm.call.value = mapOf(
            LongdoMapViewModel.METHOD to "Layers.add",
            LongdoMapViewModel.ARGS to listOf(LongdoMap.LongdoObject(
                type = "Layer",
                args = listOf(
                    mapOf(
                        "sources" to mapOf(
                            "earthquakes" to mapOf(
                                "type" to "geojson",
                                "data" to "https://maplibre.org/maplibre-gl-js/docs/assets/earthquakes.geojson",
                                "cluster" to true,
                                "clusterMaxZoom" to 14,
                                "clusterRadius" to 50,
                            ),
                        ),

                        "layers" to listOf(
                            mapOf(
                                "id" to "clusters",
                                "type" to "circle",
                                "source" to "earthquakes",
                                "filter" to listOf("has", "point_count"),
                                "paint" to mapOf(
                                    "circle-color" to listOf(
                                        "step",
                                        listOf("get", "point_count"),
                                        "#51bbd6",
                                        100,
                                        "#f1f075",
                                        750,
                                        "#f28cb1",
                                    ),
                                    "circle-radius" to listOf(
                                        "step", listOf("get", "point_count"), 20, 100, 30, 750, 40),
                                ),
                            ),

                            mapOf(
                                "id" to "cluster-count",
                                "type" to "symbol",
                                "source" to  "earthquakes",
                                "filter" to listOf("has", "point_count"),
                                "layout" to mapOf(
                                    "text-field" to "{point_count_abbreviated}",
                                    "text-font" to listOf("OCJ"),
                                    "text-size" to 12,
                                ),
                            ),
                            mapOf(
                                "id" to "unclustered-point",
                                "type" to "circle",
                                "source" to "earthquakes",
                                "filter" to listOf("!", listOf("has", "point_count")),
                                "paint" to mapOf(
                                    "circle-color" to "#11b4da",
                                    "circle-radius" to 4,
                                    "circle-stroke-width" to 1,
                                    "circle-stroke-color" to "#fff",
                                ),
                            ),
                        ),
                    )
                )
            ))
        )
    }) {
        Text(text = "ClusterMarker")
    }
}

@Composable
fun SetHeapMapLayer() {
    val vm = viewModel<LongdoMapViewModel>()
    Button(onClick = {
        vm.call.value = mapOf(
            LongdoMapViewModel.METHOD to "Layers.add",
            LongdoMapViewModel.ARGS to listOf(LongdoMap.LongdoObject(
                type = "Layer",
                args = listOf(
                    mapOf(
                        "sources" to mapOf(
                            "earthquakes" to mapOf(
                                "type" to "geojson",
                                "data" to "https://docs.mapbox.com/mapbox-gl-js/assets/earthquakes.geojson",
                            )
                        ),
                        "layers" to listOf(
                            mapOf(
                                "id" to "earthquakes-heat",
                                "type" to "heatmap",
                                "source" to "earthquakes",
                                "maxzoom" to 9,
                                "paint" to mapOf(
                                    "heatmap-weight" to listOf(
                                        "interpolate",
                                        listOf("linear"),
                                        listOf("get", "mag"),
                                        0,
                                        0,
                                        6,
                                        1,
                                    ),
                                    "heatmap-intensity" to listOf(
                                        "interpolate",
                                        listOf("linear"),
                                        listOf("zoom"),
                                        0,
                                        1,
                                        9,
                                        3,
                                    ),
                                    "heatmap-color" to listOf(
                                        "interpolate",
                                        listOf("linear"),
                                        listOf("heatmap-density"),
                                        0,
                                        "rgba(33,102,172,0)",
                                        0.2,
                                        "rgb(103,169,207)",
                                        0.4,
                                        "rgb(209,229,240)",
                                        0.6,
                                        "rgb(253,219,199)",
                                        0.8,
                                        "rgb(239,138,98)",
                                        1,
                                        "rgb(178,24,43)",
                                    ),
                                    "heatmap-radius" to listOf(
                                        "interpolate",
                                        listOf("linear"),
                                        listOf("zoom"),
                                        0,
                                        2,
                                        9,
                                        20,
                                    ),
                                    "heatmap-opacity" to listOf(
                                        "interpolate",
                                        listOf("linear"),
                                        listOf("zoom"),
                                        7,
                                        1,
                                        9,
                                        0,
                                    ),
                                )
                            ),
                            "waterway-label"
                        )
                    )
                )
            ))
        )
    }) {
        Text(text = "HeatMap")
    }
}