package com.example.lab_02_ltdd_04_recyclerview

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(var list: MutableList<Student>, var onStudentClick:RecyclerInterface) :
    RecyclerView.Adapter<CustomAdapter.StudentViewHolder>() {
    //    class view Holder
    inner class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.itemView.apply {
            var image = findViewById<ImageView>(R.id.imgAva)
            var txtId = findViewById<TextView>(R.id.txtId)
            var txtName = findViewById<TextView>(R.id.txtName)

            txtId.text = list[position].id
            txtName.text = list[position].name
            image.setImageResource(list[position].image)

//            Lắng nghe Item click chọn
            holder.itemView.setOnClickListener {
                onStudentClick.onClickStudent(position)
            }

            holder.itemView.setOnLongClickListener {
                onStudentClick.onLongClickStudent(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}