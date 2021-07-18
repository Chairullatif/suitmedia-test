package com.khoirullatif.suitmedia.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.khoirullatif.suitmedia.Guest
import com.khoirullatif.suitmedia.R
import com.khoirullatif.suitmedia.adapter.GuestAdapter
import com.khoirullatif.suitmedia.databinding.ActivityGuestBinding
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONArray

class GuestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGuestBinding
    private lateinit var rvGuest: RecyclerView
    private var guests = ArrayList<Guest>()

    companion object {
        const val EXTRA_SELECTED_GUEST = "extra_selected_GUEST"
        const val RESULT_CODE = 210
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDataGuest()

    }

    private fun getDataGuest() {
        val client = AsyncHttpClient()
        val url = "http://www.mocky.io/v2/596dec7f0f000023032b8017"
        client.get(url, object : AsyncHttpResponseHandler() {
            @SuppressLint("Recycle")
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {

                val result = String(responseBody!!)
                try {
                    val jsonArray = JSONArray(result)

                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        val name = jsonObject.getString("name")
                        val birthdate = jsonObject.getString("birthdate")
                        val photo = resources.obtainTypedArray(R.array.data_photo_guest).getResourceId(i, -1)

                        Log.d("GuestActivity", "name: $name")

                        val guest = Guest(
                            name, birthdate, photo
                        )
                        guests.add(guest)
                    }

                    Log.d("GuestActivity", "guests: ${guests.isEmpty()}")

                    actionAndAdapter(guests)


                } catch (e: Exception) {
                    Toast.makeText(this@GuestActivity, e.message, Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                val errorMessage = when (statusCode) {
                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error?.message}"
                }
                Toast.makeText(this@GuestActivity, errorMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun actionAndAdapter(listGuest: ArrayList<Guest>) {
        rvGuest = binding.rvGuest
        rvGuest.layoutManager = GridLayoutManager(this@GuestActivity,2)
        val gridGuestAdapter = GuestAdapter(listGuest)
        rvGuest.adapter = gridGuestAdapter

        gridGuestAdapter.setOnItemClickCallback(object : GuestAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Guest) {
                val resultIntent = Intent()
                resultIntent.putExtra(EXTRA_SELECTED_GUEST, data.name)
                setResult(RESULT_CODE, resultIntent)

                val date = data.birthdate.split("-")[2].toInt()
                val month = data.birthdate.split("-")[1].toInt()
                Log.d("GuestActivity", "onItemClicked: $date")

                val alert: String = if (date % 2 == 0 && date % 3 == 0) {
                    "IOS"
                } else if (date % 2 == 0) {
                    "blackberry"
                } else if (date % 3 == 0) {
                    "android"
                } else {
                    "feature phone"
                }

                isMonthPrime(month)

                Toast.makeText(this@GuestActivity, alert, Toast.LENGTH_SHORT).show()
                finish()
            }
        })
    }

    private fun isMonthPrime(mm: Int) : Boolean {
        return if (mm % 2 == 0) {
            Log.d("GuestActivity", "isMonthPrime: $mm is not prime")
            false
        } else {
            Log.d("GuestActivity", "isMonthPrime: $mm is prime")
            true
        }
    }
}