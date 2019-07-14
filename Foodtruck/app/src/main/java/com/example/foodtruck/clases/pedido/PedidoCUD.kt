package com.example.foodtruck.clases.pedido


class PedidoCUD(
    val id_cl: Int,
    id: Int = -1,
    fecha_pd: String,
    monto_total: Double,
    tarjeta_ped: String
) : Pedido(
    id, fecha_pd, monto_total, tarjeta_ped
) {
}