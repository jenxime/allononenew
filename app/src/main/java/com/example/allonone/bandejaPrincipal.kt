package com.example.allonone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

class bandejaPrincipal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bandeja_principal)
    }

    //menu option
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.overflow, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var selectedOption = ""
        when(item?.itemId){
            R.id.profile -> profileUser()
            R.id.logout -> logout()

        }
        Toast.makeText(this,"Option : " + selectedOption, Toast.LENGTH_SHORT).show()

        return super.onOptionsItemSelected(item)
    }

    private fun profileUser(){
        startActivity(Intent(this,ProfileUser::class.java))
    }

    private fun logout(){
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }
}