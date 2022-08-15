package com.example.equipo3dispositivosmoviles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

enum class ProviderType{
    BASIC
}
class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //SetUp
        val bundle = intent.extras
        val email = bundle?.getString("email")
        val provider =bundle?.getString("provider")
        setup(email?:"",provider?:"")
    }
    private fun setup(email:String,provider:String) {
        title = "Inicio"
        val correo = findViewById<TextView>(R.id.EmailtextView)
        correo.text = email
        val proovedor = findViewById<TextView>(R.id.ProvidertextView)
        proovedor.text = provider
        val cerrar = findViewById<Button>(R.id.CerrarSession)
        cerrar.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }

    }
}

