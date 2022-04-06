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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import android.widget.EditText
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import com.example.allonone.databinding.ActivityPerfilVendedorBinding
import com.example.allonone.databinding.ActivityProfileUserBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.core.Constants
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_perfil_vendedor.*


class PerfilVendedor : AppCompatActivity() {

    private lateinit var binding: ActivityPerfilVendedorBinding
    lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_perfil_vendedor)

        auth = Firebase.auth

        val currentUser = auth.currentUser
        val uid = currentUser!!.uid
        val db = Firebase.firestore

        db.collection("Seller").document(uid).get().addOnSuccessListener {
            binding.nameP .text = (it.get("name") as String?)
            binding.descripcionP.text = (it.get("descripcion") as String?)
            binding.contactoP.text = (it.get("contacto") as String? )
            binding.emailP.text = (it.get("email") as String? )
            binding.passwordP.text = (it.get("password") as String? )


        }

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

