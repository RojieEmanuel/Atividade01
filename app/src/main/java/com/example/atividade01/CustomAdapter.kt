package com.example.atividade01

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.atividade01.databinding.ActivityMainBinding

class CustomAdapter(private val dataSet: ArrayList<Filmes>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    private lateinit var bdng : ActivityMainBinding

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nome : TextView
        val ano : TextView
        val autor : TextView

        val contexto = this
        init {
            nome = view.findViewById(R.id.txtnome)
            ano = view.findViewById(R.id.txtano)
            autor = view.findViewById(R.id.txtautor)

        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        var filme : Filmes = dataSet.get(position)
        viewHolder.nome.text = "NOME: " + filme.nome
        viewHolder.ano.text = "ANO: " + filme.ano
        viewHolder.autor.text = "AUTOR: " + filme.autor

        viewHolder.itemView.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
               MainActivity.setFilme(filme, position)
            }
        })
    }
    public fun inserir(filme : Filmes){
        dataSet.add(filme)
        notifyItemInserted(itemCount)
    }
    public fun excluir(position: Int){
        dataSet.removeAt(position)
        notifyItemRemoved(position)
    }
    public fun editar(position: Int, filme: Filmes){
        dataSet.set(position, filme)
        notifyItemChanged(position)
    }

    public fun onClick(view: View){
        Log.d("testando", "fui clicado")
    }

    override fun getItemCount() = dataSet.size

}