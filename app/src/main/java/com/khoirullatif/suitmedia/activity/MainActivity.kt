package com.khoirullatif.suitmedia.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.khoirullatif.suitmedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNext.setOnClickListener {
            val intent = Intent(this@MainActivity, DetailActivity::class.java)
            val name = binding.edtName.text.toString()
            intent.putExtra(DetailActivity.EXTRA_NAME, name)
            startActivity(intent)
        }
    }
}