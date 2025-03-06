package com.example.lab_02_ltdd_04_listview

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lab_02_ltdd_04_listview.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSubmit.setOnClickListener {
            if (binding.edtIdAdd.text.isEmpty() || binding.edtNameAdd.text.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show()
            }

            if (binding.radGroup.checkedRadioButtonId == -1) {
                Toast.makeText(this, "Vui lòng chọn một avatar!", Toast.LENGTH_SHORT).show()
            }

            var id = binding.edtIdAdd.text.toString()
            var name = binding.edtNameAdd.text.toString()
            // Xác định avatar
            val avatarRes = when (binding.radGroup.checkedRadioButtonId) {
                binding.radAva1.id -> R.drawable.cat1
                binding.radAva2.id -> R.drawable.cat2
                binding.radAva3.id -> R.drawable.cat3
                else -> R.drawable.ic_launcher_background // Tránh lỗi nếu có trường hợp ngoài ý muốn
            }

            var cat = Cat(avatarRes,id,name)

            var resultIntent = Intent()
            resultIntent.putExtra("DATA_CAT",cat)
            setResult(Activity.RESULT_OK,resultIntent)
            finish()
        }
    }
}