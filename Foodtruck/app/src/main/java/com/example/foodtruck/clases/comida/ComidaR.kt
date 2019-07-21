package com.example.foodtruck.clases.comida

import com.example.foodtruck.clases.detalle.Detalle
import com.example.foodtruck.clases.foodtruck.Foodtruck

class ComidaR(
    id: Int,
    nombre_cmd: String,
    precio_cmd: Double,
    descripcion: String,
    var id_ft: Foodtruck?,
    var detalleComida: ArrayList<Detalle>? = arrayListOf<Detalle>()
) : Comida(
    id, nombre_cmd, precio_cmd, descripcion
) {
}