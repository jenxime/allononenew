package com.example.allonone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_recuperacion.*
import kotlinx.android.synthetic.main.activity_recuperacion_v.*

class RecuperacionV : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperacion_v)

        btnAtrasV.setOnClickListener {
            val intent: Intent = Intent (this, IngresarV::class.java )
            startActivity(intent)
            finish()
        }
    }
}