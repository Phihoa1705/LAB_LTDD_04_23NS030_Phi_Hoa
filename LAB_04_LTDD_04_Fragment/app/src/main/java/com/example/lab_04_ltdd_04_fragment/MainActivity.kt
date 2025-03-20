package com.example.lab_04_ltdd_04_fragment

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.lab_04_ltdd_04_fragment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),NavigationFragment {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCounter.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .add(R.id.frameLayout1,FragmentA.newInstance(),"tagFragA")
                .addToBackStack("StackA")
                .commit()
        }
    }

    override fun navigaTo(fragment: Fragment, tag: String, backStack: String) {
        supportFragmentManager.beginTransaction()
            .add(R.id.frameLayout1,fragment,tag)
            .addToBackStack(backStack)
            .commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }
}