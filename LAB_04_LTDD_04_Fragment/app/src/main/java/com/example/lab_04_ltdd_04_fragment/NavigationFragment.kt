package com.example.lab_04_ltdd_04_fragment

import androidx.fragment.app.Fragment

interface NavigationFragment {
    fun navigaTo(fragment: Fragment,tag:String,backStack: String)
}