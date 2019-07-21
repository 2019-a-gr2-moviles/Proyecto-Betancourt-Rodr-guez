package com.example.foodtruck.actividades

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import com.beust.klaxon.Klaxon
import com.example.foodtruck.R
import com.example.foodtruck.adaptadores.AdaptadorComidaFoodtruck
import com.example.foodtruck.clases.comida.ComidaCUD
import com.example.foodtruck.clases.comida.ComidaR
import com.example.foodtruck.servidor.Servidor
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_add_platos.*
import java.lang.Exception

class AddPlatosActivity : AppCompatActivity() {

    private var listaComida = arrayListOf<ComidaR>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_platos)
        val idFoodtruck: Int = this.intent.getIntExtra("id_ft", -5)
        obtenerComida(idFoodtruck)

        btn_add_plato.setOnClickListener {
            irCrearPlato(idFoodtruck)
        }
    }


    fun iniciarRecyclerView(
        listaComida: ArrayList<ComidaR>,
        actividad: AddPlatosActivity,
        recyclerView: androidx.recyclerview.widget.RecyclerView,
        idFoodtruck: Int
    ) {
        val adaptadorComida = AdaptadorComidaFoodtruck(listaComida, actividad, recyclerView, idFoodtruck)
        rv_comida_foodtruck.adapter = adaptadorComida
        rv_comida_foodtruck.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        rv_comida_foodtruck.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(actividad)

        adaptadorComida.notifyDataSetChanged()
    }

    fun obtenerComida(idFoodtruck: Int) {
        //this.listaClientes.clear()
        try {
            val url = Servidor.url("comida") + "?id_ft=$idFoodtruck"
            Log.i("http", url)

            url.httpGet()
                .responseString { request, response, result ->
                    when (result) {
                        is Result.Failure -> {
                            val ex = result.getException()
                            Log.i("http", "Error: ${ex.message}")
                        }
                        is Result.Success -> {
                            val data = result.get()
                            Log.i("http", "Data: ${data}")

                            listaComida = Klaxon()
                                .parseArray<ComidaR>(data) as ArrayList<ComidaR>

                            runOnUiThread {
                                iniciarRecyclerView(listaComida, this, rv_comida_foodtruck, idFoodtruck)
                            }
                        }
                    }
                }
        } catch (e: Exception) {
            Log.i("http", "${e}")
        }
    }

    fun eliminarComida(idComida: Int, idFoodtruck: Int) {
        val url = Servidor.url("comida") + "/$idComida"

        url.httpDelete()
            .responseString { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http", "Error: ${ex.message}")
                    }
                    is Result.Success -> {
                        runOnUiThread {
                            obtenerComida(idFoodtruck)
                        }
                    }
                }
            }
    }

    fun irCrearPlato(idFoodtruck: Int) {
        val intent = Intent(
            this,
            InsertarPlatosActivity::class.java
        )
        intent.putExtra("id_ft", idFoodtruck)
        startActivity(intent)
    }

    fun irAnadirPlato(idFoodtruck: Int) {
        val intent = Intent(
            this,
            this::class.java
        )
        intent.putExtra("id_ft", idFoodtruck)
        startActivity(intent)
    }

    fun irActualizarComida(comida: ComidaCUD) {
        val intent = Intent(
            this,
            ActualizarPlatosActivity::class.java
        )
        intent.putExtra("id_cmd", comida.id)
        intent.putExtra("id_ft", comida.id_ft)
        startActivity(intent)
    }
}
