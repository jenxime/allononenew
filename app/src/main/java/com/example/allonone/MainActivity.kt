package com.example.allonone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnCliente.setOnClickListener {
            val intent:Intent = Intent (this, Ingresar::class.java )
            startActivity(intent)
            finish()
        }

        btnVendedor.setOnClickListener {
            val intent:Intent = Intent (this, IngresarV::class.java )
            startActivity(intent)
            finish()
        }
    }


}