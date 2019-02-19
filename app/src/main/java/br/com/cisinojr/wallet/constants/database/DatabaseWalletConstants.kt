package br.com.cisinojr.wallet.constants.database

/**
 *
 * Created by cisino.gomes on 2/18/2019
 *
 */
class DatabaseWalletConstants {

    object WALLET {
        const val TABLE_NAME = "wallet"
    }

    object COLUMNS {
        const val ID = "id"
        const val SECURITY_PASSWORD = "security_password"
        const val CURRENCY_ID = "currency_id"
        const val BITCOIN_ID = "bitcoin_id"
        const val BRITA_ID = "brita_id"
    }
}