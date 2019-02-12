package br.com.cisinojr.wallet.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import br.com.cisinojr.wallet.constants.database.DatabaseUserConstants

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION: Int = 1
        private const val DATABASE_NAME: String = "wallet.db"
    }

    private val createUserTable = """CREATE TABLE ${DatabaseUserConstants.USER.TABLE_NAME} (
        ${DatabaseUserConstants.COLUMNS.ID} INTEGER PRIMARY KEY AUTOINCREMENT,
        ${DatabaseUserConstants.COLUMNS.FULLNAME} TEXT,
        ${DatabaseUserConstants.COLUMNS.EMAIL} TEXT,
        ${DatabaseUserConstants.COLUMNS.CPF} TEXT,
        ${DatabaseUserConstants.COLUMNS.PASSWORD} TEXT
    );"""

    private val deleteUserTable = "DROP TABLE IF EXISTS ${DatabaseUserConstants.USER.TABLE_NAME}"

    override fun onCreate(sqLite: SQLiteDatabase) {
        sqLite.execSQL(createUserTable)
    }

    override fun onUpgrade(sqLite: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Remove user table
        sqLite.execSQL(deleteUserTable)

        // Create user table
        sqLite.execSQL(createUserTable)
    }
}