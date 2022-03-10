package com.example.allonone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_ingresar.*
import kotlinx.android.synthetic.main.activity_recuperacion.*

class Recuperacion : AppCompatActivity() {

    private lateinit var emailRi : EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperacion)

        emailRi=findViewById(R.id.emailRi)
        progressBar=findViewById(R.id.progressBarRi)
        auth= FirebaseAuth.getInstance()

        btnAtras.setOnClickListener {
            val intent: Intent = Intent (this, Ingresar::class.java )
            startActivity(intent)
            finish()
        }


    }

    fun recuperarI(view: View){
        val email=emailRi.text.toString()
        if(!TextUtils.isEmpty(email)){
            auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(this){
                        task ->

                    if(task.isSuccessful){
                        progressBar.visibility=View.VISIBLE
                        startActivity(Intent(this, Ingresar::class.java))
                    }else{
                        Toast.makeText(this, "Error al enviar el email", Toast.LENGTH_SHORT).show()
                    }
                }

        }
    }
}