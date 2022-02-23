package com.example.allonone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class RegistreseV : AppCompatActivity() {

    private lateinit var emailRv: EditText
    private lateinit var passwordRv: EditText
    private lateinit var nameRv: EditText
    private lateinit var descripcionRv: EditText
    private lateinit var contactoRv: EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var dbReference: DatabaseReference
    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrese_v)

        emailRv = findViewById(R.id.emailRv)
        passwordRv = findViewById(R.id.passwordRv)
        nameRv = findViewById(R.id.nameRv)
        descripcionRv = findViewById(R.id.descripcionRv)
        contactoRv = findViewById(R.id.contactoRv)

        progressBar = ProgressBar(this)
        database = FirebaseDatabase.getInstance()
        //auth = FirebaseAuth.getInstance()
        auth = Firebase.auth

        dbReference = database.reference.child("Seller")
    }

    fun registrarV(view: View) {
        createNewAccount()

    }

    fun btnIngresarV(view: View){
        startActivity(Intent(this,IngresarV::class.java))
    }

    private fun createNewAccount() {

        val emailRv: String = emailRv.text.toString()
        val password: String = passwordRv.text.toString()
        val name: String = nameRv.text.toString()
        val descripcion: String = descripcionRv.text.toString()
        val contacto: String = contactoRv.text.toString()


        if (emailRv.isNotEmpty() && password.isNotEmpty() && name.isNotEmpty() && descripcion.isNotEmpty() && contacto.isNotEmpty() ) {
            progressBar.visibility = View.VISIBLE
            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(emailRv, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Registro de vendedor correcto", Toast.LENGTH_SHORT).show()
                        finish()
                        val user: FirebaseUser? = auth.currentUser
                        verifyEmail(user)

                        val userBD = dbReference.child(user?.uid.toString())

                        userBD.child("Email").setValue(emailRv)
                        userBD.child("password").setValue(password)
                        userBD.child("name").setValue(name)
                        userBD.child("descripcion").setValue(descripcion)
                        userBD.child("contacto").setValue(contacto)
                        action()
                    } else {
                        Toast.makeText(this, "Registro de vendedor Incorrecto", Toast.LENGTH_SHORT).show()
                    }

                }
        }
    }

    private fun action() {
        startActivity(Intent(this, IngresarV::class.java))
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