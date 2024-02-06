package com.jorge.app_restaurant_tdd

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MainViewModelTest {

    private lateinit var mainViewModel: MainTest.MainViewModel
    private lateinit var mainRepository: MainTest.MainRepository

    @Before
    fun setupMain() {
        mainRepository = MainTest.MainRepository()
        mainViewModel = MainTest.MainViewModel(mainRepository)
    }

    @Test
    fun `o nome do usuario tem que chegar do tipo null - test precisa falhar`() {
        val nomeUsuario = mainViewModel.recuperarUsuario()
        Assert.assertEquals(null, nomeUsuario)
    }

    @Test
    fun `Verifica nome de usuario`(){
        val nomeUsuario = mainViewModel.recuperarUsuario()
        Assert.assertEquals("Antonio Jorge Garcia", nomeUsuario)
    }

    @Test
    fun `test verifica quantidade de itens na lista de produtos`(){
        val listaDeProdutos = mainViewModel.getProduto()
        val tamanhoEsperado = 2

        Assert.assertEquals(tamanhoEsperado, listaDeProdutos.size)
    }

    @Test
    fun `test verificar se todos os itens da lista estao ok`(){
        val listaDeProdutos = mainViewModel.getProduto()
        val listaFake = mutableListOf<MainTest.Produto>(
            MainTest.Produto("Refrigerante", "Faz Mal", "R$20"),
            MainTest.Produto("Pizza", "Faz Bem", "R$40"),
            MainTest.Produto("Cola", "Faz Mal", "R$20")
        )

        Assert.assertEquals(listaFake, listaDeProdutos)

    }

}