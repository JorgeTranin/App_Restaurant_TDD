package com.jorge.app_restaurant_tdd.repositorio

import com.jorge.app_restaurant_tdd.datasource.DataSource
import com.jorge.app_restaurant_tdd.model.Produto
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class MainRepositorio @Inject constructor() {
    val dataSource = DataSource()

    fun getProdutos(): Flow<MutableList<Produto>>{
        return dataSource.getProdutos()
    }
    fun dadosPerfilUsuario(): Flow<String>{
        return dataSource.dadosPerfilUsuario()
    }
}