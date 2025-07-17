package com.lukatutberidze.finalproject.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.text.SimpleDateFormat
import java.util.*

class LeaveDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "leaves.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_LEAVES = "leaves"
        private const val KEY_ID = "id"
        private const val KEY_REASON = "reason"
        private const val KEY_DATE = "date"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE $TABLE_LEAVES($KEY_ID INTEGER PRIMARY KEY AUTOINCREMENT, $KEY_REASON TEXT, $KEY_DATE TEXT)"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_LEAVES")
        onCreate(db)
    }

    fun addLeave(reason: String) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_REASON, reason)
        contentValues.put(KEY_DATE, SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date()))
        db.insert(TABLE_LEAVES, null, contentValues)
        db.close()
    }

    fun getAllLeaves(): List<Leave> {
        val leaveList = ArrayList<Leave>()
        val selectQuery = "SELECT * FROM $TABLE_LEAVES"
        val db = this.readableDatabase
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor.moveToFirst()) {
            do {
                val leave = Leave(
                    cursor.getLong(cursor.getColumnIndex(KEY_ID)),
                    cursor.getString(cursor.getColumnIndex(KEY_REASON)),
                    cursor.getString(cursor.getColumnIndex(KEY_DATE))
                )
                leaveList.add(leave)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return leaveList
    }

    fun getLeavesCount(): Int {
        val countQuery = "SELECT * FROM $TABLE_LEAVES"
        val db = this.readableDatabase
        val cursor = db.rawQuery(countQuery, null)
        val count = cursor.count
        cursor.close()
        return count
    }
}