package org.iesch.practica01_oscar_ubeda

import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.iesch.practica01_oscar_ubeda.databinding.ActivityQuizzMainBinding

class QuizzMainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuizzMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Configuracion del binding
        binding = ActivityQuizzMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.quizzesMain)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val indice = intent.getIntExtra("indice", 0)

        val preguntas = resources.getStringArray(R.array.preguntas)

        val opcionesA = resources.getStringArray(R.array.opciones_a)

        // Cargar array de los strings de opcion B
        val opcionesB = resources.getStringArray(R.array.opciones_b)

        // Obtener enunciado
        val enunciado = binding.enunciadoPregunta

        // Obtener progreso para la barra de progreso
        val progresoTexto = binding.textoProgreso

        // Obtener barra de progreso
        val barraProgreso = binding.barraProgreso

        // Obtener radioButtons
        val opcion1 = binding.opcion1
        val opcion2 = binding.opcion2

        // Apartado de Llenado
        enunciado.text = preguntas[indice]

        // Rellenar strings con placeholder (en el texto del progreso)
        progresoTexto.text = getString(R.string.formato_progreso, indice+1, preguntas.size)

        // Barra progreso
        barraProgreso.max = preguntas.size
        barraProgreso.progress = indice+1

        // RadioButtons
        opcion1.text = opcionesA[indice]
        opcion2.text = opcionesB[indice]

        // OnClickListener del boton
        binding.botonEnviar.setOnClickListener {

            // Capturar el id de la opcion que se marcó
            val idRadioButton = binding.respuestas.checkedRadioButtonId

            // Comprobar si se ha seleccionado alguna opcion
            if (idRadioButton == -1) { // Si no se ha seleccionado ninguna opcion
                // Configuracion del mensaje Toast
                Toast.makeText(this, R.string.mensaje_toast_no_respuesta, Toast.LENGTH_SHORT).show()
            } else {

                // Si se ha seleccionado la opcion, se extrae el texto de la opcion seleccionada
                val textoOpcion = findViewById<RadioButton>(idRadioButton).text.toString()

                // Abrir pantalla 2 (QuizDialog)
                val intentRespuesta = Intent(this, QuizzDetailActivity::class.java)
                intentRespuesta.putExtra("indice", indice)
                intentRespuesta.putExtra("respuesta", textoOpcion)


                // Iniciar actividad
                startActivity(intentRespuesta)

                // Cerrar pantalla (destroy)
                finish()
            }
        }
    }
}