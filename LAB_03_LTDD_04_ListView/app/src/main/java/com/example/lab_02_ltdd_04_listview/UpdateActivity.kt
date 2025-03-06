package com.example.lab_02_ltdd_04_listview

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lab_02_ltdd_04_listview.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var intent = intent
        var list = intent.getParcelableArrayListExtra<Cat>("list")
        var pos = intent.getIntExtra("pos",-1)

        binding.edtIdUpdate.setText(list?.get(pos)?.id)
        binding.edtNameUpdate.setText(list?.get(pos)?.name)
        when(list?.get(pos)?.image) {
            R.drawable.cat1 -> binding.radAva1.isChecked = true
            R.drawable.cat2 -> binding.radAva2.isChecked = true
            R.drawable.cat3 -> binding.radAva3.isChecked = true
        }

        binding.btnSubmit.setOnClickListener{
            if (binding.edtIdUpdate.text.isEmpty() || binding.edtNameUpdate.text.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show()
            }

            if (binding.radGroup.checkedRadioButtonId == -1) {
                Toast.makeText(this, "Vui lòng chọn một avatar!", Toast.LENGTH_SHORT).show()
            }

            var id = binding.edtIdUpdate.text.toString()
            var name = binding.edtNameUpdate.text.toString()
            // Xác định avatar
            val avatarRes = when (binding.radGroup.checkedRadioButtonId) {
                binding.radAva1.id -> R.drawable.cat1
                binding.radAva2.id -> R.drawable.cat2
                binding.radAva3.id -> R.drawable.cat3
                else -> R.drawable.ic_launcher_background // Tránh lỗi nếu có trường hợp ngoài ý muốn
            }

            list?.set(pos, list[pos].copy(image = avatarRes, id = id, name = name))
            var resultIntent = Intent()
            resultIntent.putParcelableArrayListExtra("list",list)
            setResult(Activity.RESULT_OK,resultIntent)
            finish()
        }
    }
}