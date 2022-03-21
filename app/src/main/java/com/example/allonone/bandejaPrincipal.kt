package com.example.allonone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.allonone.databinding.ActivityBandejaPrincipalBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase


class bandejaPrincipal : AppCompatActivity() {

    private lateinit var binding: ActivityBandejaPrincipalBinding

    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBandejaPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.mainRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@bandejaPrincipal)

        }
        fetchData()


    }

    private fun fetchData(){
        FirebaseFirestore.getInstance().collection("posts")
            .get()
            .addOnSuccessListener { documents ->
                for(document in documents){
                    val user = documents.toObjects(UserModel::class.java)
                    binding.mainRecyclerView.adapter = UserAdapter(this, user)

                }
            }
            .addOnFailureListener{
            showToast("An error ocurred: ${it.localizedMessage}")
            }
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
        auth.signOut()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}