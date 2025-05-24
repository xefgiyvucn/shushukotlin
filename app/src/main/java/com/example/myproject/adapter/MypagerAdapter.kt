package com.example.myproject.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myproject.myfragment.mypagefragment

class MypagerAdapter(fragment: Fragment):FragmentStateAdapter(fragment) {
    //    // 内部实际调用 fragment.childFragmentManager
    override fun getItemCount(): Int {
        return 5
    }

    override fun createFragment(position: Int): mypagefragment {

    return when(position)
    {
        0->mypagefragment()
        1-> mypagefragment()
        2->mypagefragment()
        3->mypagefragment()
        4->mypagefragment()
        else -> throw IllegalStateException("Invalid position")
    }
    }
}