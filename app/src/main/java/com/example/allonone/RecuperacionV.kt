package com.example.allonone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.allonone.databinding.ActivityRecuperacionVBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_recuperacion.*
import kotlinx.android.synthetic.main.activity_recuperacion_v.*

class RecuperacionV : AppCompatActivity() {

    private lateinit var binding: ActivityRecuperacionVBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recuperacion_v)

        binding.recuperarV.setOnClickListener {
            val emailAddress = binding.emailRv.text.toString()

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