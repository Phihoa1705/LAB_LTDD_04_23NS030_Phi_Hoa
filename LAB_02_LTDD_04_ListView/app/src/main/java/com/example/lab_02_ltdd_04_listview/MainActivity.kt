package com.example.lab_02_ltdd_04_listview

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.lab_02_ltdd_04_listview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener,AdapterView.OnItemClickListener,
AdapterView.OnItemLongClickListener{
    private lateinit var binding: ActivityMainBinding
    private lateinit var customAdapter: CustomAdapter
    private val list = mutableListOf<Cat>()
//    Biến lưu trữ địa chỉ ảnh
    private var selectedImageUri: Uri? = null
    private var selectedPosition: Int = -1 // Để theo dõi item đang chỉnh sửa

    // Đăng ký nhận kết quả từ AddActivity
    private val addActivityLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data
                val id = data?.getStringExtra("EXTRA_ID")
                val name = data?.getStringExtra("EXTRA_NAME")
                val imageUriString = data?.getStringExtra("EXTRA_IMAGE_URI") // Lấy chuỗi URI từ Intent
                val imageUri: Uri? = imageUriString?.let { Uri.parse(it) }   // Chuyển thành Uri

                if (id != null && name != null && imageUri != null ) {
                    list.add(Cat(imageUri, id, name))
                    customAdapter.notifyDataSetChanged() // Cập nhật ListView
                }
            }
        }

    private val imagePickerLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            if(uri != null) {
                selectedImageUri = uri
                // Hiển thị tên file thay vì Uri
                val fileName = selectedImageUri?.lastPathSegment ?: "Chưa chọn ảnh"
                binding.btnFindImageMain.text = fileName
            }
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener(this)
        binding.btnUpdate.setOnClickListener(this)
        binding.btnFindImageMain.setOnClickListener(this)

        // Khởi tạo adapter cho ListView
        customAdapter = CustomAdapter(this,list)
        binding.lvManager.adapter = customAdapter

//       Phương thức setOnItemClickListener cho ListView để lắng nghe sự kiện click vào từng item
        binding.lvManager.setOnItemClickListener(this)
//       Phương thức setOnItemLongClickListener cho ListView để lắng nghe sự kiện click và giữ vào từng item
        binding.lvManager.setOnItemLongClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btnAdd -> {
                var intent = Intent(this@MainActivity,AddActivity::class.java)
                addActivityLauncher.launch(intent)
            }
            R.id.btnUpdate -> {
                // Nếu dữ liệu đã được điền đầy đủ
                if (binding.edtIdMain.text.toString().isNotEmpty() &&
                    binding.edtNameMain.text.toString().isNotEmpty() &&
                    selectedImageUri != null &&
                    selectedPosition != -1
                ) {
                    // Cập nhật item đã chọn
                    list[selectedPosition] = Cat(selectedImageUri!!,
                        binding.edtIdMain.text.toString(),
                        binding.edtNameMain.text.toString())
                    customAdapter.notifyDataSetChanged()

                    // Xóa nội dung trong EditText và ảnh sau khi cập nhật
                    binding.edtIdMain.text.clear()
                    binding.edtNameMain.text.clear()
                    selectedImageUri = null
                    binding.btnFindImageMain.text = "Chọn ảnh"

                    Toast.makeText(this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.btnFindImageMain -> {
                imagePickerLauncher.launch("image/*") // Chỉ chọn ảnh
            }
        }
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        /*
            parent	    ListView chứa item được click
            view	    View của item được click
            position	Vị trí của item trong danh sách
            id	        ID của item trong Adapter
         */
        selectedPosition = position
        binding.edtIdMain.setText(list[position].id)
        binding.edtNameMain.setText(list[position].name)
        selectedImageUri = list[position].image

        // Hiển thị tên file thay vì Uri
        val fileName = selectedImageUri?.lastPathSegment ?: "Chưa chọn ảnh"
        binding.btnFindImageMain.text = fileName
    }

    override fun onItemLongClick(
        parent: AdapterView<*>?,
        view: View?,
        position: Int,
        id: Long
    ): Boolean {
        // Hiển thị cửa sổ xác nhận xóa
        var dialog = AlertDialog.Builder(this).apply {
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
        return true // Báo rằng sự kiện đã được xử lý
    }
}