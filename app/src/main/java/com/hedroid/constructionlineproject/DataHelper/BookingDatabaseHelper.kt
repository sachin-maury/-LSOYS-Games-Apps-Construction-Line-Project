package com.hedroid.constructionlineproject.DataHelper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BookingDatabaseHelper(context: Context) : SQLiteOpenHelper(context, "BookingDB", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE Bookings (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, date TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS Bookings")
        onCreate(db)
    }

    fun addBooking(name: String, date: String) {
        val db = writableDatabase
        val values = ContentValues()
        values.put("name", name)
        values.put("date", date)
        db.insert("Bookings", null, values)
        db.close()
    }

    fun getBookings(): ArrayList<String> {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT name, date FROM Bookings", null)
        val bookings = ArrayList<String>()
        while (cursor.moveToNext()) {
            bookings.add("Name: ${cursor.getString(0)}, Date: ${cursor.getString(1)}")
        }
        cursor.close()
        return bookings
    }
}

