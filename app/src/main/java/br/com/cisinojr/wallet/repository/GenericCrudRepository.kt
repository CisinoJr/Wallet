package br.com.cisinojr.wallet.repository

interface GenericCrudRepository<T> {

    fun save(content: T): Int
    fun update(content: T)
    fun find(id: Int)
    fun findAll()
    fun delete(id: Int)

}