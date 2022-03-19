package com.example.allonone

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_add_v.*
import kotlinx.android.synthetic.main.activity_main.*

class AddV : AppCompatActivity() {

    private lateinit var filepath: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_v)

        btnChoose.setOnClickListener{
            startFileChoose()
        }

        btnUp.setOnClickListener{
            uploadFile()
        }
    }

    private fun uploadFile(){
        if(filepath != null){
            var pd = ProgressDialog(this)
            pd.setTitle("Uploading")
            pd.show()

            var imageName = editName.text.toString()
            var imageRef: StorageReference = FirebaseStorage.getInstance().reference.child("images/$imageName.jpg")
            imageRef.putFile(filepath).addOnSuccessListener { po ->
                pd.dismiss()
                Toast.makeText(applicationContext, "File Uploaded", Toast.LENGTH_LONG).show()
            }
                .addOnFailureListener{po ->
                    pd.dismiss()
                    Toast.makeText(applicationContext,po.message,Toast.LENGTH_LONG).show()
                }
                .addOnProgressListener { po ->
                    var progress = (100.0 * po.bytesTransferred)/ po.totalByteCount
                    pd.setMessage("Uploaded ${progress.toInt()}%")
                }
        }
    }

    private fun startFileChoose(){
        var i = Intent()
        i.setType("image/*")
        i.setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(Intent.createChooser(i,"Choose Picture"), 111)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==111 && resultCode == Activity.RESULT_OK && data != null){
            filepath = data.data!!
            var bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filepath)
            imgP.setImageBitmap(bitmap)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.atras, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            R.id.back -> bandejaP()

        }

        return super.onOptionsItemSelected(item)
    }

    private fun bandejaP(){
        startActivity(Intent(this,BandejaV::class.java))
    }


}