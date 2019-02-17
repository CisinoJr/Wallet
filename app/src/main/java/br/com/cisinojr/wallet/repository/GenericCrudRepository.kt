package br.com.cisinojr.wallet.repository

interface GenericCrudRepository<T> {

    fun save(content: T): Int?
    fun update(content: T): Int?
    fun updateUserPassword(userId: String , password: String): Int?
    fun find(id: Int): T?
    fun findAll(searchContent: T?)
    fun delete(id: Int)

}