package com.khoirullatif.suitmedia.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.khoirullatif.suitmedia.databinding.ActivityMainBinding
import java.lang.StringBuilder

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

            isPalindrome(name)

            startActivity(intent)
        }
    }

    private fun isPalindrome(text: String) {
        val builderString = StringBuilder(text)
        val reverseText = builderString.reverse().toString().replace("\\s".toRegex(), "")

        if (text.replace("\\s".toRegex(), "") == reverseText) {
            Toast.makeText(this, "isPalindrome", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "not palindrome", Toast.LENGTH_SHORT).show()
        }

        Log.d("MainActivity", "isPalindrome: $reverseText")
    }
}