package com.longdo.api3.controller

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ControllerViewModel : ViewModel() {

    val zoom = MutableLiveData(true)
    val location = MutableLiveData(false)
    val rotate = MutableLiveData(false)
    val pitch = MutableLiveData(false)

    private fun reset() {
        zoom.value = false
        location.value = false
        rotate.value = false
        pitch.value = false
    }

    fun zoom() {
        reset()
        zoom.value = true
    }

    fun location() {
        reset()
        location.value = true
    }

    fun rotate() {
        reset()
        rotate.value = true
    }

    fun pitch() {
        reset()
        pitch.value = true
    }

}