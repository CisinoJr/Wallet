package br.com.cisinojr.wallet.service

import android.content.Context
import br.com.cisinojr.wallet.R
import br.com.cisinojr.wallet.constants.tasks.TaskConstants
import br.com.cisinojr.wallet.service.validators.CpfValidator
import br.com.cisinojr.wallet.domain.User
import br.com.cisinojr.wallet.exceptions.BusinessException
import br.com.cisinojr.wallet.exceptions.RepositoryException
import br.com.cisinojr.wallet.exceptions.ValidationException
import br.com.cisinojr.wallet.repository.UserRepository
import br.com.cisinojr.wallet.service.util.SecurityPreferences

/**
 *
 * Created by cisino.gomes on 2/13/2019
 *
 */
class UserServiceImpl(val context: Context) : UserService {

    private val userRepository: UserRepository = UserRepository.getInstance(context)
    private val securityPreferences: SecurityPreferences = SecurityPreferences(context)

    /**
     * Save the object into database
     *
     * @param user Entity to save
     */
    override fun save(user: User): Int? {
        val result: Int?
        try {

            if (validateEmailBeforeRegisterUser(user.email!!)) {
                throw ValidationException(context.getString(R.string.register_validation_email_exists))
            }

            result = userRepository.save(user)

            // store user information into shared preferences
            storeUserInformation(user)

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
    override fun validateUserCredentials(email: String, password: String): Boolean {
        val result: Boolean

        try {

            if (email.isBlank() || email.isEmpty()) {
                throw ValidationException(context.getString(R.string.validation_login_mail))
            }

            if (password.isBlank() || password.isEmpty()) {
                throw ValidationException(context.getString(R.string.validation_login_password))
            }

            val user: User? = userRepository.getUserCredentials(email, password)

            if (user != null) {
                // store user information into shared preferences
                storeUserInformation(user)

                result = true
            } else {
                throw ValidationException(context.getString(R.string.validation_login_user_info_exists))
            }
        } catch (repositoryException: RepositoryException) {
            throw BusinessException(context.getString(R.string.validation_login_error))
        }

        return result
    }

    /**
     * Check if user information exists into shared preferences and automatically log-in user
     *
     * @param user User information
     * return true if user information exists
     */
    override fun isUserLoggedIn(): Boolean {
        return try {
            val userId = securityPreferences.getStoredString(TaskConstants.KEY.USER_ID)

            var userFromDatabase: User? = null

            if (userId != "") {
                userFromDatabase = userRepository.find(userId.toInt())
            }

            userFromDatabase != null
        } catch (exception: Exception) {
            throw BusinessException(context.getString(R.string.validation_login_error))
        }
    }

    /**
     * Log out user from the app
     *
     */
    override fun logoutUser() {
        try {
            removeUserInformation()
        } catch (exception: Exception) {
            throw BusinessException(context.getString(R.string.validation_logout_error))
        }
    }

    /**
     * Check if email is already in use before register new user
     *
     * @param email The email to validate
     * @return true if exists a user with the email
     */
    private fun validateEmailBeforeRegisterUser(email: String): Boolean {
        val emailExists: Boolean

        try {
            emailExists = userRepository.emailExists(email)
        } catch (repositoryException: RepositoryException) {
            throw BusinessException(context.getString(R.string.validation_login_error))
        }

        return emailExists
    }

    /**
     * Stores user information into SharedPreferences to hold user logged in
     * @param user User
     */
    private fun storeUserInformation(user: User) {
        with(securityPreferences) {
            storeString(TaskConstants.KEY.USER_ID, user.id.toString())
            storeString(TaskConstants.KEY.USER_FULLNAME, user.fullName!!)
            storeString(TaskConstants.KEY.USER_EMAIL, user.email!!)
        }
    }

    /**
     * Remove user information from SharedPreferences to logout
     *
     */
    private fun removeUserInformation() {
        with(securityPreferences) {
            removeStoredString(TaskConstants.KEY.USER_ID)
            removeStoredString(TaskConstants.KEY.USER_FULLNAME)
            removeStoredString(TaskConstants.KEY.USER_EMAIL)
        }
    }
}