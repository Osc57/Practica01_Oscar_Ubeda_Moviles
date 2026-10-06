package org.iesch.practica01_oscar_ubeda

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class EdadCaninaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edad_canina)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.edadCan)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Aqui la pantalla esta creada

        //1 - Tomamos el control de todos los elementos de la parte de la UI

        val resultText = findViewById<TextView>(R.id.texto_respuesta)
        val calculateButton = findViewById<Button>(R.id.boton_calcular)
        val ageEdit = findViewById<EditText>(R.id.edit_edad)

        //2 - Los botones tienen la propiedad setOnClickListener al pulsarlo

        calculateButton.setOnClickListener {
            //Aqui metemos el code de lo que querramos hacer cuando pulsemos el botón
            val edadString = ageEdit.text.toString()

            if (edadString.isEmpty()) {
                //3 - Mostramos un mensaje de tipo Toast
                Toast.makeText(this, R.string.texto_toast, Toast.LENGTH_LONG).show()
            } else {
                //Necesitamos pasar el valor a entero
                val edadInt = edadString.toInt()

                val dogAge = edadInt * 7

                //println(dogAge)
                resultText.text = getString(R.string.resultado_texto, dogAge)
            }


        }

    }
}