package com.phb.crud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_list_balita.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class ListBalitaActivity : AppCompatActivity() {

    private lateinit var adapter: RVAdapterBalita
    private var balita = ArrayList<Balita>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_balita)

        adapter = RVAdapterBalita(this, balita)
        recylerView.adapter = adapter

        getData()
        recylerView.layoutManager = LinearLayoutManager(this)
    }

    private fun getData() {
        database.use {
            balita.clear()
            var result = select(Balita.TABLE_BALITA)
            var dataKayu = result.parseList(classParser<Balita>())
            balita.addAll(dataKayu)
            adapter.notifyDataSetChanged()
        }
    }
}