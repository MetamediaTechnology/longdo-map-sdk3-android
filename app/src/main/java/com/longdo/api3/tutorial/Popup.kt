package com.longdo.api3.tutorial

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.longdo.api3.LongdoMapViewModel
import com.longdo.sdk3.LongdoMap

@Composable
fun AddPopup(
    button: String,
    args: List<Map<String, Any>>,
    id: Long = System.currentTimeMillis()
) {
    val vm = viewModel<LongdoMapViewModel>()
    Button(onClick = {
        vm.call.value = mapOf(
            LongdoMapViewModel.METHOD to "Overlays.add",
            LongdoMapViewModel.ARGS to listOf(LongdoMap.LongdoObject(
                type = "Popup",
                args = args,
            ))
        )
    }) {
        Text(text = button)
    }
}

@Composable
fun AddBasicPopup() {
    AddPopup(
        button = "Basic",
        args = listOf(
            mapOf(
                "lon" to 100.56,
                "lat" to 13.74
            ),mapOf(
                "title" to "Popup",
                "detail" to "Longdo Map",
                "visibleRange" to mapOf("min" to 7, "max" to 9),
                "draggable" to false
            ),
        ),
        id = 1
    )
}

@Composable
fun AddCustomPopup() {
    val vm = viewModel<LongdoMapViewModel>()
    Button(onClick = {
        vm.run.value =
            """
            var popup2 = new longdo.Popup({ lon: 100.58, lat: 14 },
            {
                title: 'Popup',
                detail: 'Popup detail...',
                loadDetail: updateDetail,
                size: { width: 200, height: 200 },
                closable: false
            });
            map.Overlays.add(popup2)
            function updateDetail(element) {
                setTimeout(function() {
                  element.innerHTML = 'Content changed';
                }, 1000);
            }
            """
    }) {
        Text(text = "CustomPopup")
    }
}

@Composable
fun AddHTMLPopup() {
    AddPopup(
        button = "HTML",
        args = listOf(
            mapOf(
                "lon" to 100.66,
                "lat" to 13.74
            ),mapOf(
                "html" to "<div style=\"background: #eeeeff;\">popup</div>"
            ),
        ),
        id = 3
    )
}