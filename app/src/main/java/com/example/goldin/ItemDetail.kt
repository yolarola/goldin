package com.example.goldin

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

//import kotlinx.android.synthetic.main.activity_item_detail.*

class ItemDetail : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)

        val name = intent.getStringExtra("name")
        findViewById<TextView>(R.id.item_name).text = name
        //item_name.text = name


        findViewById<Button>(R.id.button3).setOnClickListener {
            val intent = Intent(this@ItemDetail, ItemEdit::class.java)
            intent.putExtra("name", name)
            startActivity(intent)
        }


        findViewById<Button>(R.id.button7).setOnClickListener {
            val intent = Intent(this@ItemDetail, main_page::class.java)
            startActivity(intent)
        }
    }
}
