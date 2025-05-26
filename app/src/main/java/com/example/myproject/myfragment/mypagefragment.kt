package com.example.myproject.myfragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.myproject.R
import com.example.myproject.adapter.MyrecycleAdapter
import com.example.myproject.model.Mydata
import kotlin.math.abs
import android.view.VelocityTracker
import android.widget.Button
import android.os.Handler
import android.os.Looper
import com.example.myproject.mydiallog.MypopupWindow
import com.example.myproject.mydiallog.mydialog

class mypagefragment:Fragment() {
    private var velocityTracker: VelocityTracker? = null
    private lateinit var popWindow: MypopupWindow // 成员变量
    private var mdialog:mydialog?=null
    private var dialogListener:mydialog.myListener?=null

        override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.mypagefragment_layout,container,false)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        velocityTracker = VelocityTracker.obtain() // 初始化
        mdialog=mydialog(requireContext())
        val  mybuttom=view.findViewById<Button>(R.id.mybut)
        val   mybuttom2=view.findViewById<Button>(R.id.mybut1)
        dialogListener=object: mydialog.myListener{
            override fun onCancle() {

                Log.d("haha","oncancle")
            }

            override fun onConfirm() {
                Log.d("haha","onConfirm")
                //强引用了
                mybuttom2.text="onConfirm"

            }
        }
        mdialog?.setListener(dialogListener as mydialog.myListener)
        //1。需要父亲能够接收消息
        //在父亲这里创建handelr,父亲默认绑定了looper
        val handler=Handler(Looper.getMainLooper()) {
                msg->
            when(msg.what)
            {
                1->{
                    Log.d("rizhi","我接收到了")
                    mybuttom.text="buahaha".toString()
                    true
                }
                else -> false
            }
        }
        //可以进行ui组件的初始化
      // 在 mypagefragment 中
        val viewpager2 = (parentFragment as? mytopfragment)?.view?.findViewById<ViewPager2>(R.id.mviewpager)        //先保存起始位置
        if(viewpager2==null) return
        var startX=0f
        var startY=0f
        val touchSlop=ViewConfiguration.get(requireContext()).scaledTouchSlop
        var popWindow=MypopupWindow(this)

        mybuttom2?.setOnClickListener {
            //创建一个popupWindow
            popWindow.show(it)

        }
        mybuttom?.setOnClickListener {
            Log.d("rizhi","我点击了")
            mdialog?.show()

            //2。创建线程给父亲发送消息
            val thread=Thread{
                Thread.sleep(10000)
                 handler.sendMessage(handler.obtainMessage(1))
            }.start()
        }
        val mylist: List<Mydata> = listOf(
            Mydata("haha1",1),
            Mydata("haha2",1),
            Mydata("haha3",1),
            Mydata("haha4",1),
            Mydata("haha5",1),
            Mydata("haha6",1),
            Mydata("haha7",1),
            Mydata("haha8",1),
            Mydata("haha9",1),
            Mydata("haha10",1),
            Mydata("haha11",1)
            )
        //设置adapter
        val recyclerView=view.findViewById<RecyclerView>(R.id.recycleView)
          //设置监听器
        /*
            正 表示内容向右滚动
            负  表示内容向左滚动
            true  可以滚动
            flase 不可以滚动
         */
        //内部拦截
        recyclerView.addOnItemTouchListener(object : RecyclerView.SimpleOnItemTouchListener() {
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                velocityTracker?.addMovement(e)
                when (e.action) {
                    MotionEvent.ACTION_DOWN -> {
                        startX = e.x
                        startY=e.y
                        //要求父类不拦截
                        viewpager2?.parent?.requestDisallowInterceptTouchEvent(true)
                    }
                    MotionEvent.ACTION_MOVE -> {
                        val dx = e.x - startX
                        val dy = e.y - startY
                        // 若垂直滑动距离大于水平，允许 RecyclerView 处理
                        if (abs(dy) > abs(dx)) {
                            rv.parent.requestDisallowInterceptTouchEvent(true)
                        }
                        if (abs(dx) > touchSlop) {
                            val canScroll = if (dx > 0) {
                                //从右向左滑
                                rv.canScrollHorizontally(-1)

                            } else {
                                //从左向右滑动
                                rv.canScrollHorizontally(1)
                            }
                            if (!canScroll) {
                                //滑到了边界，让父类进行拦截
                                viewpager2?.parent?.requestDisallowInterceptTouchEvent(false)
                            }
                        }
                    }
                    MotionEvent.ACTION_UP -> {
                        velocityTracker?.computeCurrentVelocity(1000)
                        val velocityX = velocityTracker?.xVelocity
                        if(velocityX!=null){
                            if (abs(velocityX) > 1000) {
                                val canScroll = if (velocityX > 0) {
                                    rv.canScrollHorizontally(-1)
                                } else {
                                    rv.canScrollHorizontally(1)
                                }
                                if (!canScroll) {
                                    //让父类进行拦截
                                    viewpager2?.parent?.requestDisallowInterceptTouchEvent(false)
                                }
                            }
                        }
                        velocityTracker?.clear()
                    }
                }
                //表示不拦截
                return false
            }
        })
        recyclerView.adapter=MyrecycleAdapter(mylist)
        //设置布局
//        recyclerView.layoutManager= StaggeredGridLayoutManager(3,
//            StaggeredGridLayoutManager.HORIZONTAL)
        val layoutManager = LinearLayoutManager(requireContext())
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView.layoutManager=layoutManager
    }

    override fun onDestroy() {
        super.onDestroy()
        popWindow.dismiss()
        mdialog?.dismiss()
        mdialog=null
    }

}