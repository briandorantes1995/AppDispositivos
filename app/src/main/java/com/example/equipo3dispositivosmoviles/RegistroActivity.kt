package com.example.equipo3dispositivosmoviles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth

class RegistroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        //Setup
        Setup()
    }
    private fun Setup(){
        title = "Autenticacion"
        val Registro = findViewById<Button>(R.id.RegistrarButton)
        val Email = findViewById<TextView>(R.id.Correo)
        val Contra = findViewById<TextView>(R.id.Contrasena)
        Registro.setOnClickListener(){
            if (Email.text.isNotEmpty() && Contra.text.isNotEmpty()){
                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(Email.text.toString(),Contra.text.toString())
                    .addOnCompleteListener{
                        if(it.isSuccessful){
                            Inicio(it.result?.user?.email?:"",ProviderType.BASIC)
                        }else{
                            MostrarError()
                        }
                    }

            }
        }
    }

    private fun MostrarError(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error")
        builder.setPositiveButton("Aceptar",null)
        val dialog:AlertDialog = builder.create()
        dialog.show()
    }

    private fun Inicio(email:String,provider:ProviderType){
        val Ini= Intent(this,HomeActivity::class.java).apply{
            putExtra("email",email)
            putExtra("provider",provider.name)
        }
        startActivity(Ini)
    }
}