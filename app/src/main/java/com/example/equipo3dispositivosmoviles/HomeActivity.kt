package com.example.equipo3dispositivosmoviles

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import java.util.*


enum class ProviderType{
    BASIC
}
class HomeActivity : AppCompatActivity(),AdapterView.OnItemSelectedListener {
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
        val admins =arrayOf("diego-fraga2011@hotmail.com", "dany2001deleon@gmail.com", "briandorantes@hotmail.com")
        val correo = findViewById<TextView>(R.id.EmailtextView)

        //Selector de Idioma
        val spinner: Spinner = findViewById(R.id.spinner)
        ArrayAdapter.createFromResource(this,
            R.array.language, android.R.layout.simple_spinner_item).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        spinner.onItemSelectedListener =this


        correo.text = email
        val admin = findViewById<TextView>(R.id.adminview)
        if(admins.contains(email)){
            admin.visibility = View.VISIBLE
        }
        val proovedor = findViewById<TextView>(R.id.ProvidertextView)
        proovedor.text = provider
        val cerrar = findViewById<Button>(R.id.CerrarSession)
        cerrar.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }

    }



    //Funcion Cambiar de Idioma
    private fun setLanguageForApp(language: String) {
        val locale: Locale
        if (language == "not-set") {
            locale = Locale.getDefault()
        } else {
            locale = Locale(language)
        }
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        if (position == 1){
            val languague = "en-rUS"
            setLanguageForApp(languague)
            startActivity(intent)
            finish()
        }else if(position == 0){
            val languague = "es-rMX"
            setLanguageForApp(languague)
            startActivity(intent)
            finish()
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }
}


