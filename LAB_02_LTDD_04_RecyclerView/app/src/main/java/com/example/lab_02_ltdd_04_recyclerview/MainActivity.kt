package com.example.lab_02_ltdd_04_recyclerview

import android.app.Activity
import android.app.Instrumentation.ActivityResult
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab_02_ltdd_04_recyclerview.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private val list = mutableListOf<Student>()
    private var selectedPosition: Int = -1 // Để theo dõi item đang chỉnh sửa
    private lateinit var customAdapter: CustomAdapter
    private var activityResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        // Xử lý kết quả nếu Activity trả về OK
        if (result.resultCode == Activity.RESULT_OK) {
            // Lấy dữ liệu từ Intent
            var intent = result.data

            var student = intent?.getParcelableExtra<Student>("Student_DATA")
            if (student != null) {
                list.add(student)
                customAdapter.notifyDataSetChanged() // Cập nhật RecyclerView
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener(this)
        binding.btnUpdate.setOnClickListener(this)

//      Khởi tạo adapter cho RecyclerView
        customAdapter = CustomAdapter(list, object : RecyclerInterface {
            override fun onClickStudent(position: Int) {
                selectedPosition = position
                binding.edtIdStudentMain.setText(list[position].id)
                binding.edtNameStudentMain.setText(list[position].name)
            }

            override fun onLongClickStudent(position: Int) :Boolean {
// Hiển thị cửa sổ xác nhận xóa
                var dialog = AlertDialog.Builder(this@MainActivity).apply {
//            Tiêu đề thông báo
                    setTitle("Xác nhận có muốn xóa ${list[position].id}")
//            Nội dung thông báo
                    setMessage("Bạn muốn xóa ${list[position].id} không ?")
//          Nút phủ định
                    setNegativeButton("No"){ dialogInterface: DialogInterface, i: Int ->
                        dialogInterface.dismiss() // đóng cửa sổ
                    }
//          Nút khẳng định
                    setPositiveButton("Yes"){ dialogInterface: DialogInterface, i: Int ->
                        list.removeAt(position)  // Xóa item khỏi danh sách
                        customAdapter.notifyDataSetChanged()  // Cập nhật ListView
                        Toast.makeText(this@MainActivity, "Đã xóa thành công!", Toast.LENGTH_SHORT).show()
                    }
                    show()
                }
                return true
            }
        })

        binding.rvManager.adapter = customAdapter
        binding.rvManager.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnAdd -> {
                var intent  = Intent(this@MainActivity, AddActivity::class.java)
                activityResultLauncher.launch(intent)
            }
            R.id.btnUpdate -> {
                if (binding.edtIdStudentMain.text.toString().isEmpty() ||
                    binding.edtNameStudentMain.text.toString().isEmpty()) {

                    Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show()
                } else {
                    if (selectedPosition != -1) {
                        list[selectedPosition] = Student(list[selectedPosition].image,
                            binding.edtIdStudentMain.text.toString(),
                            binding.edtNameStudentMain.text.toString())
                        customAdapter.notifyDataSetChanged()

                        binding.edtIdStudentMain.text.clear()
                        binding.edtNameStudentMain.text.clear()
                        Toast.makeText(this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}