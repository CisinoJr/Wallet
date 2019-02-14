package br.com.cisinojr.wallet.service

import android.content.Context
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

            // TODO: Implementar validação de email (verificar se email ja está cadasstrado)
            // TODO: Validar se já existe usuário com o cpf digitado

            result = userRepository.save(user)
        } catch (businessException: RepositoryException) {
            throw BusinessException("Ocorreu um erro ao registrar. Tente novamente!")
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

    override fun validateUser(user: User) {
        if (!CpfValidator.myValidateCPF(user.cpf!!)) {
            throw ValidationException("CPF inválido")
        }

        if (user.fullName.isNullOrBlank() || user.fullName.isNullOrEmpty()) {
            throw ValidationException("Favor informar nome completo")
        }

        if (user.email.isNullOrBlank() || user.email.isNullOrEmpty()) {
            throw ValidationException("Favor informar e-mail")
        }

        if (user.password.isNullOrBlank() || user.email.isNullOrEmpty()) {
            throw ValidationException("Favor informar uma senha")
        }
    }
}