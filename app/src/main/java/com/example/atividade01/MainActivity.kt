package com.example.atividade01

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.atividade01.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    companion object{
        private lateinit var bdng : ActivityMainBinding
        private var posi : Int = -1

        fun setFilme(filme : Filmes, position : Int){
            bdng.textName.setText(filme.nome)
            bdng.textAno.setText(filme.ano)
            bdng.textAutor.setText(filme.autor)
            posi = position
        }
    }
    private lateinit var listaFilmes : ArrayList<Filmes>
    private lateinit var adpter : CustomAdapter
    private lateinit var recyclerView: RecyclerView

    private fun setRecyclerView(){
        listaFilmes = arrayListOf<Filmes>()
        adpter = CustomAdapter(listaFilmes)
        recyclerView = bdng.rview
        recyclerView.setLayoutManager(LinearLayoutManager(this))
        recyclerView.setItemAnimator(DefaultItemAnimator())
        recyclerView.setAdapter(adpter)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bdng = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setRecyclerView()
        bdng.buttonAdd.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                var nome = bdng.textName.text.toString()
                var ano = bdng.textAno.text.toString()
                var autor = bdng.textAutor.text.toString()

                var filme1 = Filmes(nome,  ano,  autor)
                adpter.inserir(filme1)

                bdng.textName.setText("")
                bdng.textAno.setText("")
                bdng.textAutor.setText("")
            }
        })
        bdng.buttonExcluir.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                if (posi >= 0){
                    adpter.excluir(posi)
                    posi = -1
                }
            }
        })

        bdng.buttonModificar.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                if (posi >= 0){
                    var nome = bdng.textName.text.toString()
                    var ano = bdng.textAno.text.toString()
                    var autor = bdng.textAutor.text.toString()

                    var filme1 = Filmes(nome,  ano,  autor)
                    adpter.editar(posi, filme1)
                    posi = -1

                    bdng.textName.setText("")
                    bdng.textAno.setText("")
                    bdng.textAutor.setText("")
                }
            }
        })
    }
}