package br.com.cisinojr.wallet.repository

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.CursorIndexOutOfBoundsException
import android.database.sqlite.SQLiteException
import br.com.cisinojr.wallet.constants.database.DatabaseUserConstants
import br.com.cisinojr.wallet.domain.User
import br.com.cisinojr.wallet.exceptions.RepositoryException
import java.lang.Exception

class UserRepository private constructor(context: Context) : GenericCrudRepository<User> {

    private var databaseHelper: DatabaseHelper = DatabaseHelper(context)

    /**
     * Insert User on database
     * @param content User object to be saved
     * @return user id
     */
    override fun save(content: User): Int? {
        val id: Int?

        try {
            val database = databaseHelper.writableDatabase
            val insertValues = ContentValues()
            insertValues.put(DatabaseUserConstants.COLUMNS.FULL_NAME, content.fullName)
            insertValues.put(DatabaseUserConstants.COLUMNS.EMAIL, content.email)
            insertValues.put(DatabaseUserConstants.COLUMNS.CPF, content.cpf)
            insertValues.put(DatabaseUserConstants.COLUMNS.PASSWORD, content.password)

            id = database.insert(DatabaseUserConstants.USER.TABLE_NAME, null, insertValues).toInt()
        } catch (exception: SQLiteException) {
            throw RepositoryException("Ocorreu um erro ao inserir usuário no banco")
        }

        return id
    }

    override fun update(content: User): Int? {
        // TODO: Implement update logic
        return 0
    }

    /**
     * Find user by ID
     *
     * @param id The entity ID
     * @return the entity
     */
    override fun find(id: Int): User? {
        val user: User?

        try {
            val cursor: Cursor
            val database = databaseHelper.readableDatabase
            val columns = DatabaseUserConstants.COLUMNS.getColumns() // Array of columns
            val selection = "${DatabaseUserConstants.COLUMNS.ID} = ?" // Filter user by id: where id = :id
            val selectionArgs = arrayOf(id.toString()) // Filter arguments

            // Execute query
            cursor = database.query(
                DatabaseUserConstants.USER.TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null
            )

            // create user object
            user = createUser(cursor)

        } catch (exception: SQLiteException) {
            throw RepositoryException("Ocorreu um erro ao executar query")
        } catch (exception: Exception) {
            throw RepositoryException("Ocorreu um erro ao recuperar dados do usuário")
        }

        return user
    }

    /**
     * Check with e-mail exists before creating new user
     *
     * @param email Typed email
     * return true if email was found
     */
    fun emailExists(email: String): Boolean {
        val emailExists: Boolean

        try {
            val cursor: Cursor
            val database = databaseHelper.readableDatabase
            val columns = DatabaseUserConstants.COLUMNS.getColumns() // Array of columns
            val selection = "${DatabaseUserConstants.COLUMNS.EMAIL} = ?"
            val selectionArgs = arrayOf(email) // Filter arguments

            // Execute query
            cursor = database.query(
                DatabaseUserConstants.USER.TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null
            )

            emailExists = cursor.count > 0

            cursor.close()
        } catch (exception: SQLiteException) {
            throw RepositoryException("Ocorreu um erro ao executar query")
        } catch (exception: Exception) {
            throw RepositoryException("Ocorreu um erro ao recuperar dados do usuário")
        }

        return emailExists
    }

    /**
     * Find user by ID
     *
     * @param id The entity ID
     * @return the entity
     */
    fun getUserCredentials(email: String, password: String): User? {
        // TODO: Implement find, by id
        val user: User?

        try {
            val cursor: Cursor
            val database = databaseHelper.readableDatabase
            val columns = DatabaseUserConstants.COLUMNS.getColumns() // Array of columns
            val selection = """${DatabaseUserConstants.COLUMNS.EMAIL} = ?
                AND ${DatabaseUserConstants.COLUMNS.PASSWORD} = ?""" // Filter user by id: where id = :id
            val selectionArgs = arrayOf(email, password) // Filter arguments

            // Execute query
            cursor = database.query(
                DatabaseUserConstants.USER.TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null
            )

            // create user object
            user = createUser(cursor)

        } catch (exception: SQLiteException) {
            throw RepositoryException("Ocorreu um erro ao executar query")
        } catch (exception: Exception) {
            throw RepositoryException("Ocorreu um erro ao recuperar dados do usuário")
        }

        return user
    }

    override fun findAll(searchContent: User?) {
        // TODO: Implement findAll, return a list of users
    }

    override fun delete(id: Int) {
        // TODO: Implement delete logic
    }

    /**
     * Uses cursor to get user information then closes cursor
     *
     * @param cursor Cursor
     */
    private fun createUser(cursor: Cursor): User? {
        var user: User? = null

        try {
            if (cursor.count > 0) {
                cursor.moveToFirst()
                val userId = cursor.getInt(cursor.getColumnIndex(DatabaseUserConstants.COLUMNS.ID))
                val fullName = cursor.getString(cursor.getColumnIndex(DatabaseUserConstants.COLUMNS.FULL_NAME))
                val email = cursor.getString(cursor.getColumnIndex(DatabaseUserConstants.COLUMNS.EMAIL))
                val cpf = cursor.getString(cursor.getColumnIndex(DatabaseUserConstants.COLUMNS.CPF))
                val password = cursor.getString(cursor.getColumnIndex(DatabaseUserConstants.COLUMNS.PASSWORD))

                user = User(userId, fullName, email, cpf, password)
            }

            cursor.close()
        } catch (exception: CursorIndexOutOfBoundsException) {
            throw CursorIndexOutOfBoundsException("Ocorreu um error acessando cursor")
        }

        return user
    }

    companion object {
        private var instance: UserRepository? = null

        fun getInstance(context: Context): UserRepository {
            if (instance === null) {
                instance = UserRepository(context)
            }

            return instance as UserRepository
        }
    }

}