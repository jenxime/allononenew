package com.example.allonone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth

class BandejaV : AppCompatActivity() {


    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bandeja_v)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_bandeja, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            R.id.perfilV -> profileV()
            R.id.addP -> addP()
            R.id.logout -> logout()


        }

        return super.onOptionsItemSelected(item)
    }

    private fun profileV(){
        startActivity(Intent(this,PerfilVendedor::class.java))
    }

    private fun addP(){
        startActivity(Intent(this,Gallery::class.java))
    }


    private fun logout(){
        auth.signOut()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }


    }
