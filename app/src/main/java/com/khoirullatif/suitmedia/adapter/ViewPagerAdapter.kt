package com.khoirullatif.suitmedia.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.khoirullatif.suitmedia.R
import com.khoirullatif.suitmedia.models.Event
import java.util.*
import kotlin.collections.ArrayList


class ViewPagerAdapter(context: Context, private val events: ArrayList<Event>) : PagerAdapter() {

    private var mLayoutInflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return events.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as RelativeLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView = mLayoutInflater.inflate(R.layout.viewpager_item, container, false)
        val imageView: ImageView = itemView.findViewById(R.id.image_viewpager_item)
        val tvEvent: TextView = itemView.findViewById(R.id.tv_viewpager_item)

        imageView.setImageResource(events[position].photo)
        tvEvent.text = events[position].name
        Objects.requireNonNull(container).addView(itemView)

        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as RelativeLayout)
    }
}