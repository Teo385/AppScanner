package com.example.appscanner

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.appscanner.databinding.ActivityResultScannerBinding

class ResultScannerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultScannerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultScannerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val textResultado = intent.getStringExtra(MainActivity.SCANNER_KEY)
        binding.result.text = textResultado
    }
}
