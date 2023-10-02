package com.example.temperatureconverter
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class converterViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    var celsiusValue: Double
        get() = savedStateHandle.get("celsiusValue") ?: 0.0
        set(value) {
            savedStateHandle.set("celsiusValue", value)
            savedStateHandle.set("fahrenheitValue", value * (9.00 / 5.00) + 32.00)
        }

    var fahrenheitValue: Double
        get() = savedStateHandle.get("fahrenheitValue") ?: 0.0
        set(value) {
            savedStateHandle.set("fahrenheitValue", value)
            savedStateHandle.set("celsiusValue", (value - 32.00) * (5.00 / 9.00))
        }

}
