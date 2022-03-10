package com.example.allonone

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import com.example.allonone.databinding.ActivityPerfilVendedorBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*

import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_registrese_v.*

class PerfilVendedor : AppCompatActivity() {

    private lateinit var name: TextView
    private lateinit var auth: FirebaseAuth
    private lateinit var binding : ActivityPerfilVendedorBinding
    private lateinit var dbReference: DatabaseReference
    private lateinit var storageReference: StorageReference
    private lateinit var dialog: Dialog
    private lateinit var uid : String
    private lateinit var ingresarV: IngresarV
    private lateinit var registreseV: RegistreseV




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_perfil_vendedor)
        binding = ActivityPerfilVendedorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        uid = auth.currentUser?.uid.toString()

        dbReference = FirebaseDatabase.getInstance().getReference("Seller")
        if (uid.isNotEmpty()) {
            getUserData()
        }

    }
    private fun getUserData(){
        dbReference.child(uid).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                registreseV = snapshot.getValue(RegistreseV::class.java)!!
                //binding.nameP. .setText(registreseV.nameRv )
                //binding.descripcion.setText(registreseV.descripcionRv)
                //binding.contacto.setText(registreseV.contactoRv)

            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })
        }


private fun cerrarSesion(){
    auth.signOut()
    val intent = Intent(this, MainActivity::class.java)
    startActivity(
        intent
    )

}

    override fun onStart() {
        super.onStart()

    val currentUser = auth.currentUser
    if(currentUser != null){
        reload()
    }else{

    }

    }


    private fun reload(){

    }

}

