package com.example.lab_02_ltdd_04_listview

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class CustomAdapter(var activity: Activity,var list: ArrayList<Cat>) :
    ArrayAdapter<Cat>(activity,R.layout.list_item) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = activity.layoutInflater
        var rowView = view.inflate(R.layout.list_item,parent,false)

        var image = rowView.findViewById<ImageView>(R.id.imgCat)
        var id = rowView.findViewById<TextView>(R.id.txtId)
        var name = rowView.findViewById<TextView>(R.id.txtName)

        // Dùng setImageURI nếu có ảnh từ thư viện
        image.setImageResource(list[position].image)
        id.text = list[position].id
        name.text = list[position].name

        return rowView
    }

    override fun getCount(): Int {
        return list.size
    }
}