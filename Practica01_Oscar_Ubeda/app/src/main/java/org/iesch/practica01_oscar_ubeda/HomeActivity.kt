package org.iesch.practica01_oscar_ubeda

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.iesch.practica01_oscar_ubeda.databinding.ActivityMainBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        val usuario = intent.getStringExtra("usuario") ?: "Invitado"

        binding.textPerfil.text = "Hola $usuario"

        binding.constraintEdadCanina.setOnClickListener {
            val intent = Intent(this, EdadCaninaActivity::class.java)
            startActivity(intent)
        }

        binding.constraintSuperHeroes.setOnClickListener {
            val intent = Intent(this, SuperHeroesActivityMain::class.java)
            startActivity(intent)
        }
    }
}