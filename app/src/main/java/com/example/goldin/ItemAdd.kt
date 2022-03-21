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
import android.widget.Toast
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
import com.example.goldin.Products
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

class ItemAdd : Activity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_add)


        val name = "test"
        val price = 100
        val category = 2
        val structure = "test"
        val weight = 1
        val color = "test"
        val size = 1
        val myArray = "test"


        // val rez = getMethod()
        // Log.d("e", rez.toString())




        findViewById<TextView>(R.id.editTextTextPersonName2).text = name //
        findViewById<TextView>(R.id.editTextTextPersonName3).text = category.toString()  // category
        findViewById<TextView>(R.id.editTextTextPersonName4).text = structure// sostav structure
        findViewById<TextView>(R.id.editTextTextPersonName5).text = price.toString()// price
        findViewById<TextView>(R.id.editTextTextPersonName6).text = weight.toString()// weight ves
        findViewById<TextView>(R.id.editTextTextPersonName7).text = color// color
        // run("https://api.github.com/users/Evin1-/repos")
        findViewById<Button>(R.id.button4).setOnClickListener {
            fun rawJSON(jsonObject: JSONObject) {

                // Create Retrofit
                val retrofit = Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:8000")
                    .build()

                // Create Service
                val service = retrofit.create(APIService::class.java)

                // Create JSON using JSONObject



                // Convert JSONObject to String
                val jsonObjectString = jsonObject.toString()

                // Create RequestBody ( We're not using any converter, like GsonConverter, MoshiConverter e.t.c, that's why we use RequestBody )
                val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())
                // Log.d("requestbody", requestBody.toString())
                CoroutineScope(Dispatchers.IO).launch {
                    // Do the POST request and get response
                    val response = service.createEmployee(requestBody)

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
                            val intent = Intent(this@ItemAdd, MainActivity::class.java)
                           // intent.putExtra("jsonArray", prettyJson)
                            startActivity(intent)
                            Log.d("Pretty Printed JSON :", prettyJson)

                        } else {

                            Log.e("RETROFIT_ERROR", response.code().toString())

                        }
                    }
                }
            }
//            val intent = Intent(this@ItemEdit, main_page::class.java)
//            intent.putExtra("name", name)
//            startActivity(intent)
            val text1: CharSequence = findViewById<TextView>(R.id.editTextTextPersonName2).text
            val text2: CharSequence = findViewById<TextView>(R.id.editTextTextPersonName5).text
            val text3: CharSequence = findViewById<TextView>(R.id.editTextTextPersonName3).text
            val text4: CharSequence = findViewById<TextView>(R.id.editTextTextPersonName4).text
            val text5: CharSequence = findViewById<TextView>(R.id.editTextTextPersonName6).text
            val text6: CharSequence = findViewById<TextView>(R.id.editTextTextPersonName7).text
            if((text1.trim().isNotEmpty()) and (text2.trim().isNotEmpty()) and (text3.trim()
                    .isNotEmpty()) and (text4.trim().isNotEmpty()) and (text5.trim().isNotEmpty()) and (text6.trim()
                    .isNotEmpty())) {
                val jsonObject = JSONObject()
                jsonObject.put("name", findViewById<TextView>(R.id.editTextTextPersonName2).text) //
                jsonObject.put(
                    "price",
                    findViewById<TextView>(R.id.editTextTextPersonName5).text
                ) //
                jsonObject.put(
                    "category",
                    findViewById<TextView>(R.id.editTextTextPersonName3).text
                )//
                jsonObject.put(
                    "structure",
                    findViewById<TextView>(R.id.editTextTextPersonName4).text
                ) //
                jsonObject.put(
                    "weight",
                    findViewById<TextView>(R.id.editTextTextPersonName6).text
                ) //
                jsonObject.put("color", findViewById<TextView>(R.id.editTextTextPersonName7).text)//
                jsonObject.put(
                    "size",
                    "test"
                ) //
                rawJSON(jsonObject)
            } else {
                Toast.makeText(applicationContext, "Please enter some message! ", Toast.LENGTH_SHORT).show()
            }

        }


    }
}
