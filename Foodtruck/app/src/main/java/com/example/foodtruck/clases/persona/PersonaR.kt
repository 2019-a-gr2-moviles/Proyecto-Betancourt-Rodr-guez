package com.example.foodtruck.clases.persona

import com.example.foodtruck.clases.cliente.Cliente
import com.example.foodtruck.clases.foodtruck.Foodtruck


class PersonaR(
    id: Int,
    nombre_prs: String,
    apellido_prs: String,
    cedula: String,
    fecha_nacimiento: String,
    val foodtruckPersona: ArrayList<Foodtruck>?,
    val clientePersona: ArrayList<Cliente>?
) : Persona(
    id, nombre_prs, apellido_prs, cedula, fecha_nacimiento
) {
}