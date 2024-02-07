package com.jorge.app_restaurant_tdd.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.jorge.app_restaurant_tdd.adapter.ProdutoAdapter
import com.jorge.app_restaurant_tdd.databinding.ActivityMainBinding
import com.jorge.app_restaurant_tdd.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel

    private lateinit var binding: ActivityMainBinding
    private lateinit var produtoAdapter: ProdutoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launch {
             mainViewModel.dadosPerfilUsuario().collect{nomeUser ->
                binding.txtNome.text = nomeUser
            }
        }

        lifecycleScope.launch{
            mainViewModel.getProdutos().collect{ listaProdutos ->
                val rvProdutos = binding.rvProdutos
                rvProdutos.layoutManager = LinearLayoutManager(this@MainActivity)
                produtoAdapter =  ProdutoAdapter(this@MainActivity, listaProdutos)
                rvProdutos.setHasFixedSize(true)
                rvProdutos.adapter = produtoAdapter
                produtoAdapter.notifyDataSetChanged()
            }
        }
    }
}