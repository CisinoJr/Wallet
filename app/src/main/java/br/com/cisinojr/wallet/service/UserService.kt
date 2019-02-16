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
     * Update the object into database
     *
     * @param user Entity to save
     */
    fun update(user: User): Int?

    /**
     * Save the object into database
     *
     * @param id Entity to save
     */
    fun find(id: Int)

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