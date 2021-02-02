package com.phb.crud

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class DBHelper(ctx: Context): ManagedSQLiteOpenHelper(ctx, "Kayu.db", null, 1) {
    companion object{
        private var instance: DBHelper? = null
        @Synchronized
        fun getInstance(ctx: Context) : DBHelper{

                if(instance == null){
                    instance = DBHelper(ctx.applicationContext)
                }
                return instance as DBHelper
            }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(Kayu.TABLE_KAYU, true,
        Kayu.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
        Kayu.NAMA to TEXT,
        Kayu.UKURAN to TEXT,
        Kayu.HARGA to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(Kayu.TABLE_KAYU, true)
    }
}

val Context.database : DBHelper
get() = DBHelper.getInstance(applicationContext)