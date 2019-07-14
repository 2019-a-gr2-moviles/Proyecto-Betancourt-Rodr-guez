package com.example.foodtruck.clases.detalle

import com.example.foodtruck.clases.comida.Comida
import com.example.foodtruck.clases.pedido.Pedido

class DetalleR(
    id: Int,
    cantidad: Int,
    val id_pd: Pedido,
    val id_cmd: Comida
) : Detalle(
    id, cantidad
) {

}