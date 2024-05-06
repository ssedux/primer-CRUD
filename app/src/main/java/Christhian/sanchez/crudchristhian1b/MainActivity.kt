package Christhian.sanchez.crudchristhian1b

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import modelo.ClaseConexion

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//        mandar a llamar a todos los elemtos
        val txtNombre=findViewById<EditText>(R.id.txt_nombreC)
        val txtDuracion=findViewById<EditText>(R.id.txt_Duracion)
        val txtAutor=findViewById<EditText>(R.id.txt_autor)
        val btnAgregar=findViewById<Button>(R.id.btn_agregar)

//        programar el boton
        btnAgregar.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
//                crear un objeto de la clase conexion
                val objConexion=ClaseConexion().cadenaConexion()
//                crear una variable que contenga un prepareStatement
                val addMusica=objConexion?.prepareStatement("insert into tbMusica values(?,?,?)")!!
                addMusica.setString(1,txtNombre.text.toString())
                addMusica.setInt(2,txtDuracion.text.toString().toInt())
                addMusica.setString(3,txtAutor.text.toString())
                addMusica.executeUpdate()
            }
        }
    }
}