package br.com.cisinojr.wallet.service

import android.content.Context
import br.com.cisinojr.wallet.R
import br.com.cisinojr.wallet.service.validators.CpfValidator
import br.com.cisinojr.wallet.domain.User
import br.com.cisinojr.wallet.exceptions.BusinessException
import br.com.cisinojr.wallet.exceptions.RepositoryException
import br.com.cisinojr.wallet.exceptions.ValidationException
import br.com.cisinojr.wallet.repository.UserRepository

/**
 *
 * Created by cisino.gomes on 2/13/2019
 *
 */
class UserServiceImpl(val context: Context) : UserService {

    private val userRepository: UserRepository = UserRepository.getInstance(context)

    /**
     * Save the object into database
     *
     * @param user Entity to save
     */
    override fun save(user: User): Int? {
        val result: Int?
        try {

            if (validateEmailBeforeRegisterUser(user.email!!)) {
                throw ValidationException("Email já está em uso. Favor tente com outro!")
            }

            result = userRepository.save(user)
        } catch (repositoryException: RepositoryException) {
            throw BusinessException(context.getString(R.string.validation_register_error))
        }

        return result
    }

    /**
     * Update the object into database
     *
     * @param user Entity to save
     */
    override fun update(user: User): Int? {
        return null
    }

    /**
     * Save the object into database
     *
     * @param id Entity to save
     */
    override fun find(id: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * Return a list of users
     *
     * return list of entities
     */
    override fun findAll() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * Delete the user by id
     *
     * @param id User id
     */
    override fun delete(id: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * Check if user information is valid
     *
     * @param user Entity to validate
     */
    override fun validateUser(user: User) {

        if (user.fullName.isNullOrBlank() || user.fullName.isNullOrEmpty()) {
            throw ValidationException(context.getString(R.string.validation_fullName))
        }

        if (!CpfValidator.myValidateCPF(user.cpf!!)) {
            throw ValidationException(context.getString(R.string.validation_cpf))
        }

        if (user.email.isNullOrBlank() || user.email.isNullOrEmpty()) {
            throw ValidationException(context.getString(R.string.validation_email))
        }

        if (user.password.isNullOrBlank() || user.email.isNullOrEmpty()) {
            throw ValidationException(context.getString(R.string.validation_password))
        }
    }

    /**
     * Check if the input login information is valid
     *
     * @param email User's email
     * @param password User's password
     */
    override fun validateUserCredentials(email: String, password: String): User? {
        val user: User?
        try {
            if (email.isBlank() || email.isEmpty()) {
                throw ValidationException(context.getString(R.string.validation_login_mail))
            }

            if (password.isBlank() || password.isEmpty()) {
                throw ValidationException(context.getString(R.string.validation_login_password))
            }

            user = userRepository.getUserCredentials(email, password)

            if (user == null) {
                throw ValidationException(context.getString(R.string.validation_login_user_info_exists))
            }
        } catch (repositoryException: RepositoryException) {
            throw BusinessException(context.getString(R.string.validation_login_error))
        }

        return user
    }

    fun validateEmailBeforeRegisterUser(email: String): Boolean {
        val emailExists: Boolean

        try {
            emailExists = userRepository.emailExists(email)
        } catch (repositoryException: RepositoryException) {
            throw BusinessException(context.getString(R.string.validation_login_error))
        }

        return emailExists
    }
}