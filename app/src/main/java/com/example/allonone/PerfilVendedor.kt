package com.example.allonone

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import com.example.allonone.databinding.ActivityPerfilVendedorBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import android.widget.EditText
import android.widget.ImageView
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.core.Constants
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_perfil_vendedor.*


class PerfilVendedor : AppCompatActivity() {

    private lateinit var nombre : EditText
    private lateinit var descripcion: EditText
    private lateinit var contacto : EditText
    private lateinit var actualizar : Button
    private lateinit var dbDatabase: DatabaseReference
    private lateinit var auth : FirebaseAuth
    private lateinit var progressbar: ProgressDialog
    private lateinit var rootnone: FirebaseDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_vendedor)

        nombre = findViewById(R.id.nameP)
        descripcion = findViewById(R.id.descripcionP)
        contacto = findViewById(R.id.contactoP)
        actualizar = findViewById(R.id.btnUpdate)
        progressbar = ProgressDialog(this)

        auth = FirebaseAuth.getInstance()
        val uid = auth.currentUser?.uid!!

        dbDatabase = FirebaseDatabase.getInstance().getReference("Seller").child(uid)

        actualizar.setOnClickListener {
            val nomb = nombre.text.toString().trim()
            val des = descripcion.text.toString().trim()
            val cont = contacto.text.toString().trim()

            if(TextUtils.isEmpty(nomb)){
                nombre.error = "Enter name"
                return@setOnClickListener
            }
            if(TextUtils.isEmpty(des)){
                descripcion.error = "Enter descripcion"
                return@setOnClickListener
            }
            if(TextUtils.isEmpty(cont)){
                contacto.error = "Enter contacto"
                return@setOnClickListener
            }

            updateUser(nomb, des, cont)

        }

    }

    private fun updateUser(nomb: String, des: String, cont: String ){
        progressbar.setMessage("Actualizando. Espere por favor")
        progressbar.show()

        val userMap = HashMap<String ,Any>()

        userMap["nombre"] = nomb
        userMap["descripcion"] = des
        userMap["contacto"] = cont

        dbDatabase.setValue(userMap).addOnCompleteListener( OnCompleteListener { task ->
            if(task.isSuccessful){
                val Intent = Intent(applicationContext, PerfilVendedor::class.java)
                startActivity(Intent)
                finish()
                progressbar.dismiss()
            }

        }
        )

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.atras, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var selectedOption = ""
        when(item?.itemId){
            R.id.back -> bandejaP()

        }

        return super.onOptionsItemSelected(item)
    }

    private fun bandejaP(){
        startActivity(Intent(this,BandejaV::class.java))
    }


}

