package br.com.cisinojr.wallet.service

import br.com.cisinojr.wallet.domain.User

interface UserService {

    /**
     * Save the object into database
     *
     * @param user Entity to save
     */
    fun save(user: User): Int?

    /**
     * Update the user password
     *
     * @param password to update
     */
    fun updateUserPassword(password: String): Int?

    /**
     * Save the object into database
     *
     * return user information
     */
    fun find(): User

    /**
     * Return a list of users
     *
     * return list of entities
     */
    fun findAll()

    /**
     * Delete the user by id
     *
     * @param id User id
     */
    fun delete(id: Int)

    /**
     * Check if user information is valid
     *
     * @param user Entity to validate
     */
    fun validateUser(user: User)

    /**
     * Check if the input login information is valid
     *
     * @param email User's email
     * @param password User's password
     */
    fun validateUserCredentials(email: String, password: String): Boolean

    /**
     * Check if user information exists into shared preferences and automatically log-in user
     *
     * return true if user information exists
     */
    fun isUserLoggedIn(): Boolean

    /**
     * Log out user from the app
     *
     */
    fun logoutUser()
}