package com.example.mydemo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val pageSize = 4 //每页多少条

        val itemBeans = initData(pageSize)
        val scrollAdapter = HorizontalScrollAdapter(this, itemBeans)
        view_pager.adapter = scrollAdapter
        btn.setOnClickListener {
            if (view_pager.currentItem >= 0 && view_pager.currentItem + 1 < itemBeans.size) {
                btn.text = "上一页"
                view_pager.currentItem = view_pager.currentItem + 1
            } else if (view_pager.currentItem + 1 == itemBeans.size) {
                btn.text = "下一页"
                view_pager.currentItem = view_pager.currentItem - 1
            }
        }

        val scroller = ViewPagerScroller(this)
        scroller.setScrollDuration(1000) //时间越长，速度越慢。
        scroller.initViewPagerScroll(view_pager)

        scrollAdapter.setOnItemClickListener { position, currentPage, data ->
            Toast.makeText(
                this,
                "itemPosition = $position , currentPage = $currentPage , data = ${data.title} ",
                Toast.LENGTH_SHORT
            ).show()
        }
    }


    private fun initData(pageSize: Int): List<List<ItemBean>> {
        val datas: MutableList<ItemBean> = ArrayList()
        val maps: MutableList<List<ItemBean>> = ArrayList()
        datas.add(ItemBean("1"))
        datas.add(ItemBean("2"))
        datas.add(ItemBean("3"))
        datas.add(ItemBean("4"))

        //第二页
        datas.add(ItemBean("11"))
        datas.add(ItemBean("22"))
        datas.add(ItemBean("33"))
        val firstPageItems: List<ItemBean> = datas.subList(0, pageSize)
        val secondPageItems: List<ItemBean> = datas.subList(pageSize, datas.size)
        maps.add(firstPageItems)
        maps.add(secondPageItems)
        return maps
    }
}