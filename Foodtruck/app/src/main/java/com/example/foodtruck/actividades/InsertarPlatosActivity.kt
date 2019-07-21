package com.example.foodtruck.actividades

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.foodtruck.R
import com.example.foodtruck.clases.comida.ComidaCUD
import com.example.foodtruck.servidor.Servidor
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_insertar_platos.*

class InsertarPlatosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertar_platos)
        val idFoodtruck: Int = this.intent.getIntExtra("id_ft", -5)

        btn_insertar_plato.setOnClickListener {
            val comida = ComidaCUD(
                idFoodtruck,
                -5,
                txt_insertar_nombre_plato.text.toString(),
                txt_insertar_precio_plato.text.toString().toDouble(),
                txt_insertar_descripcion_plato.text.toString()
            )
            insertarComida(comida)
        }
        btn_cancelar_insertar.setOnClickListener {
            irAddPlatos(idFoodtruck)
        }
    }

    private fun insertarComida(comida: ComidaCUD) {
        val url = Servidor.url("comida")
        val json = """
            {
               "nombre_cmd": "${comida.nombre_cmd}",
               "precio_cmd": ${comida.precio_cmd},
               "descripcion": "${comida.descripcion}",
               "id_ft": ${comida.id_ft}
              
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
                        Log.i("http", "insercion comida ${data}")
                        runOnUiThread {
                            irAddPlatos(comida.id_ft)
                        }
                    }
                }
            }
    }

    private fun irAddPlatos(idFoodtruck: Int) {
        val intent = Intent(
            this,
            AddPlatosActivity::class.java
        )
        intent.putExtra("id_ft", idFoodtruck)
        startActivity(intent)
    }
}
