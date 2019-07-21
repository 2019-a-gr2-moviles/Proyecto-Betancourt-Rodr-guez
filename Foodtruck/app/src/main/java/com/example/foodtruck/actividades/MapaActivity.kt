package com.example.foodtruck.actividades

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.beust.klaxon.Klaxon
import com.example.foodtruck.R
import com.example.foodtruck.clases.foodtruck.Foodtruck
import com.example.foodtruck.clases.foodtruck.FoodtruckR
import com.example.foodtruck.servidor.Servidor
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapaActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var listaFoodtrucks: ArrayList<FoodtruckR> = arrayListOf()
    private var tienePermisosLocalizacion = false

    init {
        obtenerFoodtrucks()
    }

    fun obtenerFoodtrucks() {
        val url = Servidor.url("foodtruck")
        Log.i("http", url)
        url.httpGet()
            .responseString { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http", "Error: ${ex.message}")
                    }
                    is Result.Success -> {
                        val data = result.get()
                        Log.i("http", "Data: ${data}")

                        listaFoodtrucks = Klaxon()
                            .parseArray<FoodtruckR>(data)!! as ArrayList<FoodtruckR>
                    }
                }
            }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mapa)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        solicitarPermisosLocalizacion()
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    fun solicitarPermisosLocalizacion() {
        val permisoFineLocation = ContextCompat
            .checkSelfPermission(
                this.applicationContext,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )

        val tienePermiso = permisoFineLocation == PackageManager.PERMISSION_GRANTED

        if (tienePermiso) {
            Log.i("mapa", "Tiene permisos de FINE_LOCATION")
            this.tienePermisosLocalizacion = true
        } else {
            // Codigo que vamos a esperar (1)
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 1)
        }

    }
}
