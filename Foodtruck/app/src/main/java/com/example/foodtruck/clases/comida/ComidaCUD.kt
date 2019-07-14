package com.example.foodtruck.clases.comida

class ComidaCUD(
    val id_ft: Int,
    id: Int = -1,
    nombre_cmd: String,
    precio_cmd: Double,
    descripcion: String
) : Comida(
    id, nombre_cmd, precio_cmd, descripcion
) {
}