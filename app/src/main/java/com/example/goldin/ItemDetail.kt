package com.example.goldin

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.goldin.main_page
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import java.io.Serializable

//import kotlinx.android.synthetic.main.activity_item_detail.*

class ItemDetail : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)

        val position = intent.getIntExtra("name1", 777)
        val id = intent.getIntExtra("id",777)
        val name = intent.getStringExtra("name")
        val price = intent.getIntExtra("price",0)
        val category = intent.getIntExtra("category",0)
        val structure = intent.getStringExtra("structure")
        val weight = intent.getIntExtra("weight",0)
        val color = intent.getStringExtra("color")
        val size = intent.getStringExtra("size")
        val myArray = intent.getSerializableExtra("myArray")

        Log.d("ID", id.toString())
       // var myArray: Serializable? =intent.getSerializableExtra("myArray")


       println("${myArray} массив таков")
        findViewById<TextView>(R.id.item_name).text = name.toString()
        //findViewById<TextView>(R.id.textView3).text = products[name].name


        println("${name} вывод таков")
        Log.d("position",name.toString())
        //Log.d("myArray",myArray.toString())


        findViewById<Button>(R.id.button3).setOnClickListener {
            val intent = Intent(this@ItemDetail, ItemEdit::class.java)
            intent.putExtra("id",id)
            intent.putExtra("name",name)
            intent.putExtra("price",price)
            intent.putExtra("category",category)
            intent.putExtra("structure",structure)
            intent.putExtra("weight",weight)
            intent.putExtra("color",color)
            intent.putExtra("size",size)
            startActivity(intent)
        }
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

                        val intent = Intent(this@ItemDetail, MainActivity::class.java)
                       // intent.putExtra("jsonArray", prettyJson)
                        startActivity(intent)


                        Log.d("Pretty Printed JSON :", prettyJson)

                    } else {

                        Log.e("RETROFIT_ERROR", response.code().toString())

                    }
                }

            }

        }

        findViewById<Button>(R.id.button7).setOnClickListener {
            fun deleteMethod(id: String) {

                // Create Retrofit
                val retrofit = Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:8000")

                    .build()

                // Create Service
                val service = retrofit.create(APIService::class.java)

                CoroutineScope(Dispatchers.IO).launch {

                    // Do the DELETE request and get response

                    val response =service.deleteEmployee(id)
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
                            //intent.setComponent(null)
                            val intent = Intent(this@ItemDetail, MainActivity::class.java)
                           // intent.setComponent(null)
                           // intent.putExtra("jsonArray", prettyJson)
                            intent.putExtra("flag", 1)

                            startActivity(intent)


                            Log.d("Pretty Printed JSON :", prettyJson)

                        } else {

                            Log.e("RETROFIT_ERROR", response.code().toString())

                        }
                    }
                }
            }

            deleteMethod(id.toString())
            //val intent = Intent(this@ItemDetail, main_page::class.java)
           //startActivity(intent)

        }
    }
}
