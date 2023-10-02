package com.example.temperatureconverter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.viewModels
import com.example.temperatureconverter.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: converterViewModel by viewModels()
    lateinit var celsiusSlider : SeekBar
    lateinit var celsiusValueText : TextView
    lateinit var fahrenheitSlider : SeekBar
    lateinit var fahrenheitValueText : TextView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        celsiusSlider = binding.celsiusSeekBar
        celsiusValueText = binding.celsiusValue
        fahrenheitSlider = binding.fahrenheitSeekBar
        fahrenheitValueText = binding.fahrenheitValue

        celsiusSlider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(celsiusSeekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                var celsiusDouble = progress.toDouble()
                viewModel.celsiusValue = celsiusDouble
                celsiusValueText.text = celsiusDouble.toString() + "°C"
                celsiusToFahrenheit(celsiusDouble)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        fahrenheitSlider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(celsiusSeekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                var fahrenheitDouble = progress.toDouble()
                viewModel.fahrenheitValue = fahrenheitDouble
                fahrenheitValueText.text = fahrenheitDouble.toString() + "°F"
                fahrenheitToCelsius(fahrenheitDouble)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
    }

    private fun fahrenheitToCelsius(fahrenheit: Double) {
        if (fahrenheit < 32.00) {
            fahrenheitSlider.progress = 32
            Snackbar.make(binding.root, R.string.warmer_toast, Snackbar.LENGTH_SHORT).show()
        } else {
            val celsius = (fahrenheit - 32.00) * (5.00 / 9.00)
            celsiusSlider.progress = celsius.toInt()
            if (celsius <= 20.00) {
                Snackbar.make(binding.root, R.string.warmer_toast, Snackbar.LENGTH_SHORT).show()
            } else {
                Snackbar.make(binding.root, R.string.colder_toast, Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun celsiusToFahrenheit(celsius: Double) {
        val fahrenheit = celsius * (9.00 / 5.00) + 32.00
        fahrenheitSlider.progress = fahrenheit.toInt()
        if (celsius <= 20.00) {
            Snackbar.make(binding.root, R.string.warmer_toast, Snackbar.LENGTH_SHORT).show()
        } else {
            Snackbar.make(binding.root, R.string.colder_toast, Snackbar.LENGTH_SHORT).show()
        }
    }
}