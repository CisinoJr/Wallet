package br.com.cisinojr.wallet.repository

import android.content.ContentValues
import android.content.Context
import br.com.cisinojr.wallet.constants.database.DatabaseUserConstants
import br.com.cisinojr.wallet.domain.User

class UserRepository private constructor(context: Context) : GenericCrudRepository<User> {

    private var databaseHelper: DatabaseHelper = DatabaseHelper(context)

    override fun save(content: User): Int {
        val db = databaseHelper.writableDatabase
        val insertValues = ContentValues()
        insertValues.put(DatabaseUserConstants.COLUMNS.FULL_NAME, content.fullName)
        insertValues.put(DatabaseUserConstants.COLUMNS.EMAIL, content.email)
        insertValues.put(DatabaseUserConstants.COLUMNS.CPF, content.cpf)
        insertValues.put(DatabaseUserConstants.COLUMNS.PASSWORD, content.password)

        return db.insert(DatabaseUserConstants.USER.TABLE_NAME, null, insertValues).toInt()
    }

    override fun update(content: User): Int {
        // TODO: Implement update logic
        return 0
    }

    override fun find(id: Int): User? {
        // TODO: Implement find, by id
        return null
    }

    override fun findAll(searchContent: User?) {
        // TODO: Implement findAll, return a list of users
    }

    override fun delete(id: Int) {
        // TODO: Implement delete logic
    }

    companion object {
        private var instance: UserRepository? = null

        fun getContext(context: Context): UserRepository {
            if (instance === null) {
                instance = UserRepository(context)
            }

            return instance as UserRepository
        }
    }

}