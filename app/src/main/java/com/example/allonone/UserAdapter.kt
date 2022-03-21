package com.example.allonone

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.firestore.auth.User
import java.text.FieldPosition

class UserAdapter(private val context: Context,  private val posts: List<UserModel>) : RecyclerView.Adapter<UserViewHolder>() {
    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):UserViewHolder{
        return UserViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_layout, parent, false )
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int){
        val user = posts[position]

        Glide.with(context)
            .load(user.imageUrl)
            .into(holder.imageUrl)

    }

}


class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val imageUrl: ImageView = itemView.findViewById(R.id.imageView_user)

}

