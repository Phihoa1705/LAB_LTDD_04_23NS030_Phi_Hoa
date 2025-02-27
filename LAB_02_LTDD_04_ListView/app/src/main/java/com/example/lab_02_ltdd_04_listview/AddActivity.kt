package com.example.lab_02_ltdd_04_listview

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lab_02_ltdd_04_listview.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding
    private var selectedImageUri: Uri? = null
    // Đăng ký lấy ảnh từ thư viện
    private val imagePickerLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            if (uri != null) {
                selectedImageUri = uri
//                Hiển thị tên file
                val fileName = selectedImageUri?.lastPathSegment ?: "Chưa chọn ảnh"
                binding.btnFindImage.text = fileName
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Xử lý chọn ảnh
        binding.btnFindImage.setOnClickListener {
            imagePickerLauncher.launch("image/*") // Chỉ chọn ảnh
        }

        binding.btnSubmit.setOnClickListener {
            val id = binding.edtId.text.toString()
            val name = binding.edtName.text.toString()

            val resultIntent = Intent()
            resultIntent.putExtra("EXTRA_ID", id)
            resultIntent.putExtra("EXTRA_NAME", name)
            resultIntent.putExtra("EXTRA_IMAGE_URI", selectedImageUri?.toString())

            setResult(Activity.RESULT_OK, resultIntent)
            finish() // Đóng AddActivity
        }
    }
}