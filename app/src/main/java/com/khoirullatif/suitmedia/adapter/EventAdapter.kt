package com.khoirullatif.suitmedia.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.khoirullatif.suitmedia.Event
import com.khoirullatif.suitmedia.R

class EventAdapter internal constructor(private val context: Context) : BaseAdapter() {

    internal var events = arrayListOf<Event>()

    override fun getCount(): Int {
        return events.size
    }

    override fun getItem(i: Int): Any = events[i]

    override fun getItemId(i: Int): Long = i.toLong()

    override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View {
        var itemView = view
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.item_event, viewGroup, false)
        }

        val viewHolder = ViewHolder(itemView as View)

        val event = getItem(position) as Event
        viewHolder.bind(event)
        return itemView
    }

    private inner class ViewHolder(view: View) {
        val tvNameEvent: TextView = view.findViewById(R.id.tv_name_event)
        val tvDateEvent: TextView = view.findViewById(R.id.tv_date_event)
        val ivPhotoEvent: ImageView = view.findViewById(R.id.iv_photo_event)

        fun bind(event: Event) {
            tvNameEvent.text = event.name
            tvDateEvent.text = event.date
            //ivPhotoEvent.setImageResource(event.photo)
            Glide.with(context)
                .load(event.photo)
                .into(ivPhotoEvent)
        }
    }
}