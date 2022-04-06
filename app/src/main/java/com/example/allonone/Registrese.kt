package com.example.allonone


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.allonone.databinding.ActivityRegistreseBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Registrese : AppCompatActivity() {

    private lateinit var binding: ActivityRegistreseBinding
    private lateinit var auth: FirebaseAuth

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_registrese)

        binding.registrarBtn.setOnClickListener {
            val email = binding.txtEmailr.text.toString()
            val password = binding.txtPasswordr.text.toString()
            val name = binding.txtNamer.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty() && name.isNotEmpty()){
                registrar(
                    email,
                    password,
                    name
                )
            }else{
                Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
        auth=Firebase.auth
    }

    override fun onStart(){
        super.onStart()
        val currentUser = auth.currentUser
        if(currentUser != null){
            reload()
        }else{

        }
    }

    private fun registrar(email:String, password:String, name:String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){ task ->
                if(task.isSuccessful){
                    val user = auth.currentUser
                    val uid = user!!.uid
                    val map = hashMapOf(
                        "email" to email,
                        "password" to password,
                        "name" to name
                    )

                    val db = Firebase.firestore

                    db.collection("User").document(uid).set(map).addOnSuccessListener{
                        infoUser()
                       //val user: FirebaseUser? = auth.currentUser
                        //verifyEmail(user)
                        Toast.makeText(this, "Usuario Registrado", Toast.LENGTH_SHORT).show()
                    }
                        .addOnFailureListener{
                            Toast.makeText(this, "Fallo el registro", Toast.LENGTH_SHORT).show()
                        }
                }else{
                    Toast.makeText(this,"Authentication Failed", Toast.LENGTH_SHORT).show()
                }

            }

    }

    private fun infoUser() {
        val infoUserIntent = Intent(this, Ingresar::class.java)
        startActivity(infoUserIntent)

    }

    private fun reload(){

    }


    private fun verifyEmail(user: FirebaseUser?) {
       user?.sendEmailVerification()
            ?.addOnCompleteListener(this) { task ->

                if (task.isComplete) {
                    Toast.makeText(this, "Email enviado", Toast.LENGTH_LONG)
                } else {
                    Toast.makeText(this, "Error al enviar el correo", Toast.LENGTH_LONG)
                }
            }

    }



}
