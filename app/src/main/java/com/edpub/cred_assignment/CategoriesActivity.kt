package com.edpub.cred_assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CategoriesActivity : AppCompatActivity() {

    private lateinit var moneyLayoutManager: GridLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)


        moneyLayoutManager = GridLayoutManager(this, 1)

        val moneyAdapter = RecyclerViewItemAdapter(moneyLayoutManager)

        val rvMoney = findViewById<RecyclerView>(R.id.rvMoney)
        rvMoney.layoutManager = moneyLayoutManager
        rvMoney.adapter = moneyAdapter


    }
}