package br.com.cisinojr.wallet.constants.database

class DatabaseUserConstants {

    object USER {
        const val TABLE_NAME = "user"
    }

    object COLUMNS {
        const val ID = "id"
        const val FULL_NAME = "fullname"
        const val EMAIL = "email"
        const val CPF = "cpf"
        const val PASSWORD = "password"

        fun getColumns(): Array<String> {
            return arrayOf(ID, FULL_NAME, EMAIL, CPF, PASSWORD)
        }
    }

}