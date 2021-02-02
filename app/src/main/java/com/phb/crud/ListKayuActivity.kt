package com.phb.crud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_list_kayu.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class ListKayuActivity : AppCompatActivity() {

    private lateinit var adapter: RVAdapterKayu
    private var kayu = ArrayList<Kayu>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_kayu)

        adapter = RVAdapterKayu(this, kayu)
        recylerView.adapter = adapter

        getData()
        recylerView.layoutManager = LinearLayoutManager(this)
    }

    private fun getData() {
        database.use {
            kayu.clear()
            var result = select(Kayu.TABLE_KAYU)
            var dataKayu = result.parseList(classParser<Kayu>())
            kayu.addAll(dataKayu)
            adapter.notifyDataSetChanged()
        }
    }
}