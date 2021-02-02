package com.phb.crud

import android.app.DownloadManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.update
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var oldNama = intent.getStringExtra("oldNama")
        var oldUkuran = intent.getStringExtra("oldUkuran")
        var oldHarga = intent.getStringExtra("oldHarga")


        if (oldUkuran.isNullOrBlank()){
            buttonUpdate.isEnabled = false
        }else{
            buttonSimpan.isEnabled = false
            editTextNama.setText(oldNama)
            editTextUkuran.setText(oldUkuran)
            editTextHarga.setText(oldHarga)
        }

        buttonSimpan.setOnClickListener {
            addDataKayu()

            // clear data
            clearData()
        }

        buttonLihatData.setOnClickListener {
            startActivity<ListKayuActivity>()
        }

        buttonUpdate.setOnClickListener {
            database.use {
                update(Kayu.TABLE_KAYU,
                    Kayu.NAMA to editTextNama.text.toString(),
                    Kayu.UKURAN to editTextUkuran.text.toString(),
                    Kayu.HARGA to editTextHarga.text.toString())
                    .whereArgs("${Kayu.NAMA} = {nama}",
                    "nama" to oldNama
                    ).exec()
            }

            // clear data
            clearData()
            toast("Data Diupdate")
        }
    }

    private fun addDataKayu() {
        database.use {
            insert(Kayu.TABLE_KAYU,
                Kayu.NAMA to editTextNama.text.toString(),
                Kayu.UKURAN to editTextUkuran.text.toString(),
                Kayu.HARGA to editTextHarga.text.toString()
            )
            toast("Data berhasil disimpan!")
        }
    }

    fun clearData(){
        editTextNama.text.clear()
        editTextUkuran.text.clear()
        editTextHarga.text.clear()
    }
}