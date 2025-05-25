package com.example.myproject.mydiallog

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.myproject.R

class mydialog(context:Context):Dialog(context) {
    init
    {
        //找到视图
        setContentView(R.layout.dialog_custom)
        val tvTitle=findViewById<TextView>(R.id.tvTitle)
        val etUsername=findViewById<EditText>(R.id.etUsername)
        val myconfirm=findViewById<Button>(R.id.confirm)
        val mycancle=findViewById<Button>(R.id.cancle)
        //设置监听器
        myconfirm.setOnClickListener {
            //提示成功
            mylistener?.onConfirm()
            dismiss()
        }
        mycancle.setOnClickListener {
            //提示取消
            mylistener?.onCancle()
            dismiss()
        }
        //点击外部不能够关闭
        setCanceledOnTouchOutside(false)


    }
    //设置一个接口
    interface  myListener{
        fun onCancle();
        fun onConfirm();
    }
    //设置监听者
    private var mylistener: myListener? =null

    //设置回调方法
    public  fun setListener(listen:myListener)
    {
        this.mylistener=listen
    }
}