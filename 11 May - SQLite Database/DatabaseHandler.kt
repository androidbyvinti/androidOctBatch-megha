package com.bmpl.sqlitedatabase_nishamohit

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.util.*

//SQLite --> Sqliteopenhelper class --> abstract --> two abstract --> one method used to create table and second method is used to upgrade database
class DatabaseHandler(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {


    // create your table here
    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE)
    }

    // upgrade your table here
    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        sqLiteDatabase.execSQL(DROP_TABLE)
    }

    //CRUD --> Create , Read, Delete, Update
    internal fun addDetails(details: Details) {
        //insert -->
        val contentValues = ContentValues()
        val name = details.name
        val phn = details.phn
        val id = details.id

        Log.i("Database Handler", name)

        contentValues.put(COLUMN_ID, id)
        contentValues.put(COLUMN_NAME, name)
        contentValues.put(COLUMN_PHN, phn)

        // open database for writing data into it -> opening a connection for writing data
        val sqLiteDatabase = this.writableDatabase

        sqLiteDatabase.insert(TABLE_NAME, null, contentValues)
        sqLiteDatabase.close()
    }

    internal fun readData(): List<Details> {
        val arrayList = ArrayList<Details>()

        val sqLiteDatabase = this.readableDatabase
        val readQuery = "Select * from $TABLE_NAME"

        val cursor = sqLiteDatabase.rawQuery(readQuery, null) // ContentProvider --> Content Resolver and Content Values and Cursor

        while (cursor.moveToNext()) {
            val details = Details()
            details.id = Integer.parseInt(cursor.getString(0)) // 1
            details.name = cursor.getString(1)
            details.phn = java.lang.Long.parseLong(cursor.getString(2))

            //details.setId(Integer.parseInt(id));
            //details.setName(name);

            arrayList.add(details)
        }
        return arrayList
    }

    internal fun updateData(details: Details) {
        val sqLiteDatabase = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(COLUMN_ID, details.id)
        contentValues.put(COLUMN_NAME, details.name)
        contentValues.put(COLUMN_PHN, details.phn)
        // update table_name where                                   // id==id_value
        sqLiteDatabase.update(TABLE_NAME, contentValues, "id=?", arrayOf(details.id.toString()))
        sqLiteDatabase.close()
    }

    internal fun deleteData(details: Details) {
        val sqLiteDatabase = this.writableDatabase
        sqLiteDatabase.delete(TABLE_NAME, "id=? ", arrayOf(details.id.toString()))
        sqLiteDatabase.close()
    }

    companion object {

        private val DATABASE_NAME = "mydatabase"
        private val TABLE_NAME = "user_details"
        private val DATABASE_VERSION = 1
        private val COLUMN_NAME = "Name"
        private val COLUMN_ID = "ID"
        private val COLUMN_PHN = "PHONE_NO"
        private val CREATE_TABLE = "Create table " + TABLE_NAME + " ( " +
                COLUMN_ID + " INTEGER PRIMARY_KEY, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_PHN + " INTEGER )"

        private val DROP_TABLE = "DROP TABLE $TABLE_NAME IF EXISTS"
    }
}