package com.edpub.cred_assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivityTag"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPref = getSharedPreferences("com.edpub.cred_assignment", MODE_PRIVATE)

        val category = sharedPref.getString("category", null)

        val clGotoCategory = findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.clGotoCategory)
        val clMain = findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.clMain)

        val bGotoCategory = findViewById<Button>(R.id.bGotoCategory)
        val tvTitle = findViewById<android.widget.TextView>(R.id.tvTitle)


        if(category==null){
            clGotoCategory.visibility = android.view.View.VISIBLE
            clMain.visibility = android.view.View.GONE
        }else{
            Log.i(TAG, category)
            clMain.visibility = android.view.View.VISIBLE
            clGotoCategory .visibility = android.view.View.GONE
            val handler = Handler(Looper.getMainLooper())
            handler.post {
                tvTitle.text = category
            }

        }

        bGotoCategory.setOnClickListener {
            val intent = Intent(this, CategoriesActivity::class.java)
            startActivity(intent)
        }


    }
}