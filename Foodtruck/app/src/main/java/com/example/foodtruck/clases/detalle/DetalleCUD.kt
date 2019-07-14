package com.example.foodtruck.clases.detalle

class DetalleCUD(
    val id_pd: Int,
    val id_cmd: Int,
    id: Int = -1,
    cantidad: Int
) : Detalle(
    id, cantidad
) {

}