package br.com.cisinojr.wallet.exceptions

import android.database.sqlite.SQLiteException

class RepositoryException(message: String): SQLiteException(message)
