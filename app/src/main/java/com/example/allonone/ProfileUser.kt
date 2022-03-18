package com.example.allonone

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.annotation.NonNull
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_profile_user.*


class ProfileUser : AppCompatActivity() {

    private lateinit var nombre: EditText;
    private lateinit var email: EditText;
    private lateinit var password: EditText;
    private lateinit var auth: FirebaseAuth;
    private lateinit var fstore: FirebaseStorage;
    private lateinit var userId: String;
    private lateinit var dbDatabase: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_user)

        nombre = findViewById(R.id.namePu)
        email = findViewById(R.id.emailPu)
        password = findViewById(R.id.passwordPu)

        auth = FirebaseAuth.getInstance();
        fstore = FirebaseStorage.getInstance();

        userId = auth.currentUser?.uid!!

        dbDatabase = FirebaseDatabase.getInstance().getReference("User").child(userId)


        dbDatabase.child("User").child(userId).addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot){
                if(snapshot.exists()) {
                    val nombre1 = "${snapshot.child("name").value}"

                    namePu.setText(nombre1)

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })


    }


    }
