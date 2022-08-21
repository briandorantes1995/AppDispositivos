package com.example.equipo3dispositivosmoviles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
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
                            sendEmailVerification()
                            MostrarMsg()
//                            onBackPressed()
//                            Inicio(it.result?.user?.email?:"",ProviderType.BASIC)
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

    private fun MostrarMsg(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Verificacion de Cuenta")
        builder.setMessage("Te enviamos una verificacion de cuenta a tu correo," +
                " se necesita aceptar para utilizar la aplicacion")
        builder.setPositiveButton("Ok") { dialog, which ->
            Home()
        }
        val dialog:AlertDialog = builder.create()
        dialog.show()
    }

    private fun sendEmailVerification() {
        //get instance of firebase auth
        val firebaseAuth = FirebaseAuth.getInstance()
        //get current user
        val firebaseUser = firebaseAuth.currentUser

        //send email verification
        firebaseUser!!.sendEmailVerification()
            .addOnSuccessListener {
                Toast.makeText(this, "Correo Enviado", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "No se logron enviar el correo " + e.message, Toast.LENGTH_SHORT).show()
            }

    }
    private fun Home(){
        val Ini= Intent(this,Inicio::class.java).apply{

        }
        startActivity(Ini)
    }
}