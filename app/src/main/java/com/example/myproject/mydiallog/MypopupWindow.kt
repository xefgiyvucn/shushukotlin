package com.example.myproject.mydiallog

import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupWindow
import com.example.myproject.R
import com.example.myproject.myfragment.mypagefragment

class MypopupWindow(context: mypagefragment) {
    private  val popView:View=LayoutInflater.from(context.context).inflate(R.layout.popupwindow,null)
    private val popupWindow:PopupWindow
    lateinit var but: Button // 使用 lateinit 延迟初始化

    init{

        popupWindow= PopupWindow(popView,ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT,true).apply {
            isOutsideTouchable=true  //点击外部关闭
        }
        but = popView.findViewById(R.id.btnClose) // 在 init 中初始化
        but.setOnClickListener {
            popupWindow.dismiss()
        }
    }
    fun show(anchorView:View)
    {
        if(!popupWindow.isShowing){
            popupWindow.showAtLocation(anchorView, Gravity.START, 0, 0)
        }
    }
    fun dismiss()
    {
        popupWindow.dismiss()
    }
    fun onDestroy()
    {
        if(popupWindow.isShowing)
        {
            popupWindow.dismiss()
        }

    }
}