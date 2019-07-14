package com.example.foodtruck.clases.cliente

class ClienteCUD(
    val id_prs: Int,
    id: Int = -1,
    usuario_cl: String,
    contrasenia_cl: String,
    latitud_cl: String,
    longitud_cl: String
) : Cliente(
    id, usuario_cl, contrasenia_cl, latitud_cl, longitud_cl
) {
}