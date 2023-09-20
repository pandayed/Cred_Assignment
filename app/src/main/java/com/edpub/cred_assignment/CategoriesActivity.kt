package com.edpub.cred_assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.switchmaterial.SwitchMaterial

class CategoriesActivity : AppCompatActivity() {

    private val TAG = "CategoriesActivityTag"
    private lateinit var moneyLayoutManager: GridLayoutManager
    private lateinit var moneyAdapter: RecyclerViewItemAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

        moneyLayoutManager = GridLayoutManager(this, 1)

        moneyAdapter = RecyclerViewItemAdapter(moneyLayoutManager)

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


        val rvMoney = findViewById<RecyclerView>(R.id.rvMoney)
        rvMoney.layoutManager = moneyLayoutManager
        rvMoney.adapter = moneyAdapter

    }

    private fun loadDescriptiveUI(){
        moneyLayoutManager.spanCount = 1
        moneyAdapter.notifyItemRangeChanged(0, moneyAdapter.itemCount)

    }

    private fun loadCompactUI(){
        moneyLayoutManager.spanCount = 3;
        moneyAdapter.notifyItemRangeChanged(0, moneyAdapter.itemCount)
    }
}