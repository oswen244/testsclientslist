package com.test.testclientslist.data.localDB

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import org.json.JSONObject

class SqliteHelper(context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int) : SQLiteOpenHelper(context, name, factory, version) {

    // Database Version
    private val DATABASE_VERSION = 1
    // Database Name
    private val DATABASE_NAME = "Prospects"


    private val KEY_VALUE = "value"

    private val COLUMNS = arrayOf(KEY_VALUE)

    override fun onCreate(db: SQLiteDatabase?) {
        // SQL statement to create table
        var CREATE_ITEM_TABLE = "CREATE TABLE prospects ( " +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "value TEXT)"

        db?.execSQL(CREATE_ITEM_TABLE)

        CREATE_ITEM_TABLE = "CREATE TABLE log ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "value TEXT, " +
                "dateTime TEXT)"

        db?.execSQL(CREATE_ITEM_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Drop older answers table if existed
        db?.execSQL("DROP TABLE IF EXISTS answers")
    }

    fun addItem(table: String, value: String){
        // 1. get reference to writable DB
        val db = this.writableDatabase

        // 2. create ContentValues to add key "column"/value
        val values = ContentValues()
        values.put(KEY_VALUE, value)

        db.insert(table, null, values)
        db.close()
    }

    fun getItem(table: String, pos: String): String{
        var result = ""

        val db = this.readableDatabase

        val cursor = db.query(table, // a. table
            COLUMNS, // b. column names
            "_id = ?", // c. selections
            arrayOf(pos), // g. order by
            null, null, null, null)

        if(cursor.moveToFirst()){
            result = cursor.getString(0)
        }

        cursor.close()

        return result
    }

    fun getItems(table: String): ArrayList<String> {
        val result: ArrayList<String> = ArrayList()

        // 1. get reference to readable DB
        val db = this.readableDatabase

        // 2. build query
        val cursor = db.query(table, // a. table
                COLUMNS, // b. column names
                "", // c. selections
                null, // g. order by
                null, null, null, null)

        if(cursor != null && cursor.count != 0){
            cursor.moveToFirst()
            while (cursor.moveToNext()){
                result.add(cursor.getString(0))
            }

            cursor.close()
        }

        return result

    }

    fun deleteAllRows(table: String) {
        val db = this.writableDatabase
        db.execSQL("delete from $table")
    }

}