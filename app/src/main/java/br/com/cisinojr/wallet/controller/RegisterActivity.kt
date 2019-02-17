package br.com.cisinojr.wallet.controller

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import br.com.cisinojr.wallet.R
import br.com.cisinojr.wallet.controller.util.CpfMask
import br.com.cisinojr.wallet.controller.util.ValidationUtil
import br.com.cisinojr.wallet.domain.User
import br.com.cisinojr.wallet.exceptions.BusinessException
import br.com.cisinojr.wallet.exceptions.ValidationException
import br.com.cisinojr.wallet.service.UserService
import br.com.cisinojr.wallet.service.UserServiceImpl
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var userService: UserService
    private lateinit var validationUtil: ValidationUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Initiate UserService
        userService = UserServiceImpl(this)
        validationUtil = ValidationUtil.getInstance()

        // initializing the listeners
        initListeners()

        // initialize cpf mask
        onCpfTextChange()
    }

    /**
     * This method is to initialize listeners
     */
    private fun initListeners() {
        btnConfirmRegister!!.setOnClickListener(this)
    }

    /**
     * This implemented method is to listen the click on view
     *
     * @param view
     */
    override fun onClick(view: View) {
        when (view.id) {
            R.id.btnConfirmRegister -> {
                register()
            }
        }
    }

    /**
     * Set CPF mask
     */
    private fun onCpfTextChange() {
        editTextCpf.addTextChangedListener(CpfMask.mask("###.###.###-##", editTextCpf))
    }

    /**
     * Register the user into database
     */
    private fun register() {
        try {

            if (!editTexPassword.text.toString().equals(editTexConfirmPassword.text.toString(), true)) {
                throw ValidationException("Senhas não correspondem, favor revisar.")
            }

            val user = User(
                null,
                editTextFullname.text.toString(),
                editTextEmail.text.toString(),
                editTextCpf.text.toString(),
                editTexPassword.text.toString()
            )

            // Validate user and throws ValidationException if something doesn't match the rules
            userService.validateUser(user)

            val result: Int? = userService.save(user)

            if (result != null) { // TODO: implementar redirecionamento para main activity
                Toast.makeText(this, "Cadastro realizado com sucesso. Realizando login...", Toast.LENGTH_LONG)
                    .show()
            }

        } catch (validationException: ValidationException) {
            validationUtil.showSnackFeedback(this, validationException.message, false, editTextFullname)
        } catch (businessException: BusinessException) {
            validationUtil.showSnackFeedback(this, businessException.message, false, editTextFullname)
        }
    }




}
