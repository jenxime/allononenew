package com.example.allonone

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import java.text.FieldPosition

//class CustomAdapter (val context: Context, arrayList: ArrayList<HashMap<String,Any>>) : BaseAdapter(){
/*
    var F_NAME = "file_name"
    var F_TYPE = "file_type"
    val F_URL = "file_url"
    val list = arrayList
    var uri = Uri.EMPTY

    inner class ViewHolder(){
        var txtFileName : TextView? = null
        var txtFileType : TextView? = null
        var txtFileUrl : TextView? = null
        var imv : ImageView? = null
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var holder = ViewHolder()
        var view = convertView
        if(convertView == null){
            var inflater : LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)
            as LayoutInflater
            view = inflater.inflate(R.layout.data_rows, null, true)

            holder.txtFileName = view!!.findViewById(R.id.txtFileName) as TextView
            holder.txtFileType = view!!.findViewById(R.id.txtFileType) as TextView
            holder.txtFileUrl = view!!.findViewById(R.id.txtFileUrl) as TextView
            holder.imv = view!!.findViewById(R.id.imv) as ImageView

            view.tag = holder

        }else{
            holder = view!!.tag as ViewHolder
        }
        var fileType: String = list.get(position).get(F_TYPE).toString()
        uri = Uri.parse(list.get(position).get(F_URL).toString())

        holder.txtFileName!!.setText(list.get(position).get(F_NAME).toString())
        holder.txtFileType!!.setText(fileType)
        holder.txtFileUrl!!.setText(uri.toString())
        holder.txtFileUrl!!.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW).setData(
            Uri.parse((holder.txtFileUrl!!.text.toString()))
        )
        context.startActivity(intent)
        }
        when(fileType){
            ".jpg" -> {Picasso.get().load(uri).into(holder.imv)}
        }
        return view!!
    }




    override fun getItem(position: Int): Any {
        return list.get(position)
    }

    override fun getItemId(position: Int): Long {
        return 0
    }



}*/