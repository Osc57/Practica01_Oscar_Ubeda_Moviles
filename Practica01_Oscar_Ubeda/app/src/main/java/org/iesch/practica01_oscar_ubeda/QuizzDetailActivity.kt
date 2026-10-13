package org.iesch.practica01_oscar_ubeda

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.iesch.practica01_oscar_ubeda.databinding.ActivityQuizzDetailBinding

class QuizzDetailActivity : AppCompatActivity() {
    // Declaracion del binding
    private lateinit var binding: ActivityQuizzDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityQuizzDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.quizzesDetail)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        // Leer los datos de la pantalla main

        // Recibir indice (pregunta actual)
        val indice = intent.getIntExtra("indice", 0)

        // Recibir string (respuesta)
        val respuesta = intent.getStringExtra("respuesta")

        // Obtener array de las respuestas correctas
        val correctas = resources.getStringArray(R.array.correctas)

        // Obtener tamaño del array (total de respuestas correctas). Con esto se puede hallar cuál es la última
        val total = correctas.size

        // Variable para hacer las comprobaciones y no repetir codigo
        val esUltima = indice == total-1

        // Comprobacion del estado del quiz, existen 3 estados: ultima pregunta = finalizado, acierto = correcto, error = incorrecto
        if (esUltima) { // Si se ha llegado a la ultima

            // Pintar en la pantalla el icono de finalizado, el titulo, el subtitulo y el texto del boton a "Start again"

            // El icono
            binding.iconoImagen.setImageResource(R.drawable.icon_state_finish)
            // El titulo
            binding.tituloEstadoRespuesta.text = getString(R.string.estado_finalizado)
            // El subtitulo
            binding.subtituloEstadoRespuesta.text = getString(R.string.subtitulo_estado_finalizado)
            // Texto del boton
            binding.botonContinuar.text = getString(R.string.texto_boton_comenzar_de_nuevo)

        } else if(respuesta == correctas[indice]){ // Si acertó

            // Pintar en la pantalla el icono de correcto, el titulo y el subtitulo

            // El icono
            binding.iconoImagen.setImageResource(R.drawable.icon_check_dialog)
            // El titulo
            binding.tituloEstadoRespuesta.text = getString(R.string.estado_correcto)
            // El subtitulo
            binding.subtituloEstadoRespuesta.text = getString(R.string.subtitulo_estado_correcto)


        } else { // Si no acierta

            // Pintar en la pantalla el icono de incorrecto, el titulo y el subtitulo

            // El icono
            binding.iconoImagen.setImageResource(R.drawable.icon_wrong_dialog)
            // El titulo
            binding.tituloEstadoRespuesta.text = getString(R.string.estado_incorrecto)
            // El subtitulo
            binding.subtituloEstadoRespuesta.text = getString(R.string.subtitulo_estado_incorrecto)
        }

        // Configuracion del botón
        binding.botonContinuar.setOnClickListener {

            // Cálculo de la siguiente pregunta:

            // ¿A qué pregunta se quiere ir?
            val siguiente = if (esUltima) 0 else indice+1 // Si se encuentra en la ultima pregunta, se retorna el indice 0, si no, solamente se retorna el indice actual + 1 (porque hay que avanzar)

            // Crear intent para volver a QuizMain
            val intentRetorno = Intent(this, QuizzMainActivity::class.java)
            intentRetorno.putExtra("indice", siguiente)

            // Comenzar activity
            startActivity(intentRetorno)

            // Destroy (pantalla actual)
            finish()
        }

    }
}

