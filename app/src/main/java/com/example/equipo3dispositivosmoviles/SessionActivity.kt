package com.example.equipo3dispositivosmoviles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth

class SessionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iniciosesion)
        Setup()
    }


    private fun Setup(){

        title = "Iniciar Sesion"
        val Loggin = findViewById<Button>(R.id.LogginButton)
        val Email = findViewById<TextView>(R.id.Correo)
        val Contra = findViewById<TextView>(R.id.Contrasena)

        Loggin.setOnClickListener(){
            if (Email.text.isNotEmpty() && Contra.text.isNotEmpty()){
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(Email.text.toString(),Contra.text.toString())
                    .addOnCompleteListener{
                        if(it.isSuccessful){
                            if(it.result?.user?.isEmailVerified == true){
                                Inicio(it.result?.user?.email?:"",ProviderType.BASIC)
                            }
                            else{
                                sendEmailVerification()
                                FirebaseAuth.getInstance().signOut()
                                MostrarMsg()
                            }
                        }else{
                            MostrarError()
                        }
                    }

            }
        }
    }

    private fun MostrarMsg(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Verificacion de Cuenta")
        builder.setMessage("Te enviamos una verificacion de cuenta a tu correo," +
                " se necesita aceptar para utilizar la aplicacion")
        builder.setPositiveButton("Aceptar",null)
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

    private fun MostrarError(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog = builder.create()
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