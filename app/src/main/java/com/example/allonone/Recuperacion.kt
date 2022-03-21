package com.example.allonone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.allonone.databinding.ActivityRecuperacionBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_ingresar.*
import kotlinx.android.synthetic.main.activity_recuperacion.*

class Recuperacion : AppCompatActivity() {

    private lateinit var binding: ActivityRecuperacionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recuperacion)

        binding.recuperarI.setOnClickListener {
            val emailAddress = binding.emailRi.text.toString()

            Firebase.auth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener{task ->
                    if(task.isSuccessful){
                        Toast.makeText(this,"Correo enviado", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, Ingresar::class.java))
                    }else{
                        Toast.makeText(this, "Error al enviar el email", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}