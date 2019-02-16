package br.com.cisinojr.wallet

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // initializing the listeners
        initListeners()
        bottomAppBar.replaceMenu(R.menu.main_menu)
        bottomAppBar.setNavigationOnClickListener {
            // do something interesting on navigation click
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

}
