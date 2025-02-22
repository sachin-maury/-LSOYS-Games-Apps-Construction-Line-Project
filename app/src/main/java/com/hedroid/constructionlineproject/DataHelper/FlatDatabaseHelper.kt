package com.hedroid.constructionlineproject.DataHelper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.hedroid.constructionlineproject.Model.SellFlatItem

class FlatDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "flats.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "flats"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_ADDRESS = "address"
        private const val COLUMN_PRICE = "price"
        private const val COLUMN_IMAGE = "imageRes" // Store resource ID
    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE_QUERY = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NAME TEXT,
                $COLUMN_ADDRESS TEXT,
                $COLUMN_PRICE INTEGER,
                $COLUMN_IMAGE INTEGER
            )
        """.trimIndent()
        db.execSQL(CREATE_TABLE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addFlat(name: String, address: String, price: Int, imageRes: Int) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME, name)
            put(COLUMN_ADDRESS, address)
            put(COLUMN_PRICE, price)
            put(COLUMN_IMAGE, imageRes)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getFlats(): ArrayList<SellFlatItem> {
        val flatsList = ArrayList<SellFlatItem>()
        val db = readableDatabase
        val cursor = db.query(TABLE_NAME, null, null, null, null, null, null)

        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME))
            val address = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ADDRESS))
            val price = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PRICE))
            val imageRes = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_IMAGE)) // Retrieve image

            flatsList.add(SellFlatItem(id, name, address, price, imageRes))
        }
        cursor.close()
        db.close()
        return flatsList
    }
}
