package com.example.foodtruck.clases.foodtruck

class FoodtruckCUD(
    var cedula: String,
    id: Int = -1,
    nombre_ft: String,
    latitud_ft: String,
    longitud_ft: String,
    usuario_ft: String,
    contrasenia_ft: String
) : Foodtruck(
    id, nombre_ft, latitud_ft, longitud_ft, usuario_ft, contrasenia_ft
) {
}