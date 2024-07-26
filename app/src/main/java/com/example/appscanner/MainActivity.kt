package com.example.appscanner

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.appscanner.databinding.ActivityMainBinding
import com.google.zxing.BinaryBitmap
import com.google.zxing.MultiFormatReader
import com.google.zxing.common.HybridBinarizer
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var scanLauncher: ActivityResultLauncher<ScanOptions>
    private lateinit var imagePickerLauncher: ActivityResultLauncher<Intent>

    companion object {
        const val SCANNER_KEY = "ResultScanner"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnScan.setOnClickListener { initScanner() }
        binding.btnScanImage.setOnClickListener { selectImageFromGallery() }

        scanLauncher = registerForActivityResult(ScanContract()) { result ->
            if (result.contents == null) {
                Toast.makeText(this, "Cancelado", Toast.LENGTH_LONG).show()
            } else {
                val textResultado = formatResult(result.contents)
                Toast.makeText(this, "El valor escaneado es: $textResultado", Toast.LENGTH_LONG).show()
                navigateToResult(textResultado)
            }
        }

        imagePickerLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val selectedImageUri: Uri? = data?.data
                if (selectedImageUri != null) {
                    scanImage(selectedImageUri)
                } else {
                    Toast.makeText(this, "No se seleccionó ninguna imagen", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun navigateToResult(textResultado: String) {
        val intent = Intent(this, ResultScannerActivity::class.java)
        intent.putExtra(SCANNER_KEY, textResultado)
        startActivity(intent)
    }

    private fun initScanner() {
        val options = ScanOptions().apply {
            setDesiredBarcodeFormats(ScanOptions.ONE_D_CODE_TYPES)
            setPrompt("Mateo el mejor")
            setCameraId(0)
            setBeepEnabled(true)
            setCaptureActivity(CaptureActivityPortrait::class.java)
            setOrientationLocked(false)
            setBarcodeImageEnabled(true)
        }
        scanLauncher.launch(options)
    }

    private fun selectImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        imagePickerLauncher.launch(intent)
    }

    private fun scanImage(imageUri: Uri) {
        try {
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, imageUri)
            val intArray = IntArray(bitmap.width * bitmap.height)
            bitmap.getPixels(intArray, 0, bitmap.width, 0, 0, bitmap.width, bitmap.height)

            val source = com.google.zxing.RGBLuminanceSource(bitmap.width, bitmap.height, intArray)
            val binaryBitmap = BinaryBitmap(HybridBinarizer(source))
            val reader = MultiFormatReader()
            val result = reader.decode(binaryBitmap)

            if (result != null) {
                val textResultado = formatResult(result.text)
                Toast.makeText(this, "El valor escaneado es: $textResultado", Toast.LENGTH_LONG).show()
                navigateToResult(textResultado)
            } else {
                Toast.makeText(this, "No se pudo escanear la imagen", Toast.LENGTH_LONG).show()
            }
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(this, "Error al escanear la imagen", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "No se pudo escanear la imagen", Toast.LENGTH_LONG).show()
        }
    }

    private fun formatResult(result: String): String {

        var formattedResult = result.trim()

        formattedResult = formattedResult.replace("\n", " ")

        val parts = formattedResult.split(":")


        if (parts.size >= 5) {
            val code = parts[0]
            val idNumber = parts[1]
            val uniqueId = parts[2]
            val issueDate = parts[3]
            val expiryDate = parts[4]
            val additionalData = parts[5]

            formattedResult = """
            Placa: $code
            Número de Identificación: $idNumber
            Cedula: $uniqueId
            Fecha de expedicion: $issueDate
            Fecha de Expiración: $expiryDate
            Datos Adicionales: $additionalData
        """.trimIndent()
        }

        return formattedResult
    }



}

