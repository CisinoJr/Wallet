package br.com.cisinojr.wallet.controller

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import br.com.cisinojr.wallet.R
import android.view.View
import android.widget.EditText
import br.com.cisinojr.wallet.controller.util.CpfMask
import br.com.cisinojr.wallet.controller.util.CpfValidator
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

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
        if (!CpfValidator.myValidateCPF(editTextCpf.text.toString())) {
            showSnackFeedback("CPF inválido", false, editTextCpf)
        } else {

        }
    }

    /**
     * Show validation error messages
     */
    fun showSnackFeedback(message: String, isValid: Boolean, view: View) {
        val snackbar: Snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
        val v: View = snackbar.view

        if (!isValid) {
            v.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_red_dark))
        }

        snackbar.show()
    }

}
