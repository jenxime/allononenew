package com.example.allonone

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.allonone.databinding.ActivityRegistreseBinding
import com.example.allonone.databinding.ActivityRegistreseVBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegistreseV : AppCompatActivity() {

    private lateinit var binding: ActivityRegistreseVBinding
    private lateinit var auth: FirebaseAuth

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_registrese_v)

        binding.registrarV.setOnClickListener {
            val email = binding.txtemailRv.text.toString()
            val password = binding.passwordRv.text.toString()
            val name = binding.nameRv.text.toString()
            val descripcion = binding.descripcionRv.text.toString()
            val contacto = binding.contactoRv.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty() && name.isNotEmpty() && descripcion.isNotEmpty() && contacto.isNotEmpty()){
                registrar(
                    email,
                    password,
                    name, descripcion,
                    contacto
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

    private fun registrar(email:String, password:String, name:String, descripcion:String, contacto:String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){ task ->
                if(task.isSuccessful){
                    val user = auth.currentUser
                    val uid = user!!.uid
                    val map = hashMapOf(
                        "email" to email,
                        "password" to password,
                        "name" to name,
                    "descripcion" to descripcion,
                        "contacto" to contacto

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
        val infoUserIntent = Intent(this, PerfilVendedor::class.java)
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