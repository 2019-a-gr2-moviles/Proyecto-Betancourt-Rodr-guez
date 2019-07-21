package com.example.foodtruck.actividades

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.beust.klaxon.Klaxon
import com.example.foodtruck.R
import com.example.foodtruck.clases.cliente.ClienteR
import com.example.foodtruck.clases.foodtruck.Foodtruck
import com.example.foodtruck.clases.foodtruck.FoodtruckR
import com.example.foodtruck.clases.persona.PersonaR
import com.example.foodtruck.servidor.Servidor
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() {

//    private listaPersonas =

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
//        obtenerListaClientes()
        val spinner = findViewById(R.id.spinner_usuario) as Spinner

        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.spinner_arreglo, android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        btn_registrar_foodtruck.setOnClickListener {
            irCrearFoodtruck()
        }

        btn_iniciar_sesion.setOnClickListener {
            val contrasenia = txt_home_contrasenia.text.toString()
            val usuario = txt_home_usuario.text.toString()
            val tipoUsuario = spinner_usuario.selectedItem.toString()
            Log.i("http", "$tipoUsuario //// $usuario  //// $contrasenia")
            if (tipoUsuario == "Cliente") {
                verificarCliente(usuario, contrasenia)
            } else {
                verificarFoodtruck(usuario, contrasenia)
            }

        }

    }

    fun verificarCliente(usuario: String, contrasenia: String) {

        val url = Servidor.url("cliente") + "?usuario_cl=$usuario"
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

                        val cliente = Klaxon()
                            .parseArray<ClienteR>(data)!!

                        runOnUiThread {
                            if (cliente != null) {
//                                if (cliente.contrasenia_cl == contrasenia) {
//                                }
                            }
                        }
                    }
                }
            }
    }

    fun verificarFoodtruck(usuario: String, contrasenia: String) {

        val url = Servidor.url("foodtruck") + "?usuario_ft=$usuario"
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

                        val foodtrucks = Klaxon()
                            .parseArray<FoodtruckR>(data)!!

                        if (foodtrucks != null) {
                            foodtrucks.forEach { foodtruck ->
                                if (foodtruck.contrasenia_ft == contrasenia) {
                                    runOnUiThread {
                                        irAnadirPlato(foodtruck.id!!)
                                    }
                                }
                            }
                        } else {


                        }
                    }
                }
            }
    }

    private fun irCrearFoodtruck() {
        val intent = Intent(
            this,
            CrearFoodtruckActivity::class.java
        )
        startActivity(intent)
    }

    private fun irMapa() {
        val intent = Intent(
            this,
            MapaActivity::class.java
        )
        startActivity(intent)
    }

    private fun irAnadirPlato(idFoodtruck: Int) {
        val intent = Intent(
            this,
            AddPlatosActivity::class.java
        )
        Log.i("http", "enviandoooo ID  ${idFoodtruck}")
        intent.putExtra("id_ft", idFoodtruck)
        startActivity(intent)
    }
}
