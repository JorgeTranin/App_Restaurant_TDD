package com.jorge.app_restaurant_tdd.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jorge.app_restaurant_tdd.model.Produto
import com.jorge.app_restaurant_tdd.repositorio.MainRepositorio
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepositorio: MainRepositorio) : ViewModel() {
    private val _listaDeProdutos = MutableStateFlow<MutableList<Produto>>(mutableListOf())
    private val listaDeProdutosFlow: StateFlow<MutableList<Produto>> = _listaDeProdutos


    private val _nomeUsuario = MutableStateFlow<String>("")
    private val nomeUsuarioFlow: StateFlow<String> = _nomeUsuario
    fun getProdutos(): Flow<MutableList<Produto>> {
        viewModelScope.launch {
            mainRepositorio.getProdutos().collect {
                _listaDeProdutos.value = it
            }
        }
        return listaDeProdutosFlow
    }

    fun dadosPerfilUsuario(): Flow<String> {
        viewModelScope.launch {
            mainRepositorio.dadosPerfilUsuario().collect {
                _nomeUsuario.value = it
            }
        }
        return nomeUsuarioFlow
    }
}