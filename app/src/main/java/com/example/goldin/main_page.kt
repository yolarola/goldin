package com.example.goldin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit

class main_page: Activity(), OnItemClickListener {
    //var names = getBoxList()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_page)

        val jsonArray = intent.getStringExtra("jsonArray")
       // var flag = intent.getIntExtra("flag",0)
//        if (flag == 1) {
//            recreate()
//        }

     Log.d("array", jsonArray.toString())
      //  val product : Products = Gson().fromJson(jsonArray, Products::class.java)

        val productArray = object : TypeToken<Array<Products>>() {}.type

        val products: Array<Products>

        products= Gson().fromJson(jsonArray, productArray)

        products.forEachIndexed  { idx, tut -> println("> Item ${idx}:\n${tut}") }
        Log.d("array", products[0].toString())



        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

        val customRecyclerAdapter = CustomRecyclerAdapter(products, this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = customRecyclerAdapter

        customRecyclerAdapter.notifyDataSetChanged()



        findViewById<Button>(R.id.button5).setOnClickListener {
            val intent = Intent(this@main_page, ItemAdd::class.java)
            // intent.putExtra("name", name)
            startActivity(intent)
        }

    }

      //  private fun getBoxList(): List<String> {
      //      return this.resources.getStringArray(R.array.box_names).toList()
      //  }


        override fun onItemClicked(products: Array<Products>, position: Int) {

            Toast.makeText(this, " box " + position + " clicled", Toast.LENGTH_LONG).show()
            val intent = Intent(this@main_page, ItemDetail::class.java)
            var id : Int? = products[position].id
            var name: String?=products[position].name
            var price: Int? =products[position].price
            var category: Int? = products[position].category
            var structure: String? = products[position].structure
            var weight: Int? = products[position].weight
            var color: String? = products[position].color
            var size: String? = products[position].size
            intent.putExtra("id",id)
            intent.putExtra("name",name)
            intent.putExtra("price",price)
            intent.putExtra("category",category)
            intent.putExtra("structure",structure)
            intent.putExtra("weight",weight)
            intent.putExtra("color",color)
            intent.putExtra("size",size)

           // intent.putExtra("myArray", products)
            intent.putExtra("name1", position)
            products.forEachIndexed  { idx, tut -> println("> Item ${idx}:\n${tut}") }

            startActivity(intent)
        }
    }
