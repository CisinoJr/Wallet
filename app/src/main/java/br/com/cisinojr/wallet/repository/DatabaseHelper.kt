package br.com.cisinojr.wallet.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import br.com.cisinojr.wallet.constants.database.DatabaseCurrencyConstants
import br.com.cisinojr.wallet.constants.database.DatabaseUserConstants
import br.com.cisinojr.wallet.constants.database.DatabaseWalletConstants

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION: Int = 1
        private const val DATABASE_NAME: String = "wallet.db"
    }

    // USER TABLE
    private val createUserTable = """CREATE TABLE ${DatabaseUserConstants.USER.TABLE_NAME} (
        ${DatabaseUserConstants.COLUMNS.ID} INTEGER PRIMARY KEY AUTOINCREMENT,
        ${DatabaseUserConstants.COLUMNS.FULL_NAME} TEXT,
        ${DatabaseUserConstants.COLUMNS.EMAIL} TEXT,
        ${DatabaseUserConstants.COLUMNS.CPF} TEXT,
        ${DatabaseUserConstants.COLUMNS.PASSWORD} TEXT
    );"""

    // QUERY TO DROP USER TABLE
    private val deleteUserTable = "DROP TABLE IF EXISTS ${DatabaseUserConstants.USER.TABLE_NAME}"

    // WALLET TABLE
    private val createWalletTable = """CREATE TABLE ${DatabaseWalletConstants.WALLET.TABLE_NAME} (
        ${DatabaseWalletConstants.COLUMNS.ID} INTEGER PRIMARY KEY AUTOINCREMENT,
        ${DatabaseWalletConstants.COLUMNS.CURRENCY_ID} INTEGER,
        ${DatabaseWalletConstants.COLUMNS.BITCOIN_ID} INTEGER,
        ${DatabaseWalletConstants.COLUMNS.BRITA_ID} INTEGER
    );"""

    // QUERY TO DROP WALLET TABLE
    private val deleteWalletTable = "DROP TABLE IF EXISTS ${DatabaseWalletConstants.WALLET.TABLE_NAME}"

    // CURRENCY TABLE
    private val createCurrencyTable = """CREATE TABLE ${DatabaseCurrencyConstants.CURRENCY.TABLE_NAME} (
        ${DatabaseCurrencyConstants.COLUMNS.ID} INTEGER PRIMARY KEY AUTOINCREMENT,
        ${DatabaseCurrencyConstants.COLUMNS.BALANCE} INTEGER
    );"""

    // QUERY TO DROP USER TABLE
    private val deleteCurrencyTable = "DROP TABLE IF EXISTS ${DatabaseCurrencyConstants.CURRENCY.TABLE_NAME}"

    override fun onCreate(sqLite: SQLiteDatabase) {
        sqLite.execSQL(createUserTable)
        sqLite.execSQL(createWalletTable)
        sqLite.execSQL(createCurrencyTable)
    }

    override fun onUpgrade(sqLite: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Remove user table
        sqLite.execSQL(deleteUserTable)

        // Create user table
        sqLite.execSQL(createUserTable)

        // Create wallet table
        sqLite.execSQL(createWalletTable)

        // Create currency table
        sqLite.execSQL(createCurrencyTable)

        // Remove currency table
        sqLite.execSQL(deleteCurrencyTable)
    }
}