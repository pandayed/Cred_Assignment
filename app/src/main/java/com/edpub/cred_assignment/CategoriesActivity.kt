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

    private lateinit var benefitsLayoutManager: GridLayoutManager
    private lateinit var benefitsAdapter: RecyclerViewItemAdapter

    private lateinit var billsLayoutManager: GridLayoutManager
    private lateinit var billsAdapter: RecyclerViewItemAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

        moneyLayoutManager = GridLayoutManager(this, 1)
        moneyAdapter = RecyclerViewItemAdapter(moneyLayoutManager, DataItems.moneyItems)

        benefitsLayoutManager = GridLayoutManager(this, 1)
        benefitsAdapter = RecyclerViewItemAdapter(benefitsLayoutManager, DataItems.benefitsItems)

        billsLayoutManager = GridLayoutManager(this, 1)
        billsAdapter = RecyclerViewItemAdapter(billsLayoutManager, DataItems.billItems)

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

        val rvBenefits = findViewById<RecyclerView>(R.id.rvBenefits)
        rvBenefits.layoutManager = benefitsLayoutManager
        rvBenefits.adapter = benefitsAdapter

        val rvBills = findViewById<RecyclerView>(R.id.rvBills)
        rvBills.layoutManager = billsLayoutManager
        rvBills.adapter = billsAdapter

    }

    private fun loadDescriptiveUI(){
        moneyLayoutManager.spanCount = 1
        moneyAdapter.notifyItemRangeChanged(0, moneyAdapter.itemCount)

        benefitsLayoutManager.spanCount = 1
        benefitsAdapter.notifyItemRangeChanged(0, benefitsAdapter.itemCount)

        billsLayoutManager.spanCount = 1
        billsAdapter.notifyItemRangeChanged(0, billsAdapter.itemCount)

    }

    private fun loadCompactUI(){
        moneyLayoutManager.spanCount = 3
        moneyAdapter.notifyItemRangeChanged(0, moneyAdapter.itemCount)

        benefitsLayoutManager.spanCount = 3
        benefitsAdapter.notifyItemRangeChanged(0, benefitsAdapter.itemCount)

        billsLayoutManager.spanCount = 3
        billsAdapter.notifyItemRangeChanged(0, billsAdapter.itemCount)
    }
}