package com.example.allonone

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Registrese : AppCompatActivity() {

    private lateinit var txtEmailr: EditText
    private lateinit var txtPasswordr: EditText
    private lateinit var txtNamer: EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var dbReference: DatabaseReference
    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrese)


        //cambiar de ventana
        /* btnIngrese.setOnClickListener {
            val intent: Intent = Intent(this, Ingresar::class.java)
            startActivity(intent)
            finish()
        }*/

        //logica de firebase
        txtEmailr = findViewById(R.id.txtEmailr)
        txtPasswordr = findViewById(R.id.txtPasswordr)
        txtNamer = findViewById(R.id.txtNamer)


        progressBar = ProgressBar(this)
        database = FirebaseDatabase.getInstance()
        //auth = FirebaseAuth.getInstance()
        auth = Firebase.auth

        dbReference = database.reference.child("User")

    }

    fun registrar(view: View) {
        createNewAccount()

    }

    private fun createNewAccount() {

        val email: String = txtEmailr.text.toString()
        val password: String = txtPasswordr.text.toString()
        val name: String = txtNamer.text.toString()


        if (txtEmailr.text.isNotEmpty() && txtPasswordr.text.isNotEmpty() && txtNamer.text.isNotEmpty() ) {
            progressBar.visibility = View.VISIBLE
            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Registro correcto", Toast.LENGTH_SHORT).show()
                        finish()
                        val user: FirebaseUser? = auth.currentUser
                        verifyEmail(user)

                        val userBD = dbReference.child(user?.uid.toString())

                        userBD.child("Email").setValue(txtEmailr.text.toString())
                        userBD.child("password").setValue(password)
                        userBD.child("name").setValue(name)

                        action()
                    } else {
                        Toast.makeText(this, "Registro Incorrecto", Toast.LENGTH_SHORT).show()
                    }

                }
        }
    }

    private fun action() {
        startActivity(Intent(this, Ingresar::class.java))
        //finish()


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
