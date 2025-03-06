package com.example.lab_02_ltdd_04_listview

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lab_02_ltdd_04_listview.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.navBottom.selectedItemId = R.id.navAc2

        binding.navBottom.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.navMainAc -> {
                    var intent = Intent(this@MainActivity2,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.navAc2 -> {
                    true
                }
                R.id.navAc3 -> {
                    var intent = Intent(this@MainActivity2,MainActivity3::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
            }
            false
        }
    }
}