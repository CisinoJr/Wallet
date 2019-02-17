package br.com.cisinojr.wallet.controller

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import br.com.cisinojr.wallet.R
import br.com.cisinojr.wallet.service.UserService
import br.com.cisinojr.wallet.service.UserServiceImpl
import kotlinx.android.synthetic.main.activity_main.*
import br.com.cisinojr.wallet.controller.util.ValidationUtil
import br.com.cisinojr.wallet.exceptions.BusinessException

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var userService: UserService
    private lateinit var validationUtil: ValidationUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initiate UserService
        userService = UserServiceImpl(this)
        validationUtil = ValidationUtil.getInstance()

        // initializing the listeners
        initListeners()

        // Set bottom app bar
        with(bottomAppBar) {
            replaceMenu(R.menu.main_menu)

            // Menu actions
            setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.action_profile -> showProfileActivity()
                    R.id.action_logout -> logout()
                    else -> false
                }
            }
        }

    }

    /**
     * This method is to initialize listeners
     */
    private fun initListeners() {
    }

    /**
     * This implemented method is to listen the click on view
     *
     * @param view
     */
    override fun onClick(view: View) {
        when (view.id) {
        }
    }

    private fun showProfileActivity(): Boolean {
        startActivity(Intent(this, ProfileActivity::class.java))
        return true
    }

    private fun logout(): Boolean {
        try {
            // Remove user credentials from SharedPreferences
            userService.logoutUser()

            // Redirect user to login view
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        } catch (businessException: BusinessException) {
            return false
        }

        return true
    }

}
