package com.example.foodtruck.actividades

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.beust.klaxon.Klaxon
import com.example.foodtruck.R
import com.example.foodtruck.clases.foodtruck.FoodtruckCUD
import com.example.foodtruck.clases.persona.Persona
import com.example.foodtruck.clases.persona.PersonaCUD
import com.example.foodtruck.clases.persona.PersonaR
import com.example.foodtruck.servidor.Servidor
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_crear_foodtruck.*
import java.lang.Exception

class CrearFoodtruckActivity : AppCompatActivity() {

    private var listaPersonas = arrayListOf<PersonaR>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_foodtruck)
//        obtenerListaPersonas()
        btn_crear_foodtruck.setOnClickListener {
            try {
                val persona = PersonaCUD(
                    -1,
                    txt_nombre_persona.text.toString(),
                    txt_apellido_foodtruck.text.toString(),
                    txt_cedula_foodtruck.text.toString(),
                    txt_fecha_foodtruck.text.toString()
                )

                val foodtruck = FoodtruckCUD(
                    persona.cedula,
                    0,
                    txt_nombre_foodtruck.text.toString(),
                    "",
                    "",
                    txt_usuario_foodtruck.text.toString(),
                    txt_contrasenia_foodtruck.text.toString()

                )
                try {
                    insertarPersona(persona)
                } catch (e: Exception) {
                    Log.i("http", "CATCH 2: $e")
                }

                insertarFoodtruck(foodtruck)
            } catch (e: Exception) {
                Log.i("http", "CATCH : $e")
            }
        }
    }

    fun insertarFoodtruck(foodtruck: FoodtruckCUD) {

        val url = Servidor.url("foodtruck")
        val json = """
            {
                "cedula": "${foodtruck.cedula}",             
                "nombre_ft": "${foodtruck.nombre_ft}",
                "usuario_ft": "${foodtruck.usuario_ft}",
                "contrasenia_ft": "${foodtruck.contrasenia_ft}"
            }
                    """
        Log.i("http", url)
        Log.i("http", json)
        url.httpPost().body(json)
            .responseString { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http", "Error: ${ex.message}")
                    }
                    is Result.Success -> {
                        runOnUiThread {
                            irLogin()
                        }
                    }
                }
            }
    }

    fun existePersona(persona: Persona): PersonaR {
        val personaAux: PersonaR? = listaPersonas.find { personaAux: PersonaR ->
            persona.cedula == personaAux.cedula
        }
        return personaAux!!
    }

    fun insertarPersona(persona: PersonaCUD) {

        val url = Servidor.url("persona")
        val json = """
            {
                "nombre_prs": "${persona.nombre_prs}",             
                "apellido_prs": "${persona.apellid_prs}",
                "cedula": "${persona.cedula}",
                "fecha_nacimiento": "${persona.fecha_nacimiento}"
            }
                    """
        Log.i("http", url)
        Log.i("http", json)
        url.httpPost().body(json)
            .responseString { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http", "Error: ${ex.message}")
                    }
                    is Result.Success -> {
                        irLogin()
                    }
                }
            }
    }

    fun irLogin() {
        val intent = Intent(
            this,
            HomeActivity::class.java
        )
        startActivity(intent)
    }

    private fun obtenerListaPersonas() {
        val url = Servidor.url("persona")
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

                        listaPersonas = Klaxon()
                            .parseArray<PersonaR>(data)!! as ArrayList<PersonaR>
                    }
                }
            }
    }
}
