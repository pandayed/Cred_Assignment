package com.edpub.cred_assignment

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Switch
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.switchmaterial.SwitchMaterial

class CategoriesActivity : AppCompatActivity() {

    private val TAG = "CategoriesActivityTag"
    private lateinit var moneyLayoutManager: GridLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

        val sViewChanger = findViewById<SwitchMaterial>(R.id.sViewChanger)
        if(sViewChanger.isChecked){
            loadCompactUI()
        }else{
            loadDescriptiveUI()
        }

        sViewChanger.setOnCheckedChangeListener { compoundButton, isChecked ->
            if(isChecked){
                loadCompactUI()
            }else{
                loadDescriptiveUI()
            }
        }

        moneyLayoutManager = GridLayoutManager(this, 1)

        val moneyAdapter = RecyclerViewItemAdapter(moneyLayoutManager)

        val rvMoney = findViewById<RecyclerView>(R.id.rvMoney)
        rvMoney.layoutManager = moneyLayoutManager
        rvMoney.adapter = moneyAdapter


    }

    private fun loadDescriptiveUI(){
        Log.i(TAG, "LoadDescriptiveUI")
    }

    private fun loadCompactUI(){
        Log.i(TAG, "LoadCompactUI")
    }
}