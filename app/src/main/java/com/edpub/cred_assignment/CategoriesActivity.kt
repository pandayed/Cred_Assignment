package com.edpub.cred_assignment

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.switchmaterial.SwitchMaterial
import org.json.JSONArray
import org.json.JSONObject

class CategoriesActivity : AppCompatActivity() {

    private val TAG = "CategoriesActivityTag"
    private val url = "https://api.mocklets.com/p68785/skuSections"

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

        if (sViewChanger.isChecked) {
            loadCompactUI()
        } else {
            loadDescriptiveUI()
        }

        sViewChanger.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked) {
                loadCompactUI()
            } else {
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

        getDataFromApi()

    }

    private fun loadDescriptiveUI() {
        moneyLayoutManager.spanCount = 1
        moneyAdapter.notifyItemRangeChanged(0, moneyAdapter.itemCount)

        benefitsLayoutManager.spanCount = 1
        benefitsAdapter.notifyItemRangeChanged(0, benefitsAdapter.itemCount)

        billsLayoutManager.spanCount = 1
        billsAdapter.notifyItemRangeChanged(0, billsAdapter.itemCount)

    }

    private fun loadCompactUI() {
        moneyLayoutManager.spanCount = 3
        moneyAdapter.notifyItemRangeChanged(0, moneyAdapter.itemCount)

        benefitsLayoutManager.spanCount = 3
        benefitsAdapter.notifyItemRangeChanged(0, benefitsAdapter.itemCount)

        billsLayoutManager.spanCount = 3
        billsAdapter.notifyItemRangeChanged(0, billsAdapter.itemCount)
    }

    private fun getDataFromApi() {
        Log.i(TAG, "getDataFromApi: Called")
        val queue = Volley.newRequestQueue(this)
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                parseJsonData(response)
            },
            { error ->
                Log.e(TAG, "Error: ${error}")
            }
        )
        queue.add(jsonObjectRequest)
    }

    private fun parseJsonData(response: JSONObject) {
        val moneySize = DataItems.moneyItems.size
        DataItems.moneyItems.clear()
        moneyAdapter.notifyItemRangeRemoved(0, moneySize)

        val benefitsSize = DataItems.benefitsItems.size
        DataItems.benefitsItems.clear()
        benefitsAdapter.notifyItemRangeRemoved(0, benefitsSize)

        val billsSize = DataItems.billItems.size
        DataItems.billItems.clear()
        billsAdapter.notifyItemRangeRemoved(0, billsSize)

        val data = response.get("data") as JSONArray

        val money = data.getJSONObject(0).getJSONObject("section_properties").getJSONArray("items")
        for (i in 0..<money.length()) {
            val displayData = money.getJSONObject(i).getJSONObject("display_data")
            val currDataItem = RvItemClass(
                R.drawable.ic_launcher_foreground,
                displayData.getString("name"),
                displayData.getString("description")
            )
            DataItems.moneyItems.add(currDataItem)
            moneyAdapter.notifyItemInserted(DataItems.moneyItems.size - 1)
        }

        val benefits = data.getJSONObject(1).getJSONObject("section_properties").getJSONArray("items")
        for (i in 0..<benefits.length()) {
            val displayData = benefits.getJSONObject(i).getJSONObject("display_data")
            val currDataItem = RvItemClass(
                R.drawable.ic_launcher_foreground,
                displayData.getString("name"),
                displayData.getString("description")
            )
            DataItems.benefitsItems.add(currDataItem)
            benefitsAdapter.notifyItemInserted(DataItems.benefitsItems.size - 1)
        }

        val bills = data.getJSONObject(2).getJSONObject("section_properties").getJSONArray("items")
        for (i in 0..<bills.length()) {
            val displayData = bills.getJSONObject(i).getJSONObject("display_data")
            val currDataItem = RvItemClass(
                R.drawable.ic_launcher_foreground,
                displayData.getString("name"),
                displayData.getString("description")
            )
            DataItems.billItems.add(currDataItem)
            billsAdapter.notifyItemInserted(DataItems.billItems.size - 1)
        }

    }
}