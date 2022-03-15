package com.example.goldin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.FieldPosition

class main_page: Activity(), OnItemClickListener {
    //var names = getBoxList()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_page)


        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.adapter = CustomRecyclerAdapter(getBoxList())

        var customRecyclerAdapter = CustomRecyclerAdapter(getBoxList(), this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = customRecyclerAdapter
        customRecyclerAdapter.notifyDataSetChanged()

        findViewById<Button>(R.id.button5).setOnClickListener {
            val intent = Intent(this@main_page, ItemEdit::class.java)
            // intent.putExtra("name", name)
            startActivity(intent)
        }

    }

        private fun getBoxList(): List<String> {
            return this.resources.getStringArray(R.array.box_names).toList()
        }


        override fun onItemClicled(position: Int) {

//        Toast.makeText(this, " box " + position + " clicled", Toast.LENGTH_LONG).show()
            val intent = Intent(this@main_page, ItemDetail::class.java)
            var names = getBoxList()
            intent.putExtra("name", names[position])
            startActivity(intent)
        }
    }
