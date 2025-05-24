package com.example.myproject.myfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myproject.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class myfragment:Fragment() {
    private lateinit var topnav: BottomNavigationView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.myfragment_layout,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
            topnav= activity?.findViewById(R.id.top_navigation)!!
            //默认选中第一个
             topnav.selectedItemId=R.id.nav_tophome1
             //实例化对象
           var mytopfragment=mytopfragment()
             //手动进行替换
           activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.myTabswitch,mytopfragment)?.commit()
            //创建监听器
           topnav.setOnItemSelectedListener {
               var fragment=when(it.itemId)
               {
                   R.id.nav_tophome1->mytopfragment()
                   R.id.nav_tophome2->mytopfragment()
                   R.id.nav_tophome3->mytopfragment()
                   R.id.nav_tophome4->mytopfragment()
                   else ->null
               }
               fragment?.let {
                   activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.myTabswitch,fragment)?.commit()
               }

               true
           }

    }
}
