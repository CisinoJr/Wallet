package br.com.cisinojr.wallet.controller

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import br.com.cisinojr.wallet.R
import br.com.cisinojr.wallet.controller.util.ValidationUtil
import br.com.cisinojr.wallet.domain.User
import br.com.cisinojr.wallet.exceptions.BusinessException
import br.com.cisinojr.wallet.exceptions.ValidationException
import br.com.cisinojr.wallet.service.UserService
import br.com.cisinojr.wallet.service.UserServiceImpl
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var userService: UserService
    private lateinit var validationUtil: ValidationUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // Initiate UserService
        userService = UserServiceImpl(this)
        validationUtil = ValidationUtil.getInstance()

        // initializing the listeners
        initListeners()

        // Load user information
        setUserInformationInForm()

    }

    /**
     * This method is to initialize listeners
     */
    private fun initListeners() {
        btnUpdateUserInformation!!.setOnClickListener(this)
    }

    /**
     * This implemented method is to listen the click on view
     *
     * @param view
     */
    override fun onClick(view: View) {
        when (view.id) {
            R.id.btnUpdateUserInformation -> updateUserInformation()
        }
    }

    /**
     * Load user information to form
     */
    private fun setUserInformationInForm() {
        try {
            val user: User = userService.find()
            editTextFullname.setText(user.fullName)
            editTextEmail.setText(user.email)
            editTextCpf.setText(user.cpf)
        } catch (businessException: BusinessException) {
            validationUtil.showSnackFeedback(this, businessException.message, false, editTextFullname)
        }
    }

    private fun updateUserInformation() {
        try {
            if (!editTexPassword.text.toString().equals(editTexConfirmPassword.text.toString(), true)) {
                throw ValidationException("Senhas não correspondem, favor revisar.")
            }

            val result: Int? = userService.updateUserPassword(editTexPassword.text.toString())

            if (result != null) {
                Toast.makeText(this, "Senha alterada com sucesso!", Toast.LENGTH_LONG).show()

                // Redirect user back to Home
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }

        } catch (validationException: ValidationException) {
            validationUtil.showSnackFeedback(this, validationException.message, false, editTexPassword)
        } catch (businessException: BusinessException) {
            validationUtil.showSnackFeedback(this, businessException.message, false, editTexPassword)
        }
    }
}
