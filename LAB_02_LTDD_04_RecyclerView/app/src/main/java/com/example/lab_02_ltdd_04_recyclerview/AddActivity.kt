package com.example.lab_02_ltdd_04_recyclerview

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lab_02_ltdd_04_recyclerview.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity(),View.OnClickListener {
    lateinit var binding: ActivityAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSubmit.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btnSubmit -> {
                if (binding.edtIdStudentAdd.text.isEmpty() || binding.edtNameStudentAdd.text.isEmpty()) {
                    Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show()
                    return // Thoát ngay hàm onClick nếu thiếu thông tin
                }

                if (binding.radGroup.checkedRadioButtonId == -1) {
                    Toast.makeText(this, "Vui lòng chọn một avatar!", Toast.LENGTH_SHORT).show()
                    return // Thoát ngay hàm onClick nếu thiếu thông tin
                }

                // Xác định avatar
                val avatarRes = when (binding.radGroup.checkedRadioButtonId) {
                    binding.radAva1.id -> R.drawable.ava1
                    binding.radAva2.id -> R.drawable.ava2
                    binding.radAva3.id -> R.drawable.ava3
                    else -> R.drawable.ic_launcher_background // Tránh lỗi nếu có trường hợp ngoài ý muốn
                }

                // Tạo đối tượng Student
                val student = Student(
                    avatarRes,
                    binding.edtIdStudentAdd.text.toString(),
                    binding.edtNameStudentAdd.text.toString()
                )

                var resultIntent = Intent()
                resultIntent.putExtra("Student_DATA",student)
                setResult(Activity.RESULT_OK,resultIntent)
                finish() // Kết thúc Activity và trả kết quả về
            }
        }
    }
}