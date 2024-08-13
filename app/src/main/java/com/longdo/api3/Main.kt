package com.longdo.api3

import android.app.Application
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import com.longdo.api3.tutorial.AddEndingPoint
import com.longdo.api3.tutorial.AddStartingPoint
import com.longdo.api3.tutorial.RouteSearch
import com.longdo.api3.ui.theme.Theme
import com.longdo.sdk3.LongdoMap
import org.json.JSONObject

class Main : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val message = remember { mutableStateOf("") }
            Theme(darkTheme = false) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.White)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
//                        SetWMSLayer()
//                        SetTMSLayer()
//                        SetWMTSLayer()
//                        SetWMTSRESTLayer()
//                        AddBasicMarker()
//                        AddBasicPopup()
//                        AddCustomPopup()
//                        AddHTMLPopup()
//                        SetClusterMarkerLayer()
//                        SetHeapMapLayer()
                        AddStartingPoint()
                        AddEndingPoint()
                        RouteSearch()
//                        ReverseGEO()
//                        SetZoomRange()
//                        UIDPad()
//                        UIZoombar()
//                        UILayerSelector()
//                        UIScale()
//                        _3DObject()
//                        eventDrag()
//                        SetBound()
//                        GetBound()
//                        SetZoomRange()
//                        RemoveMarker()
//                        ClearMarker()
//                        AddOption1Marker()
//                        AddOption2Marker()
//                        AddOption3Marker()
//                        AddOptionGeoJsonMarker()
//                        AddPolyline()
//                        AddDashline()
//                        AddPolygon()
//                        AddCircle()
//                        AddDot()
//                        AddDonut()
//                        AddRectangle()
//                        GetGeometryArea()
//                        GetFormatDistancePolyline()
//                        HoverEffect()
                    }
                    LongdoMapView(message)
                }
            }
            if (message.value.isNotBlank()) {
                Toast
                    .makeText(
                        LocalContext.current,
                        message.value,
                        Toast.LENGTH_SHORT
                    )
                    .show()
                message.value = ""
            }

        }
    }

}

@Composable
fun LongdoMapView(message: MutableState<String>) {
    val vm = viewModel<LongdoMapViewModel>()
    val const = vm.const.observeAsState().value
    val call = vm.call.observeAsState().value
    val objectCall = vm.objectCall.observeAsState().value
    val run = vm.run.observeAsState().value
    AndroidView(
        factory = {
            LongdoMap(it).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT,
                )
            }
        },
        update = {
            const?.run {
                LongdoMap.API_KEY = get("apiKey")
                LongdoMap.PACKAGE_NAME = get("packageName")
//                LongdoMap.LAYER = LongdoMap.LongdoStatic("Layers", "TRAFFIC")
                LongdoMap.ZOOM = 10
                LongdoMap.ZOOM_RANGE = JSONObject()
                    .put("min", 1)
                    .put("max", 20)
                LongdoMap.LOCATION = JSONObject()
                    .put("lon", 100.54)
                    .put("lat", 13.73)
//                LongdoMap.UI = LongdoMap.LongdoStatic("UiComponent", "None")
                LongdoMap.LAST_VIEW = true
                LongdoMap.LANGUAGE = "th"
                it.load()
                vm.const.value = null
            }
            call?.run {
                it.call(
                    get(LongdoMapViewModel.METHOD)!! as String,
                    get(LongdoMapViewModel.ARGS) as List<Any?>
                ) {
                    message.value = it
                }
                vm.call.value = null
            }
            objectCall?.run {
                it.objectCall(
                    get(LongdoMapViewModel.OBJECT).let { it as JSONObject },
                    get(LongdoMapViewModel.METHOD)!! as String,
                    get(LongdoMapViewModel.ARGS) as List<Any?>
                ) {
                    message.value = it
                }
                vm.objectCall.value = null
            }
            run?.run {
                it.run(this)
                vm.run.value = null
            }
        }
    )
}

class LongdoMapViewModel(app: Application) : AndroidViewModel(app) {

    companion object {

        const val OBJECT = "OBJECT"
        const val METHOD = "METHOD"
        const val ARGS = "ARGS"

    }

    val const = MutableLiveData(
        mapOf(
            "apiKey" to "",
            "packageName" to app.packageName
        )
    )
    val call = MutableLiveData<Map<String, Any>>()
    val objectCall = MutableLiveData<Map<String, Any>>()
    val run = MutableLiveData<String>()

}