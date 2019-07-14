package com.example.foodtruck.clases.comida

import com.example.foodtruck.clases.detalle.Detalle
import com.example.foodtruck.clases.foodtruck.Foodtruck

class ComidaR(
    id: Int,
    nombre_cmd: String,
    precio_cmd: Double,
    descripcion: String,
    val id_ft: Foodtruck,
    val detalleComida: ArrayList<Detalle>?
) : Comida(
    id, nombre_cmd, precio_cmd, descripcion
) {
}