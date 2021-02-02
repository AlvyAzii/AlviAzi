package com.phb.crud

data class Kayu(var id: Long?, var nama: String?, var ukuran: String?, var harga: String?){
    companion object{
        const val TABLE_KAYU: String = "TABLE_KAYU"
        const val ID: String = "ID_"
        const val NAMA: String = "NAMA"
        const val UKURAN: String = "UKURAN"
        const val HARGA: String = "HARGA"

    }
}