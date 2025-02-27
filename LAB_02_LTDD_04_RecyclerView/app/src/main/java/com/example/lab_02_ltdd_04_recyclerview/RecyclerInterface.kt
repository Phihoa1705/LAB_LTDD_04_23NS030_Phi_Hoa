package com.example.lab_02_ltdd_04_recyclerview

interface RecyclerInterface {
    fun onClickStudent(position: Int) : Unit
    fun onLongClickStudent(position: Int) : Boolean
}