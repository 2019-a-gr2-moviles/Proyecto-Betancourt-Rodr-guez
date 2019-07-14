package com.example.foodtruck.servidor

// archivo ubicado en .gitignore
class Servidor {
    companion object {
        private val ip = "192.168.0.1"
        private val puerto = "1337"

        fun url(ruta: String): String {
            var ruta = ""
            when (ruta) {
                "persona" -> ruta = "persona/"
                "cliente" -> ruta = "cliente/"
                "foodtruck" -> ruta = "foodtruck/"
                "comida" -> ruta = "comida/"
                "pedido" -> ruta = "pedido/"
                "detalle" -> ruta = "detalle/"
                else -> ruta = ""
            }
            return "http://${ip}:${puerto}/ruta"
        }
    }
}