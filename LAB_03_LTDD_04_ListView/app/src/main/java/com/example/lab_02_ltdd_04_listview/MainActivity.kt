package com.example.lab_02_ltdd_04_listview

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.lab_02_ltdd_04_listview.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), View.OnClickListener,AdapterView.OnItemClickListener,
AdapterView.OnItemLongClickListener{
    private lateinit var binding: ActivityMainBinding
    private lateinit var customAdapter: CustomAdapter
    private lateinit var homeFAB: FloatingActionButton
    private lateinit var list:ArrayList<Cat>
    private var selectedPosition: Int = -1 // Để theo dõi item đang chỉnh sửa

    // Đăng ký nhận kết quả từ AddActivity
    private var addActivityLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                var data = result.data
                var cat = data?.getParcelableExtra<Cat>("DATA_CAT")

                if (cat != null) {
                    list.add(cat)
                    customAdapter.notifyDataSetChanged() // Cập nhật ListView
                    Log.d("MainActivity","Đã được add item thứ " + list.size)
                }
            }
        }

    private var updateActivityLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                var data = result.data
                var list = data?.getParcelableArrayListExtra<Cat>("list")

                if (list != null) {
                    customAdapter = CustomAdapter(this,list)
                    binding.lvManager.adapter = customAdapter
                }
            }
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        Khởi tạo list
        list = ArrayList()

        binding.btnAdd.setOnClickListener(this)
        binding.btnUpdate.setOnClickListener(this)
        binding.btnRoot.setOnClickListener(this)

        // Khởi tạo adapter cho ListView
        customAdapter = CustomAdapter(this,list)
        binding.lvManager.adapter = customAdapter

//       Phương thức setOnItemClickListener cho ListView để lắng nghe sự kiện click vào từng item
        binding.lvManager.setOnItemClickListener(this)
//       Phương thức setOnItemLongClickListener cho ListView để lắng nghe sự kiện click và giữ vào từng item
        binding.lvManager.setOnItemLongClickListener(this)

        // Đăng ký Context Menu cho ListView
        registerForContextMenu(binding.lvManager)
//      Bottom Navigation View
        binding.navBottom.selectedItemId = R.id.navMainAc

        binding.navBottom.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.navMainAc -> {
                    true
                }
                R.id.navAc2 -> {
                    var intent = Intent(this@MainActivity,MainActivity2::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.navAc3 -> {
                    var intent = Intent(this@MainActivity,MainActivity3::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
            }
            false
        }

//        FloatingActionButton
        homeFAB = binding.fabHome
        homeFAB.setOnClickListener {
            Toast.makeText(this@MainActivity,"Click on Add Circle Button",Toast.LENGTH_SHORT).show()
        }

//      Navigation Drawer
        binding.navLeftMenu.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.navMyAccount->{
                    Toast.makeText(this@MainActivity,"My Account Click",Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.navSettings->{
                    Toast.makeText(this@MainActivity,"Setting Click",Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.navLogout->{
                    Toast.makeText(this@MainActivity,"Logout Click",Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }

//    Khởi tạo ContextMenu
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.menu, menu) // Tạo menu từ file XML
    }
//      Xử lý sự kiện item ContextMenu
    override fun onContextItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.mnuAdd->{
                var intent = Intent(this@MainActivity,AddActivity::class.java)
                addActivityLauncher.launch(intent)
            }
            R.id.mnuUpdate->{
                if (selectedPosition != -1){
                    var intent = Intent(this@MainActivity,UpdateActivity::class.java)
                    intent.putParcelableArrayListExtra("list",ArrayList(list))
                    intent.putExtra("pos",selectedPosition)
                    updateActivityLauncher.launch(intent)
                } else {
                    Toast.makeText(this@MainActivity,"Vui lòng chọn một item",Toast.LENGTH_SHORT).show()
                    return false // ngừng xử lý nếu không có item được chọn.
                }
            }
            R.id.mnuRoot->{
                list.add(Cat(R.drawable.cat1,"meo1","mie"))
                list.add(Cat(R.drawable.cat2,"meo2","nie"))
                list.add(Cat(R.drawable.cat3,"meo3","kie"))
                customAdapter.notifyDataSetChanged() // Cập nhật ListView
            }
        }
        return super.onContextItemSelected(item)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btnAdd -> {
                var intent = Intent(this@MainActivity,AddActivity::class.java)
                addActivityLauncher.launch(intent)
            }
            R.id.btnUpdate -> {
                if (selectedPosition != -1){
                    var intent = Intent(this@MainActivity,UpdateActivity::class.java)
                    intent.putParcelableArrayListExtra("list",ArrayList(list))
                    intent.putExtra("pos",selectedPosition)
                    updateActivityLauncher.launch(intent)
                } else {
                    Toast.makeText(this@MainActivity,"Vui lòng chọn một item",Toast.LENGTH_SHORT).show()
                    return
                }
            }
            R.id.btnRoot -> {
                list.add(Cat(R.drawable.cat1,"meo1","mie"))
                list.add(Cat(R.drawable.cat2,"meo2","nie"))
                list.add(Cat(R.drawable.cat3,"meo3","kie"))
                customAdapter.notifyDataSetChanged() // Cập nhật ListView
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
//    khởi tạo OptionsMenu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.mnuAdd->{
                var intent = Intent(this@MainActivity,AddActivity::class.java)
                addActivityLauncher.launch(intent)
            }
            R.id.mnuUpdate->{
                if (selectedPosition != -1){
                    var intent = Intent(this@MainActivity,UpdateActivity::class.java)
                    intent.putParcelableArrayListExtra("list",ArrayList(list))
                    intent.putExtra("pos",selectedPosition)
                    updateActivityLauncher.launch(intent)
                } else {
                    Toast.makeText(this@MainActivity,"Vui lòng chọn một item",Toast.LENGTH_SHORT).show()
                    return false // ngừng xử lý nếu không có item được chọn.
                }
            }
            R.id.mnuRoot->{
                list.add(Cat(R.drawable.cat1,"meo1","mie"))
                list.add(Cat(R.drawable.cat2,"meo2","nie"))
                list.add(Cat(R.drawable.cat3,"meo3","kie"))
                customAdapter.notifyDataSetChanged() // Cập nhật ListView
            }
        }
        return super.onOptionsItemSelected(item)
    }
}