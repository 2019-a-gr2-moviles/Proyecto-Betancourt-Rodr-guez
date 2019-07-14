package com.example.foodtruck.actividades

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.foodtruck.R

class RegistroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
//
//    btn_ir.setOnClickListener {
//        ir()
//    }
    }

    fun ir(){
        val intent = Intent(
            this,
            HomeActivity::class.java
        )

        startActivity(intent)
    }


}
