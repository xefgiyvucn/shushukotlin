package com.example.myproject.myfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.myproject.R
import com.example.myproject.adapter.MypagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class mytopfragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return  inflater.inflate(R.layout.mytopfragment_layout,container,false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var mviewpager=view.findViewById<ViewPager2>(R.id.mviewpager)
        var tabLayout=view.findViewById<TabLayout>(R.id.tabLayout)
        //1.设置适配器
        mviewpager?.adapter=MypagerAdapter(this)
        mviewpager.isUserInputEnabled = true  // 确保允许用户滑动（默认true，但可能被误设为false）

        //2.关联tablayout和viewpager
        TabLayoutMediator(tabLayout,mviewpager)
        {
            tab,position->
           tab.text= when(position)
            {
              0->"手页"
               1->"第二页"
               2->"第三页"
               3->"第四页"
               4->"第五页"
               else ->"error"
            }.toString()
        }.attach()

    }
}