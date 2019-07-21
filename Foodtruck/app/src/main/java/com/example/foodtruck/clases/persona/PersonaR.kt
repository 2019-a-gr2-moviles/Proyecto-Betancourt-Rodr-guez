package com.example.foodtruck.clases.persona

import com.example.foodtruck.clases.cliente.Cliente
import com.example.foodtruck.clases.cliente.ClienteR2
import com.example.foodtruck.clases.foodtruck.Foodtruck
import com.example.foodtruck.clases.foodtruck.FoodtruckR2


class PersonaR(
    var foodtruckDePersona: ArrayList<FoodtruckR2>?,
    var clientePersona: ArrayList<ClienteR2>?,
    id: Int,
    nombre_prs: String,
    apellido_prs: String,
    cedula: String,
    fecha_nacimiento: String
) : Persona(
    id, nombre_prs, apellido_prs, cedula, fecha_nacimiento
) {
}