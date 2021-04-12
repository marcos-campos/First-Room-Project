package com.example.roomexample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomexample.R
import com.example.roomexample.ui.main.Contato

class AdapterContato(
        private val listaDeContatos:MutableList<Contato>,
        private val context: Context
        ): RecyclerView.Adapter<ViewHolderContato>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderContato {
        val view = LayoutInflater.from(context).inflate(R.layout.recycler_contato, parent, false)
        return ViewHolderContato(view)
    }

    override fun getItemCount(): Int = listaDeContatos.size


    override fun onBindViewHolder(holder: ViewHolderContato, position: Int) {

        val nameContato = holder.nomeContato
        nameContato.text = listaDeContatos[position].name

        val telephoneContato = holder.telefoneContato
        telephoneContato.text = listaDeContatos[position].telefone.toString()

    }
}