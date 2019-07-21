package com.example.foodtruck.adaptadores

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.example.foodtruck.R
import com.example.foodtruck.actividades.AddPlatosActivity
import com.example.foodtruck.clases.comida.ComidaCUD
import com.example.foodtruck.clases.comida.ComidaR

class AdaptadorComidaFoodtruck(
    private val listaComida: ArrayList<ComidaR>,
    private val contexto: AddPlatosActivity,
    private val recyclerView: RecyclerView,
    private val idFoodtruck: Int
) :
    RecyclerView.Adapter<AdaptadorComidaFoodtruck.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var nombreTextView: TextView
        var precioTextView: TextView
        var descripcionTextView: TextView
        var idTextView: TextView
        var eliminarBoton: ImageButton
        var actualizarBoton: ImageButton

        init {
            nombreTextView = view.findViewById(R.id.txv_nombre_comida) as TextView
            precioTextView = view.findViewById(R.id.txv_precio_comida) as TextView
            descripcionTextView = view.findViewById(R.id.txv_descripcion_comida) as TextView
            idTextView = view.findViewById(R.id.txv_id_comida) as TextView
            eliminarBoton = view.findViewById(R.id.btn_eliminar_plato) as ImageButton
            actualizarBoton = view.findViewById(R.id.btn_modificar_plato) as ImageButton

            eliminarBoton.setOnClickListener {
                contexto.eliminarComida(idTextView.text.toString().toInt(), idFoodtruck)
            }

            actualizarBoton.setOnClickListener {
                 val comida: ComidaCUD = ComidaCUD(
                    idFoodtruck,
                    idTextView.text.toString().toInt(),
                    nombreTextView.text.toString(),
                    precioTextView.text.toString().substring(2, precioTextView.text.length).toDouble(),
                    descripcionTextView.text.toString()
                )

                contexto.irActualizarComida(comida)
            }
        }

    }

    //Esta función define el template que vamos a utilizar.
    // El template esta en la carpeta de recursos res/layout -> layout
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): AdaptadorComidaFoodtruck.MyViewHolder {
        val itemView = LayoutInflater
            .from(p0.context)
            .inflate(
                R.layout.layout_menu_foodtruck,
                p0,
                false
            )
        return MyViewHolder(itemView)
    }

    //Devuelve el número de items de la lista
    override fun getItemCount(): Int {
        return listaComida.size
    }

    override fun onBindViewHolder(myViewHolder: AdaptadorComidaFoodtruck.MyViewHolder, position: Int) {
        val comida: ComidaR = listaComida[position]
        myViewHolder.nombreTextView.text = comida.nombre_cmd
        myViewHolder.precioTextView.text = "$ ${comida.precio_cmd.toString()}"
        myViewHolder.descripcionTextView.text = comida.descripcion
        myViewHolder.idTextView.text = comida.id.toString()
    }


}