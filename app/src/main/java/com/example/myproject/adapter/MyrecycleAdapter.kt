package com.example.myproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myproject.R
import com.example.myproject.model.Mydata

class MyrecycleAdapter(val list: List<Mydata>):RecyclerView.Adapter<fruitholder>(){
    override fun getItemCount(): Int {
     return  list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): fruitholder {
        //创建viewHolder
        val view=LayoutInflater.from(parent.context).inflate(R.layout.page_item,parent,false)
        //封装一下
        return fruitholder(view)
    }

    override fun onBindViewHolder(holder: fruitholder, position: Int) {
        //当刷新到位置的时候进行赋值
        val mylist=list[position]
        holder.text.text=mylist.data

    }
}
class fruitholder(view:View):ViewHolder(view){
val image:ImageView=view.findViewById(R.id.item_image)
    val  text:TextView=view.findViewById(R.id.item_text)
}