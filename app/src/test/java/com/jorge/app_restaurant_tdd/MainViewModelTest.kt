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

}