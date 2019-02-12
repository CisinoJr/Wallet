package br.com.cisinojr.wallet.domain

data class User(
    val id: Int,
    val fullName: String,
    val email: String,
    val cpf: String,
    val password: String
)