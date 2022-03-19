package com.example.allonone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

class BandejaV : AppCompatActivity() {
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
            R.id.addP -> addProducts()
            R.id.logout -> logout()

        }

        return super.onOptionsItemSelected(item)
    }

    private fun profileV(){
        startActivity(Intent(this,PerfilVendedor::class.java))
    }

    private fun addProducts(){
        startActivity(Intent(this,AddV::class.java))
    }

    private fun logout(){
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }


    }
