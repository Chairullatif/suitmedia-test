package com.khoirullatif.suitmedia

import android.content.res.TypedArray
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.khoirullatif.suitmedia.adapter.ViewPagerAdapter
import com.khoirullatif.suitmedia.models.Event

class MapFragment : Fragment() {
    private lateinit var dataName: Array<String>
    private lateinit var dataDate: Array<String>
    private lateinit var dataPhoto: TypedArray
    private var events = arrayListOf<Event>()
    private lateinit var mViewPager: ViewPager
    private lateinit var mViewPagerAdapter: ViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewPager = view.findViewById(R.id.view_pager)
        prepare()
        addItem()
        mViewPagerAdapter = ViewPagerAdapter(view.context, events)
        mViewPager.adapter = mViewPagerAdapter
    }

    private fun prepare() {
        dataName = resources.getStringArray(R.array.data_name)
        dataDate = resources.getStringArray(R.array.data_date)
        dataPhoto = resources.obtainTypedArray(R.array.data_photo)
    }

    private fun addItem() {
        for (position in dataName.indices) {
            val event = Event(
                dataPhoto.getResourceId(position, -1),
                dataName[position],
                dataDate[position]
            )
            events.add(event)
        }
    }

}