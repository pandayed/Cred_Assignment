package com.edpub.cred_assignment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
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

        val sharedPref = getSharedPreferences("com.edpub.cred_assignment", MODE_PRIVATE)

        val sharedPrefEditor = sharedPref.edit()

        moneyLayoutManager = GridLayoutManager(this, 1)
        moneyAdapter = RecyclerViewItemAdapter(moneyLayoutManager, DataItems.moneyItems, object: OnCategoryClick{
            override fun onClick(index: Int) {
                sharedPrefEditor.putString("category", DataItems.moneyItems[index].title)
                sharedPrefEditor.commit()
                val intent = Intent(this@CategoriesActivity, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        })

        benefitsLayoutManager = GridLayoutManager(this, 1)
        benefitsAdapter = RecyclerViewItemAdapter(benefitsLayoutManager, DataItems.benefitsItems, object: OnCategoryClick{
            override fun onClick(index: Int) {
                sharedPrefEditor.putString("category", DataItems.benefitsItems[index].title)
                sharedPrefEditor.commit()
                val intent = Intent(this@CategoriesActivity, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        })

        billsLayoutManager = GridLayoutManager(this, 1)
        billsAdapter = RecyclerViewItemAdapter(billsLayoutManager, DataItems.billItems, object: OnCategoryClick{
            override fun onClick(index: Int) {
                sharedPrefEditor.putString("category", DataItems.billItems[index].title)
                sharedPrefEditor.commit()
                val intent = Intent(this@CategoriesActivity, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        })

        val sViewChanger = findViewById<SwitchCompat>(R.id.sViewChanger)

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
        moneyLayoutManager.spanCount = 4
        moneyAdapter.notifyItemRangeChanged(0, moneyAdapter.itemCount)

        benefitsLayoutManager.spanCount = 4
        benefitsAdapter.notifyItemRangeChanged(0, benefitsAdapter.itemCount)

        billsLayoutManager.spanCount = 4
        billsAdapter.notifyItemRangeChanged(0, billsAdapter.itemCount)
    }

    private fun getDataFromApi() {
        Toast.makeText(this, "Fetching Data", Toast.LENGTH_SHORT).show()
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
                R.drawable.default_icon,
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
                R.drawable.default_icon,
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
                R.drawable.default_icon,
                displayData.getString("name"),
                displayData.getString("description")
            )
            DataItems.billItems.add(currDataItem)
            billsAdapter.notifyItemInserted(DataItems.billItems.size - 1)
        }

    }
}