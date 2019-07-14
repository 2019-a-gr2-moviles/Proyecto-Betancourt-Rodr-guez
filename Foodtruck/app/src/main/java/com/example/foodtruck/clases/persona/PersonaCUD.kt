package com.example.foodtruck.clases.persona

class PersonaCUD(
    id: Int = -1,
    nombre_prs: String,
    apellido_prs: String,
    cedula: String,
    fecha_nacimiento: String
) : Persona(
    id, nombre_prs, apellido_prs, cedula, fecha_nacimiento
) {
}