package com.example.roomexample.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import com.example.roomexample.R

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

    val botaoAdd = view?.findViewById<com.google.android.material.floatingactionbutton.FloatingActionButton>(R.id.button_add)
    val botaoDelete = view?.findViewById<Button>(R.id.button_delete)
    val listaDeContatos by lazy { view?.findViewById<TextView>(R.id.lista_contatos) }

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

        dataBaseOk?.let {
            viewModel.database = it
        }

        viewModel.buscarContato()

        val etNome = view?.findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.nome)

        val etTelefone= view?.findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.telefone)


        val btnAdd = view?.findViewById<com.google.android.material.floatingactionbutton.FloatingActionButton>(R.id.button_add)
        btnAdd?.setOnClickListener {
            adicionarContato(etNome?.text.toString(), etTelefone?.text.toString().toInt())
        }

        val btnDelete = view?.findViewById<Button>(R.id.button_delete)
        btnDelete?.setOnClickListener {
            deletarContato()
        }

        teste()
    }

    fun adicionarContato(nome: String, telefone: Int) {

            val contato = Contato(name = nome, telefone = telefone)
            viewModel.addContato(contato)

    }

    fun deletarContato() {
        viewModel.delete()
    }

    fun teste() {
        viewModel.contatosLiveData.observe(this){
            var text = ""
            it.forEach { text += (it.toString() + "\n") }
            listaDeContatos?.text = text
        }
    }
}