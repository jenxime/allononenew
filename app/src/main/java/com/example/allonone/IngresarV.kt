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
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_registrese_v.*
import javax.crypto.spec.IvParameterSpec

class IngresarV : AppCompatActivity() {

    private lateinit var emailIv: EditText
    private lateinit var passwordIv: EditText
    private lateinit var progressBarIv: ProgressBar

    private lateinit var mDatabase : DatabaseReference

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingresar_v)

        emailIv=findViewById(R.id.emailIv)
        passwordIv=findViewById(R.id.passwordIv)
        progressBarIv=findViewById(R.id.progressBarIv)
        auth= FirebaseAuth.getInstance()
        mDatabase= FirebaseDatabase.getInstance().reference



    }

    fun btnRegistrarseV(view: View){
        startActivity(Intent(this,RegistreseV::class.java))
    }

    fun btnRecuperarV(view: View){
        startActivity(Intent(this,RecuperacionV::class.java))
    }

    fun btnIngresarV(view: View){
        ingresar()
    }

    private fun ingresar(){
        val emailIv:String=emailIv.text.toString()
        val passwordIv:String=passwordIv.text.toString()

        if(!TextUtils.isEmpty(emailIv) && !TextUtils.isEmpty(passwordIv)){
            progressBarIv.visibility= View.VISIBLE

            auth.signInWithEmailAndPassword(emailIv,passwordIv)
                .addOnCompleteListener(this){
                    task ->
                    if(task.isSuccessful){
                        Toast.makeText(this, "Ingreso correcto", Toast.LENGTH_SHORT).show()
                        finish()
                        action()
                    }else{
                        Toast.makeText(this, "Ingreso Incorrecto", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

    private fun action(){
        startActivity(Intent(this,PerfilVendedor::class.java))
    }


}