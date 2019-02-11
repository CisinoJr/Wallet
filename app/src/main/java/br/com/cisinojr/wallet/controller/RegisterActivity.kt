package br.com.cisinojr.wallet.controller

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import br.com.cisinojr.wallet.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // initializing the listeners
        initListeners()
    }

    /**
     * This method is to initialize listeners
     */
    private fun initListeners() {
        txtViewRegister!!.setOnClickListener(this)
    }

    /**
     * This implemented method is to listen the click on view
     *
     * @param view
     */
    override fun onClick(view: View) {
       when(view.id) {
           // Go to registration view
           R.id.txtViewRegister -> {
               // Navigate to RegisterActivity
               val intentRegister = Intent(applicationContext, RegisterActivity::class.java)
               startActivity(intentRegister)
           }
       }
    }

}