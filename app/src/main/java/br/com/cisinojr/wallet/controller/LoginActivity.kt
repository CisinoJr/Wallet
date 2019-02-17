package br.com.cisinojr.wallet.controller

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import br.com.cisinojr.wallet.R
import br.com.cisinojr.wallet.controller.util.ValidationUtil
import br.com.cisinojr.wallet.exceptions.BusinessException
import br.com.cisinojr.wallet.exceptions.ValidationException
import br.com.cisinojr.wallet.service.UserService
import br.com.cisinojr.wallet.service.UserServiceImpl
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var userService: UserService
    private lateinit var validationUtil: ValidationUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initiate UserService
        userService = UserServiceImpl(this)
        validationUtil = ValidationUtil.getInstance()

        // initializing the listeners
        initListeners()

        // Verify if user was already logged in
        verifyLoggedUser()
    }

    /**
     * This method is to initialize listeners
     */
    private fun initListeners() {
        txtViewRegister!!.setOnClickListener(this)
        btnLogin!!.setOnClickListener(this)
    }

    /**
     * This implemented method is to listen the click on view
     *
     * @param view
     */
    override fun onClick(view: View) {
        when (view.id) {
            // Login-in the user
            R.id.btnLogin -> {
                login()
            }
            // Go to registration view
            R.id.txtViewRegister -> {
                // Navigate to RegisterActivity
                val intentRegister = Intent(applicationContext, RegisterActivity::class.java)
                startActivity(intentRegister)
            }
        }
    }

    /**
     * Log-in user
     *
     */
    private fun login() {
        try {
            val email = editTextEmail.text.toString()
            val password = editTexPassword.text.toString()

            if (userService.validateUserCredentials(email, password)) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        } catch (validationException: ValidationException) {
            validationUtil.showSnackFeedback(this, validationException.message, false, editTextEmail)
        } catch (businessException: BusinessException) {
            validationUtil.showSnackFeedback(this, businessException.message, false, editTextEmail)
        }
    }

    private fun verifyLoggedUser() {
        if (userService.isUserLoggedIn()) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

}