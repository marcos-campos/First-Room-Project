package com.example.roomexample.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomexample.R

class ViewHolderContato  (view: View): RecyclerView.ViewHolder(view) {

    val nomeContato by lazy { view.findViewById<TextView>(R.id.recycler_nome)}
    val telefoneContato by lazy { view.findViewById<TextView>(R.id.recycler_telefone)}

}