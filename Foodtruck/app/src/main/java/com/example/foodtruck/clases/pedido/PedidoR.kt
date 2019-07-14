package com.example.foodtruck.clases.pedido

import com.example.foodtruck.clases.cliente.Cliente
import com.example.foodtruck.clases.detalle.Detalle

class PedidoR(
    id: Int,
    fecha_pd: String,
    monto_total: Double,
    tarjeta_ped: String,
    val id_cl: Cliente,
    val detallePedido: ArrayList<Detalle>?
) : Pedido(
    id, fecha_pd, monto_total, tarjeta_ped
) {
}