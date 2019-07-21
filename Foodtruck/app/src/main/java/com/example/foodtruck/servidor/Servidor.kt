package com.example.foodtruck.servidor

// archivo ubicado en .gitignore
class Servidor {

    companion object {
        
        private val ip = "192.168.0.103"
        private val puerto = "1337"

        fun url(ruta: String): String {
            var rutaAux = ""
            when (ruta) {
                "persona" -> rutaAux = "persona"
                "cliente" -> rutaAux = "cliente"
                "foodtruck" -> rutaAux = "foodtruck"
                "comida" -> rutaAux = "comida"
                "pedido" -> rutaAux = "pedido"
                "detalle" -> rutaAux = "detalle"
                else -> rutaAux = ""
            }
            return "http://${ip}:${puerto}/${rutaAux}"
        }
    }
}