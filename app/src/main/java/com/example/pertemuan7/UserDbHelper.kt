package com.example.pertemuan7

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UserDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private val DATABASE_NAME = "user.db"
        private val DATABASE_VERSION = 1

        private val SQL_CREATE_ENTRIES =
            " CREATE TABLE ${UserContract.UserEntry.Table_nama} ( " +
                    "${UserContract.UserEntry.Coloumn_Id} INTEGER PRIMARY  KEY AUTOINCREMENT,  " +
                    "${UserContract.UserEntry.Coloumn_Email} VARCHAR(255), "+
                    "${UserContract.UserEntry.Coloumn_Password} VARCHAR(255))"

        private val SQL_DELETE_ENTRIES = " DROP TABLE IF EXISTS ${UserContract.UserEntry.Table_nama} "
    }

    override fun onCreate(db:SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db:SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
    }

    fun insertUser(user: User) {
        val db = writableDatabase

        val sql = " INSERT INTO ${UserContract.UserEntry.Table_nama} " +
                "  (${UserContract.UserEntry.Coloumn_Id}, " +
                "${UserContract.UserEntry.Coloumn_Email}, " +
                "${UserContract.UserEntry.Coloumn_Password} ) " +
                " VALUES (null, '${user.email}','${user.password}' ) "

        db.execSQL(sql)
        db.close()

    }

    fun getUser (email: String, password: String): User? {
        val db = readableDatabase
        val sql = " SELECT * FROM ${UserContract.UserEntry.Table_nama} WHERE ${UserContract.UserEntry.Coloumn_Email} ='${email}' AND ${UserContract.UserEntry.Coloumn_Password} ='${password} ' "
        val cursor = db.rawQuery(sql, null)

        var user: User? = null

        if (cursor.moveToFirst()){
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(UserContract.UserEntry.Coloumn_Id))
            val email = cursor.getString(cursor.getColumnIndexOrThrow(UserContract.UserEntry.Coloumn_Email))
            val password = cursor.getString(cursor.getColumnIndexOrThrow(UserContract.UserEntry.Coloumn_Password))

            user = User(id, email, password)
        }

        cursor.close()
        db.close()
        return user
    }
}