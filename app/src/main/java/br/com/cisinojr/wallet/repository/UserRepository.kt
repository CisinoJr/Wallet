package br.com.cisinojr.wallet.repository

import android.content.ContentValues
import android.content.Context
import br.com.cisinojr.wallet.constants.database.DatabaseUserConstants
import br.com.cisinojr.wallet.domain.User

class UserRepository private constructor(context: Context) : GenericCrudRepository<User> {

    private var databaseHelper : DatabaseHelper = DatabaseHelper(context)

    override fun save(content: User) : Int {
        val db = databaseHelper.writableDatabase
        val insertValues = ContentValues()
        insertValues.put(DatabaseUserConstants.COLUMNS.FULL_NAME, content.fullName)
        insertValues.put(DatabaseUserConstants.COLUMNS.EMAIL, content.email)
        insertValues.put(DatabaseUserConstants.COLUMNS.CPF, content.cpf)
        insertValues.put(DatabaseUserConstants.COLUMNS.PASSWORD, content.password)

        return db.insert(DatabaseUserConstants.USER.TABLE_NAME, null, insertValues).toInt()
    }

    override fun update(content: User) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun find(id: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun findAll() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(id: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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