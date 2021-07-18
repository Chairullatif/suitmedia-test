package com.khoirullatif.suitmedia.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.khoirullatif.suitmedia.R
import com.khoirullatif.suitmedia.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val EXTRA_NAME = "extra_name"
        private const val REQUEST_CODE = 100
        private const val REQUEST_CODE_GUEST = 200
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra(EXTRA_NAME)
        binding.tvName.text = String.format(getString(R.string.nama), name)

        binding.btnEvent.setOnClickListener(this)
        binding.btnGuest.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btn_event -> {
                val intent = Intent(this@DetailActivity, EventActivity::class.java)
                startActivityForResult(intent, REQUEST_CODE)
            }
            R.id.btn_guest -> {
                val intent = Intent(this@DetailActivity, GuestActivity::class.java)
                startActivityForResult(intent, REQUEST_CODE_GUEST)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            REQUEST_CODE -> {
                if (resultCode == EventActivity.RESULT_CODE) {
                    val selectedValue = data?.getStringExtra(EventActivity.EXTRA_SELECTED_NAME)
                    Log.d("DetailActivity", "EventValue: $selectedValue")
                    binding.btnEvent.text = selectedValue.toString()
                }
            }
            REQUEST_CODE_GUEST -> {
                if (resultCode == GuestActivity.RESULT_CODE) {
                    val selectedValue = data?.getStringExtra(GuestActivity.EXTRA_SELECTED_GUEST)
                    Log.d("DetailActivity", "GuestValue: $selectedValue")
                    binding.btnGuest.text = selectedValue.toString()
                }
            }
        }
    }

}