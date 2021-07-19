package com.khoirullatif.suitmedia.activity

import android.content.Intent
import android.content.res.TypedArray
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.khoirullatif.suitmedia.MapFragment
import com.khoirullatif.suitmedia.R
import com.khoirullatif.suitmedia.adapter.EventAdapter
import com.khoirullatif.suitmedia.databinding.ActivityEventBinding
import com.khoirullatif.suitmedia.models.Event


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

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)

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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.plus_button -> {
            val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
            binding.lvList.visibility = View.INVISIBLE
            binding.fragmentMap.visibility = View.VISIBLE
            ft.replace(R.id.fragment_map, MapFragment())
            ft.commit()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
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