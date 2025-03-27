package com.example.ltdd_tuan6

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.ltdd_tuan6.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val myViewModel:MyViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener {
            val a = binding.etNumber1.text.toString().toIntOrNull()?:0
            val b = binding.etNumber2.text.toString().toIntOrNull()?:0

            myViewModel.cong(a, b)
        }

        binding.btnSubtract.setOnClickListener {
            val a = binding.etNumber1.text.toString().toIntOrNull()?:0
            val b = binding.etNumber2.text.toString().toIntOrNull()?:0

            myViewModel.tru(a, b)
        }

        myViewModel.message.observe(this@MainActivity, Observer { message ->
            lifecycleScope.launch(Dispatchers.Main) {
                binding.tvResult.text = message.toString()
            }
        })
    }
}