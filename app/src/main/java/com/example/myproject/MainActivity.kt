package com.example.myproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myproject.ui.theme.MyprojectTheme
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.myproject.myfragment.myfragment

class MainActivity : FragmentActivity() {
    lateinit var  bottomNavigation:BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_main)
        //设置监听器 切换fragment
        //默认的时候选中home的home1
        bottomNavigation=findViewById(R.id.bottom_navigation)
        bottomNavigation.selectedItemId = R.id.navigation_home
        //手动加载进行初始化
        var morenfragment=myfragment()
        supportFragmentManager.beginTransaction().replace(R.id.myframlayout,morenfragment).commit()

        bottomNavigation.setOnItemSelectedListener {
           var fragment= when(it.itemId) {
               R.id.navigation_home->myfragment()
               R.id.navigation_photo->myfragment()
               R.id.navigation_picture-> myfragment()
               else -> null
           }
            fragment?.let {
                supportFragmentManager.beginTransaction().replace(R.id.myframlayout,it).commit()
            }
             true
        }
    }
}

