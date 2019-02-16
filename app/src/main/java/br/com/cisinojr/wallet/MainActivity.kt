package br.com.cisinojr.wallet

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomAppBar.replaceMenu(R.menu.main_menu)
        bottomAppBar.setNavigationOnClickListener {
            // do something interesting on navigation click
        }
    }
}
