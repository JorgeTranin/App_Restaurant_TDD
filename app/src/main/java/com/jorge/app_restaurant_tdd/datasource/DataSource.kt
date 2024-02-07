package com.jorge.app_restaurant_tdd.datasource

import com.google.firebase.firestore.FirebaseFirestore
import com.jorge.app_restaurant_tdd.model.Produto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


class DataSource @Inject constructor() {
    private val firebase = FirebaseFirestore.getInstance()
    val listaProdutos: MutableList<Produto> = mutableListOf()
    private val _listaDeProdutos = MutableStateFlow<MutableList<Produto>>(mutableListOf())
    private val listaDeProdutosFlow: StateFlow<MutableList<Produto>> = _listaDeProdutos


    private val _nomeUsuario = MutableStateFlow<String>("")
    private val nomeUsuarioFlow: StateFlow<String> = _nomeUsuario
    fun getProdutos(): Flow<MutableList<Produto>> {
        firebase.collection("Produtos").get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                for (doc in task.result) {
                    val produto = doc.toObject(Produto::class.java)
                    listaProdutos.add(produto)
                    _listaDeProdutos.value = listaProdutos
                }
            }

        }.addOnFailureListener {

        }
        return listaDeProdutosFlow
    }

    fun dadosPerfilUsuario(): Flow<String>{
        firebase.collection("usuarios").document("HUddp7qpJviqOIWhXQnT").addSnapshotListener { value, error ->
            val nomeUsuario = value?.data?.get("nome").toString()
            _nomeUsuario.value = nomeUsuario
        }
        return nomeUsuarioFlow
    }
}