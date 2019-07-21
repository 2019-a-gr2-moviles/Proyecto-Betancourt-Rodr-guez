package com.example.foodtruck.actividades

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.foodtruck.R
import com.example.foodtruck.clases.comida.ComidaCUD
import com.example.foodtruck.servidor.Servidor
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_actualizar_platos.*

class ActualizarPlatosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_platos)
        val idComida = this.intent.getIntExtra("id_cmd", -1)
        val idFoodtruck = this.intent.getIntExtra("id_ft", -1)
        btn_actualizar_plato.setOnClickListener {
            val comida = ComidaCUD(
                idFoodtruck,
                idComida,
                txt_actualizar_nombre_plato.text.toString(),
                txt_actualizar_precio_plato.text.toString().toDouble(),
                txt_atualizar_descripcion_plato.text.toString()
            )
            actualizarComida(comida)
        }
    }

    fun actualizarComida(comida: ComidaCUD) {
        val url = Servidor.url("comida") + "/${comida.id}"
        val json = """
    {
        "nombre_cmd": "${comida.nombre_cmd}",
        "precio_cmd": ${comida.precio_cmd},
        "descripcion": "${comida.descripcion}"
    }
                    """.trimIndent()
        Log.i("http", url)
        Log.i("http", json)
        url.httpPut().body(json)
            .responseString { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http", "Error: ${ex.message}")
                    }
                    is Result.Success -> {
                        runOnUiThread {
                            irAnadirPlato(comida.id_ft)
                        }
                    }
                }
            }
    }

    fun irAnadirPlato(idFoodtruck: Int) {
        val intent = Intent(
            this,
            AddPlatosActivity::class.java
        )
        intent.putExtra("id_ft", idFoodtruck)
        startActivity(intent)
    }
}
