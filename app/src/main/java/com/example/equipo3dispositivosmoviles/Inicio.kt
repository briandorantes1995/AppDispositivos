package com.example.equipo3dispositivosmoviles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Inicio : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)
        Setup()
    }
    private fun Setup(){
        title = "Inicio"
        val Registro = findViewById<Button>(R.id.RegistrarButton)
        val Loggin = findViewById<Button>(R.id.LogginButton)
        Registro.setOnClickListener(){
            Registro()
        }

        Loggin.setOnClickListener(){
            InicioSesion()
        }
    }

    private fun Registro(){
        val registro= Intent(this,RegistroActivity::class.java)
        startActivity(registro)
    }
    private fun InicioSesion(){
        val inicio= Intent(this,SessionActivity::class.java)
        startActivity(inicio)
    }
}