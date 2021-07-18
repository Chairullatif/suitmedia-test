package com.khoirullatif.suitmedia.activity

import android.content.Intent
import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import com.khoirullatif.suitmedia.Event
import com.khoirullatif.suitmedia.R
import com.khoirullatif.suitmedia.adapter.EventAdapter
import com.khoirullatif.suitmedia.databinding.ActivityEventBinding

class EventActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEventBinding
    private lateinit var adapterEvent: EventAdapter
    private lateinit var dataName: Array<String>
    private lateinit var dataDate: Array<String>
    private lateinit var dataPhoto: TypedArray
    private var events = arrayListOf<Event>()

    companion object {
        const val EXTRA_SELECTED_NAME = "extra_selected_name"
        const val RESULT_CODE = 110
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listView: ListView = binding.lvList
        adapterEvent = EventAdapter(this)
        listView.adapter = adapterEvent

        prepare()
        addItem()

        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val value = events[position].name
            val resultIntent = Intent()
            resultIntent.putExtra(EXTRA_SELECTED_NAME, value)
            setResult(RESULT_CODE, resultIntent)
            finish()
        }
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
        adapterEvent.events = events
    }
}