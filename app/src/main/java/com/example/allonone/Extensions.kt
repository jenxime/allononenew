package com.example.allonone

import android.content.Context
import android.widget.Toast

fun Context.showToast(message: String){
    Toast.makeText(this, message, android.widget.Toast.LENGTH_SHORT).show()
}