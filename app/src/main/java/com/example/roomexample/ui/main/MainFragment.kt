package com.example.roomexample.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import com.example.roomexample.R
import com.example.roomexample.adapter.AdapterContato

class MainFragment : Fragment() {

    private val dataBaseOk by lazy {
        activity?.let {
            databaseBuilder(
                    it,
                RoomDatabase::class.java,
                "dataBaseOk"
        ).build()
        }
    }

    private var listaDeContatos = mutableListOf<Contato>()
    val recycler by lazy { view?.findViewById<RecyclerView>(R.id.recycler) }

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        recycler?.layoutManager = LinearLayoutManager(activity)

        val adapter = activity?.let { AdapterContato(listaDeContatos, it) }
        recycler?.adapter = adapter

        dataBaseOk?.let {
            viewModel.database = it
        }

        viewModel.buscarContato()
        viewModel.contatosLiveData.observe(this, Observer {
            listaDeContatos.addAll(it)

            adapter?.notifyDataSetChanged()

        })

        val etNome = view?.findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.nome)
        val etTelefone= view?.findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.telefone)

        val btnAdd = view?.findViewById<com.google.android.material.floatingactionbutton.FloatingActionButton>(R.id.button_add)
        btnAdd?.setOnClickListener {
            adicionarContato(etNome?.text.toString(), etTelefone?.text.toString().toInt())
        }

//        val btnDelete = view?.findViewById<Button>(R.id.button_delete)
//        btnDelete?.setOnClickListener {
//            deletarContato()
//        }

    }

    fun adicionarContato(nome: String, telefone: Int) {

            val contato = Contato(name = nome, telefone = telefone)
            viewModel.addContato(contato)
    }

//    fun deletarContato() {
//        viewModel.delete()
//    }
}