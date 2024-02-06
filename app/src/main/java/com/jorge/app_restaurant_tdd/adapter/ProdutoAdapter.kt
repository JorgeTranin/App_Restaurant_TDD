package com.jorge.app_restaurant_tdd.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jorge.app_restaurant_tdd.databinding.ProdutoItemBinding
import com.jorge.app_restaurant_tdd.model.Produto

class ProdutoAdapter(private val context: Context, private val listaProdutos: MutableList<Produto>):
    RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoViewHolder {
        val itemLista = ProdutoItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return ProdutoViewHolder(itemLista)
    }

    override fun getItemCount() = listaProdutos.size

    override fun onBindViewHolder(holder: ProdutoViewHolder, position: Int) {
        holder.txtNome.text = listaProdutos[position].nome
        holder.txtDescricao.text = listaProdutos[position].descricao
        holder.txtPreco.text = listaProdutos[position].preco
    }

    inner class ProdutoViewHolder(binding: ProdutoItemBinding): RecyclerView.ViewHolder(binding.root){
        val txtNome = binding.txtNome
        val txtDescricao = binding.txtDescricao
        val txtPreco = binding.txtPreco
    }
}