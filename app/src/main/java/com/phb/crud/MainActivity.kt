package com.phb.crud

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
        var oldUmur = intent.getStringExtra("oldUmur")
        var oldPenyakit = intent.getStringExtra("oldPenyakit")


        if (oldUmur.isNullOrBlank()){
            buttonUpdate.isEnabled = false
        }else{
            buttonSimpan.isEnabled = false
            editTextNama.setText(oldNama)
            editTextUmur.setText(oldUmur)
            editTextPenyakit.setText(oldPenyakit)
        }

        buttonSimpan.setOnClickListener {
            addDataBalita()

            // clear data
            clearData()
        }

        buttonLihatData.setOnClickListener {
            startActivity<ListBalitaActivity>()
        }

        buttonUpdate.setOnClickListener {
            database.use {
                update(Balita.TABLE_BALITA,
                    Balita.NAMA to editTextNama.text.toString(),
                    Balita.UMUR to editTextUmur.text.toString(),
                    Balita.PENYAKIT to editTextPenyakit.text.toString())
                    .whereArgs("${Balita.NAMA} = {nama}",
                    "nama" to oldNama
                    ).exec()
            }

            // clear data
            clearData()
            toast("Data Diupdate")
        }
    }

    private fun addDataBalita() {
        database.use {
            insert(Balita.TABLE_BALITA,
                Balita.NAMA to editTextNama.text.toString(),
                Balita.UMUR to editTextUmur.text.toString(),
                Balita.PENYAKIT to editTextPenyakit.text.toString()
            )
            toast("Data berhasil disimpan!")
        }
    }

    fun clearData(){
        editTextNama.text.clear()
        editTextUmur.text.clear()
        editTextPenyakit.text.clear()
    }
}