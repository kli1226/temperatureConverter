package com.example.temperatureconverter
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class converterViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    var celsiusValue: Double
        get() = savedStateHandle.get("celsiusValue") ?: 0.0
        set(value) {
            savedStateHandle.set("celsiusValue", value)
        }

    var fahrenheitValue: Double
        get() = savedStateHandle.get("fahrenheitValue") ?: 0.0
        set(value) {
            savedStateHandle.set("fahrenheitValue", value)
        }
}
