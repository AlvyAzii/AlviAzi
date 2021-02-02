package com.phb.crud

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_list.view.*
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class RVAdapterKayu(val context: Context, val items: ArrayList<Kayu>) : RecyclerView.Adapter<RVAdapterKayu.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        fun bindItem(items: Kayu){
            itemView.namaKayu.text = items.nama
            itemView.ukuranKayu.text = items.ukuran
            itemView.hargaKayu.text = items.harga

            itemView.btnEdit.setOnClickListener {
                itemView.context.startActivity<MainActivity>(
                    "oldNama" to items.nama,
                    "oldUkuran" to items.ukuran,
                    "oldHarga" to items.harga
                )
            }

            itemView.btnHapus.setOnClickListener {
                itemView.context.database.use {
                    delete(Kayu.TABLE_KAYU, "(${Kayu.NAMA} = {nama})",
                        "nama" to items.nama.toString())
                }
                itemView.context.toast("Data Dihapus")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position])
    }
}