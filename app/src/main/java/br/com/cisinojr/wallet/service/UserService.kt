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


    fun validateUser(user: User)

}