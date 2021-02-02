package com.phb.crud

data class Balita(var id: Long?, var nama: String?, var umur: String?, var penyakit: String?){
    companion object{
        const val TABLE_BALITA: String = "TABLE_BALITA"
        const val ID: String = "ID_"
        const val NAMA: String = "NAMA"
        const val UMUR: String = "UMUR"
        const val PENYAKIT: String = "PENYAKIT"

    }
}