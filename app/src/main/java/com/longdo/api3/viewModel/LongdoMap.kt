package com.longdo.api3.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.longdo.api3.Boundary
import com.longdo.api3.Location
import com.longdo.api3.Range
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LongdoMapViewModel : ViewModel() {

    val snackBar = MutableLiveData("")
    val animate = MutableLiveData(true)
    val zoomLevel = MutableLiveData("")
    val zoomRange = MutableLiveData(1F..22F)
    val lon = MutableLiveData("100.0")
    val lat = MutableLiveData("16.0")
    val minLon = MutableLiveData("100.0")
    val minLat = MutableLiveData("13.0")
    val maxLon = MutableLiveData("101.0")
    val maxLat = MutableLiveData("14.0")
    val rotate = MutableLiveData(0F)
    val pitch = MutableLiveData(0F)

    fun snackBar(text: String, delay: Long = 1000L) {
        this@LongdoMapViewModel.snackBar.value = text
        viewModelScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.Default) { delay(delay) }
            this@LongdoMapViewModel.snackBar.value = ""
        }
    }

    fun zoomLevel(zoomLevel: Float) {
        val level = String.format("%.1f", zoomLevel)
        snackBar("Zoom Level :: $level")
    }

    fun zoomRange(zoomRange: Range) {
        val min = String.format("%.0f", zoomRange.min)
        val max = String.format("%.0f", zoomRange.max)
        snackBar("Zoom Range :: min: $min, max: $max")
    }

    fun location(location: Location) {
        val lon = String.format("%.5f", location.lon)
        val lat = String.format("%.5f", location.lat)
        snackBar(text = "Location :: lon: $lon, lat: $lat", delay = 3000L)
    }

    fun boundary(boundary: Boundary) {
        val minLon = String.format("%.5f", boundary.minLon)
        val minLat = String.format("%.5f", boundary.minLat)
        val maxLon = String.format("%.5f", boundary.maxLon)
        val maxLat = String.format("%.5f", boundary.maxLat)
        snackBar(text = "Location :: minLon: $minLon, minLat: $minLat, maxLon :$maxLon, maxLat: $maxLat", delay = 3000L)
    }

    fun rotate(rotate: Float) {
        val rotate = String.format("%.1f", rotate)
        snackBar("Rotate :: $rotate")
    }

    fun pitch(pitch: Float) {
        val pitch = String.format("%.1f", pitch)
        snackBar("Pitch :: $pitch")
    }

}