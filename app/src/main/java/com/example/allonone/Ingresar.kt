package com.example.allonone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class Ingresar : AppCompatActivity() {

    private lateinit var txtEmailI: EditText
    private lateinit var txtPasswordI: EditText
    private lateinit var progressBarI: ProgressBar

    private lateinit var mDatabase : DatabaseReference

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingresar)

        //firebase
        txtEmailI=findViewById(R.id.txtEmailI)
        txtPasswordI=findViewById(R.id.txtPasswordI)
        progressBarI=findViewById(R.id.progressBarI)
        auth= FirebaseAuth.getInstance()
        mDatabase= FirebaseDatabase.getInstance().reference



    }

    fun btnRegistrarseI(view:View){
        startActivity(Intent(this,Registrese::class.java))
    }

    fun btnRecuperarI(view:View){
        startActivity(Intent(this,Recuperacion::class.java))
    }

    fun btnIngresarI(view:View){
        ingresar()
    }

    private fun ingresar(){
        val emailI:String=txtEmailI.text.toString()
        val passwordI:String=txtPasswordI.text.toString()

        if(!TextUtils.isEmpty(emailI) && !TextUtils.isEmpty(passwordI)){
            progressBarI.visibility=View.VISIBLE

            val type = mDatabase.child("User").child("typeUser").get()

            if(type.equals("Cliente")){
                Toast.makeText(this, "Registro correcto", Toast.LENGTH_SHORT).show()
                finish()
                action()
            }else{
                Toast.makeText(this, "Incorrecto", Toast.LENGTH_SHORT).show()
            }
/*
            auth.signInWithEmailAndPassword(emailI,passwordI)
                .addOnCompleteListener(this){
                    task ->
                    if(task.isSuccessful){
                        Toast.makeText(this, "Ingreso correcto", Toast.LENGTH_SHORT).show()
                        finish()
                        action()
                    }else{
                        Toast.makeText(this, "Ingreso Incorrecto", Toast.LENGTH_SHORT).show()
                    }
                }*/
        }
    }

    private fun action(){
        startActivity(Intent(this,bandejaPrincipal::class.java))
    }















}