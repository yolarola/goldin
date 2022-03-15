package com.example.goldin

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import okhttp3.*
import java.io.IOException
import okhttp3.Response

import okhttp3.OkHttpClient
import org.json.JSONObject
import java.io.File
import java.nio.charset.Charset
import kotlin.concurrent.thread
import com.example.goldin.APIService
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.http.GET


class ItemEdit : Activity() {


    fun getMethod() {

        // Create Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8000")
            .build()

        // Create Service
        val service = retrofit.create(APIService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            /*
             * For @Query: You need to replace the following line with val response = service.getEmployees(2)
             * For @Path: You need to replace the following line with val response = service.getEmployee(53)
             */

            // Do the GET request and get response
            val response = service.getPosts()

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {

                    // Convert raw JSON to pretty JSON using GSON library
                    val gson = GsonBuilder().setPrettyPrinting().create()

                    val prettyJson = gson.toJson(
                        JsonParser.parseString(
                            response.body()
                                ?.string() // About this thread blocking annotation : https://github.com/square/retrofit/issues/3255

                        )

                    )



                    Log.d("Pretty Printed JSON :", prettyJson)

                } else {

                    Log.e("RETROFIT_ERROR", response.code().toString())

                }
            }

        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_edit)

        val name = intent.getStringExtra("name")


        val rez = getMethod()
        Log.d("e", rez.toString())




        findViewById<TextView>(R.id.editTextTextPersonName2).text = name
        findViewById<TextView>(R.id.editTextTextPersonName3).text = ""
        // run("https://api.github.com/users/Evin1-/repos")
        findViewById<Button>(R.id.button4).setOnClickListener {
            val intent = Intent(this@ItemEdit, main_page::class.java)
            // intent.putExtra("name", name)
            startActivity(intent)
        }


    }
}
