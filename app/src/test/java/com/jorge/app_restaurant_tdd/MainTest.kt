package com.jorge.app_restaurant_tdd

import androidx.lifecycle.ViewModel

class MainTest {
    data class Produto(
        val nome: String,
        val descricao: String,
        val preco: String
    )
    data class Usuario(
        val nome: String? = null
    )
    class DataSource(){
        fun recuperarUsuario(): String?{
            val usuario = Usuario("Antonio Jorge Garcia")
            return usuario.nome
        }
        fun getProdutos(): MutableList<Produto>{
            val listaProduto = mutableListOf(
                Produto("Refrigerante", "Faz Mal", "R$20"),
                Produto("Pizza", "Faz Bem", "R$40"),
                Produto("Cola", "Faz Mal", "R$20")
            )
            return listaProduto
        }
    }

    class MainRepository(){
        val dataSource = DataSource()
        fun getProduto(): MutableList<Produto>{
            return  dataSource.getProdutos()
        }
        fun recuperarUsuario(): String?{
            return dataSource.recuperarUsuario()
        }
    }

    class MainViewModel(private val mainRepository: MainRepository): ViewModel(){
        fun getProduto(): MutableList<Produto>{
            return  mainRepository.getProduto()
        }
        fun recuperarUsuario(): String?{
            return mainRepository.recuperarUsuario()
        }

    }
}