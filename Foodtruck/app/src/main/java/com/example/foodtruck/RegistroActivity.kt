package com.example.foodtruck

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_registro.*

class RegistroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

    btn_ir.setOnClickListener {
        ir()
    }
    }

    fun ir(){
        val intent = Intent(
            this,
            HomeActivity::class.java
        )

        startActivity(intent)
    }


}
