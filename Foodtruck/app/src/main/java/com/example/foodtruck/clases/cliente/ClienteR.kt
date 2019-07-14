package com.example.foodtruck.clases.cliente

import com.example.foodtruck.clases.pedido.Pedido
import com.example.foodtruck.clases.persona.Persona

class ClienteR(
    id: Int,
    usuario_cl: String,
    contrasenia_cl: String,
    latitud_cl: String,
    longitud_cl: String,
    val id_prs: Persona,
    val pedidoCliente: ArrayList<Pedido>?
) : Cliente(
    id, usuario_cl, contrasenia_cl, latitud_cl, longitud_cl
) {
}