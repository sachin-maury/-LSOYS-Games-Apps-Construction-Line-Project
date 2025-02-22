package com.hedroid.constructionlineproject.DataHelper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class CartDatabaseHelper(context: Context) : SQLiteOpenHelper(context, "CartDB", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE Cart (id INTEGER PRIMARY KEY AUTOINCREMENT, item TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS Cart")
        onCreate(db)
    }

    fun addItem(item: String) {
        val db = writableDatabase
        val values = ContentValues()
        values.put("item", item)
        db.insert("Cart", null, values)
        db.close()
    }

    fun getCartItems(): ArrayList<String> {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT item FROM Cart", null)
        val items = ArrayList<String>()
        while (cursor.moveToNext()) {
            items.add(cursor.getString(0))
        }
        cursor.close()
        return items
    }

    fun clearCart() {
        val db = writableDatabase
        db.execSQL("DELETE FROM Cart") // Clears the cart
        db.close()
    }
}