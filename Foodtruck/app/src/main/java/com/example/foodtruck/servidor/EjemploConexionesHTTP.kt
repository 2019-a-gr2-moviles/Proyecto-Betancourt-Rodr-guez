package com.example.foodtruck.servidor

import android.util.Log
import com.example.foodtruck.clases.cliente.ClienteCUD
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.result.Result

class EjemploConexionesHTTP {
    companion object {

        fun insertarCliente(cliente: ClienteCUD) {
            val url = Servidor.url("cliente")
            val json = """
            {
              usuario_cl: = "${cliente.usuario_cl}",
              contrasenia_cl: "${cliente.contrasenia_cl}",
              latitud_cl: "${cliente.latitud_cl}",
              longitud_cl: "${cliente.longitud_cl}",
                 id_prs: ${cliente.id_prs}
                        }
                    """

            url.httpPost().body(json)
                .responseString { request, response, result ->
                    when (result) {
                        is Result.Failure -> {
                            val ex = result.getException()
                            Log.i("http", "Error: ${ex.message}")
                        }
                        is Result.Success -> {
//                            runOnUiThread {
                            //Aqui va el intent
                            //ListaIntents.<nombreMetodoIntent>()
//                            }
                        }
                    }
                }

        }


        fun actualizarCliente(cliente: ClienteCUD) {
            val url = Servidor.url("cliente") + "${cliente.id}"
            val json = """
            {
              usuario_cl: = "${cliente.usuario_cl}",
              contrasenia_cl: "${cliente.contrasenia_cl}",
              latitud_cl: "${cliente.latitud_cl}",
              longitud_cl: "${cliente.longitud_cl}",
                 id_prs: ${cliente.id_prs}
                        }
                    """
            url.httpPut().body(json)
                .responseString { request, response, result ->
                    when (result) {
                        is Result.Failure -> {
                            val ex = result.getException()
                            Log.i("http", "Error: ${ex.message}")
                        }
                        is Result.Success -> {

                            //                            runOnUiThread {
                            //Aqui va el intent
                            //ListaIntents.<nombreMetodoIntent>()
//                            }
                        }
                    }
                }
        }


        fun eliminarCliente(cliente: ClienteCUD) {
            val url = Servidor.url("cliente") + "${cliente.id}"

            url.httpDelete()
                .responseString { request, response, result ->
                    when (result) {
                        is Result.Failure -> {
                            val ex = result.getException()
                            Log.i("http", "Error: ${ex.message}")
                        }
                        is Result.Success -> {
                            //                            runOnUiThread {
                            //Aqui va el intent
                            //ListaIntents.<nombreMetodoIntent>()
//                            }
                        }
                    }
                }
        }


    }
}