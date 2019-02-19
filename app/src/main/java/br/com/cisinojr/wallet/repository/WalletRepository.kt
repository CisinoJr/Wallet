package br.com.cisinojr.wallet.repository

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteException
import br.com.cisinojr.wallet.constants.database.DatabaseUserConstants
import br.com.cisinojr.wallet.constants.database.DatabaseWalletConstants
import br.com.cisinojr.wallet.domain.Wallet
import br.com.cisinojr.wallet.exceptions.RepositoryException

/**
 *
 * Created by cisino.gomes on 2/18/2019
 *
 */
class WalletRepository private constructor(context: Context) {

    private var databaseHelper: DatabaseHelper = DatabaseHelper(context)

    /**
     * Insert Wallet on database
     *
     * @param wallet Wallet object to be saved
     * @return wallet id
     */
    fun save(wallet: Wallet): Int? {
        val id: Int?

        try {
            val database = databaseHelper.writableDatabase
            val insertValues = ContentValues()

            with(insertValues) {
                put(DatabaseWalletConstants.COLUMNS.ID, wallet.id)
                put(DatabaseWalletConstants.COLUMNS.SECURITY_PASSWORD, wallet.securityPassword)
                put(DatabaseWalletConstants.COLUMNS.CURRENCY_ID, wallet.currency.id)
                put(DatabaseWalletConstants.COLUMNS.BITCOIN_ID, wallet.bitcoin.id)
                put(DatabaseWalletConstants.COLUMNS.BRITA_ID, wallet.brita.id)
            }

            id = database.insert(DatabaseWalletConstants.WALLET.TABLE_NAME, null, insertValues).toInt()
        } catch (exception: SQLiteException) {
            throw RepositoryException("Ocorreu um erro ao inserir usuário no banco")
        }

        return id
    }


}