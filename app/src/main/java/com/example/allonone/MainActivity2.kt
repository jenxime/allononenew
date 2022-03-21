package com.example.allonone

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.core.View
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import kotlinx.android.synthetic.main.activity_add_v.*
import kotlinx.android.synthetic.main.activity_add_v.btnUpImg
import kotlinx.android.synthetic.main.activity_main2.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


/*class MainActivity2 : AppCompatActivity(), View.OnClickListener {


    override fun onClick(v: View?) {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        when (v?.id) {
            R.id.btnUpImg ->{
                fileType = ".jpg"
                intent.setType("image/*")
            }
            R.id.btnUpload -> {
                if (uri != null) {
                    fileName = SimpleDateFormat("yyyyMMddHHmmssSSS").format(Date())
                    val fileRef: StorageReference = storage.child(fileName + fileType)
                    fileRef.putFile(uri)
                        .continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
                            return@Continuation fileRef.downloadUrl
                        })
                        .addOnCompleteListener { task ->
                            val hm = HashMap<String, Any>()
                            hm.put(F_NAME, fileName)
                            hm.put(F_TYPE, fileType)
                            hm.put(F_URL, task.result.toString())
                            db.document(fileName).set(hm).addOnSuccessListener {
                                Toast.makeText(
                                    this,
                                    "File Successfully uploader",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                }
            }
        }

        if (v?.id != R.id.btnUpload) startActivityForResult(intent, RC_OK)
    }

    private lateinit var filepath: Uri
    lateinit var storage: StorageReference
    lateinit var db: CollectionReference
    lateinit var alFile: ArrayList<HashMap<String, Any>>
    lateinit var adapter: CustomAdapter
    lateinit var uri: Uri
    var F_NAME = "file_name"
    var F_TYPE = "file_type"
    val F_URL = "file_url"
    val RC_OK = 100
    val fileType = ""
    val fileName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)


        btnUpImg.setOnClickListener(this)

        alFile = ArrayList()
        uri = Uri.EMPTY

    }

    override fun onStart() {
        super.onStart()

        storage = FirebaseStorage.getInstance().reference
        db = FirebaseFirestore.getInstance().collection("files")
        db.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
            if (firebaseFirestoreException != null) {
                Log.e("firestore :", firebaseFirestoreException.message)
            }
            showData()

        }
    }

    fun showData() {
        db.get().addOnSuccessListener { result ->
            alFile.clear()
            for (doc: QueryDocumentSnapshot in result) {
                val hm = HashMap<String, Any>()
                hm.put(F_NAME, doc.get(F_NAME).toString())
                hm.put(F_TYPE, doc.get(F_TYPE).toString())
                hm.put(F_URL, doc.get(F_URL).toString())
                alFile.add(hm)
            }
            adapter = CustomAdapter(this, alFile)
            lsV.adapter = adapter

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if ((resultCode == Activity.RESULT_OK) && (requestCode == RC_OK)) {
            if (data != null) {
                uri = data.data!!
                txSelectedFile.setText(uri.toString())
            }
        }

    }
}

*/