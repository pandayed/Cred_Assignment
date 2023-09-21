package com.edpub.cred_assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPref = getSharedPreferences("com.edpub.cred_assignment", MODE_PRIVATE)

        val category = sharedPref.getString("category", null)

        val clGotoCategory = findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.clGotoCategory)
        val clMain = findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.clMain)

        if(category==null){
            clGotoCategory.visibility = android.view.View.VISIBLE
            clMain.visibility = android.view.View.GONE
        }else{
            clMain.visibility = android.view.View.VISIBLE
            clGotoCategory .visibility = android.view.View.GONE
        }

        val bGotoCategory = findViewById<Button>(R.id.bGotoCategory)
        bGotoCategory.setOnClickListener {
            val intent = Intent(this, CategoriesActivity::class.java)
            startActivity(intent)
        }
    }
}