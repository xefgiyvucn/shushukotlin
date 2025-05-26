package com.example.myproject.mydiallog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.myproject.R
import java.lang.ref.WeakReference

class mydialog(context:Context):Dialog(context) {
    //使用了弱引用，防止隐式的强引用链

   // override fun create() {
    //    super.create()
        //找到视图
     override fun onCreate(savedINstanceState:Bundle?)
   {
       setContentView(R.layout.dialog_custom)
       //不会出现这个为空的情况
       val tvTitle=findViewById<TextView>(R.id.tvTitle)
       val etUsername=findViewById<EditText>(R.id.etUsername)
       val myconfirm=findViewById<Button>(R.id.confirm)
       val mycancle=findViewById<Button>(R.id.cancle)
       //设置监听器
       myconfirm.setOnClickListener {
           //提示成功
           mylistener?.get()?.onConfirm()
           dismiss()
       }
       mycancle.setOnClickListener {
           //提示取消
           mylistener?.get()?.onCancle()
           //进行了自动管理
           dismiss()
       }
       //点击外部不能够关闭
       setCanceledOnTouchOutside(false)
    }

   // }

    //设置一个接口
    interface  myListener{
        fun onCancle();
        fun onConfirm();
    }
    //设置监听者
    private var mylistener: WeakReference<myListener>? =null

    //设置回调方法
    public  fun setListener(listen: myListener?)
    {
        this.mylistener=WeakReference(listen)
    }
}