package com.example.allonone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.allonone.databinding.ActivityIngresarBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase


class Ingresar : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityIngresarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_ingresar)
        //setContentView(R.layout.activity_ingresar)

        binding.textviewRegistrar.setOnClickListener {
            val intentRegistro = Intent(this, Registrese::class.java)
            startActivity(intentRegistro)
        }

        binding.textviewRecuperarP.setOnClickListener {
            val intentRecoveryPassword = Intent(this, Recuperacion::class.java)
            startActivity(intentRecoveryPassword)
        }

        auth = Firebase.auth

        binding.btnIngresarI.setOnClickListener{
            singIn(
                binding.txtEmailI.text.toString(),
                binding.txtPasswordI.text.toString()

            )
        }


    }

    override fun onStart(){
        super.onStart()

        val currentUser = auth.currentUser

        checkUserAlreadyLoggedIn(currentUser)

    }

    private fun singIn(email: String, password: String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if(task.isSuccessful){
                    Toast.makeText(this,"Session iniciada", Toast.LENGTH_SHORT).show()
                    infoUser()
                }else{
                    Toast.makeText(baseContext, "Datos Incorrectos", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun infoUser(){
        val infoUserIntent = Intent(this, bandejaPrincipal::class.java)
        startActivity(infoUserIntent)
        this.finish()
    }

    private fun checkUserAlreadyLoggedIn(currentUser: FirebaseUser?){
        if(currentUser != null){

        }

    }

}