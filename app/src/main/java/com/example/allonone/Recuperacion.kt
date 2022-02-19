package com.example.allonone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_ingresar.*
import kotlinx.android.synthetic.main.activity_recuperacion.*

class Recuperacion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperacion)

        btnAtras.setOnClickListener {
            val intent: Intent = Intent (this, Ingresar::class.java )
            startActivity(intent)
            finish()
        }
    }
}