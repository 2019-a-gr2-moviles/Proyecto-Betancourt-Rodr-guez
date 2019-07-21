package com.example.foodtruck.actividades

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.beust.klaxon.Klaxon
import com.example.foodtruck.R
import com.example.foodtruck.clases.foodtruck.FoodtruckCUD
import com.example.foodtruck.clases.persona.PersonaCUD
import com.example.foodtruck.clases.persona.PersonaR
import com.example.foodtruck.servidor.Servidor
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_crear_foodtruck.*
import java.lang.Exception

class CrearFoodtruckActivity : AppCompatActivity() {

//    private var listaPersonas = arrayListOf<PersonaR>()
//    private var personaAux: PersonaR? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_foodtruck)
//        obtenerPersona("1718057688")

        btn_crear_foodtruck.setOnClickListener {
//            Log.i("http", "${personaAux!!.cedula}")
            try {
                val persona = PersonaCUD(
                    -1,
                    txt_nombre_persona.text.toString(),
                    txt_apellido_foodtruck.text.toString(),
                    txt_cedula_foodtruck.text.toString(),
                    txt_fecha_foodtruck.text.toString()
                )
                val foodtruck = FoodtruckCUD(
                    persona.id,
                    0,
                    txt_nombre_foodtruck.text.toString(),
                    "",
                    "",
                    txt_usuario_foodtruck.text.toString(),
                    txt_contrasenia_foodtruck.text.toString()
                )

                try {
//                    insertarPersona(persona)
//                    obtenerPersona(persona.cedula)
//                    foodtruck.id_prs = personaAux!!.id
                    insertarFoodtruck(foodtruck)
                } catch (e: Exception) {
//                    obtenerPersonas()
//                    foodtruck.id_prs = idPersona(persona.cedula)
//                    obtenerPersona(persona.cedula)
//                    foodtruck.id_prs = personaAux!!.id
//                    insertarFoodtruck(foodtruck)

                }

            } catch (e: Exception) {
                Log.i("http", "CATCH : $e")
            }
        }
    }

//    private fun idPersona(cedula: String): Int {
//        val persona = listaPersonas.find {
//            it.cedula == cedula
//        }
//
//        if (persona != null) {
//            return persona.id
//        } else {
//            return -1
//        }
//    }

    private fun insertarFoodtruck(foodtruck: FoodtruckCUD) {

        val url = Servidor.url("foodtruck")
        val json = """
            {        
                "nombre_ft": "${foodtruck.nombre_ft}",
                "usuario_ft": "${foodtruck.usuario_ft}",
                "latitud_ft": "0",
                "longitud_ft": "0",
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
                        val data = result.get()

                        runOnUiThread {
                            irLogin()
                        }
                    }
                }
            }
    }

    private fun insertarPersona(persona: PersonaCUD) {
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
                        val data = result.get()
//                        val persona = Klaxon().parse<Persona>(data)
//                        obtenerPersonas()
                    }
                }
            }
    }

//    fun obtenerPersona(cedula: String) {
//
//        val url = Servidor.url("persona") + "?cedula=$cedula"
//        Log.i("http", url)
//        url.httpGet()
//            .responseString { request, response, result ->
//                when (result) {
//                    is Result.Failure -> {
//                        val ex = result.getException()
//                        Log.i("http", "Error: ${ex.message}")
//                    }
//                    is Result.Success -> {
//                        val data = result.get()
//                        Log.i("http", "Data personaaaaaaaaaaaaaaaaaaa: ${data}")
//
//                        listaPersonas = Klaxon()
//                            .parseArray<PersonaR>(data)!! as ArrayList<PersonaR>
//
//                    }
//                }
//            }
//    }

    private fun irLogin() {
        val intent = Intent(
            this,
            HomeActivity::class.java
        )
        startActivity(intent)
    }

}
