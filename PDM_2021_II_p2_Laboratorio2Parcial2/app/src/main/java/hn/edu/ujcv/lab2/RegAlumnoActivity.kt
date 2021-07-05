package hn.edu.ujcv.lab2
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_reg_alumno.*

class RegAlumnoActivity : AppCompatActivity() {
    var valores: HashMap<Int,String> = hashMapOf()
    var contador : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg_alumno)
        btnRegAl.setOnClickListener{registrarAl()}
        btnRegClase.setOnClickListener{regClase()}
    }
    private fun registrarAl() {
        if (txtCorreo.text.toString().isEmpty()||txtCuenta.text.toString().isEmpty()||txtNombre.text.toString().isEmpty()){
            Toast.makeText(this,"Introduzca todos los datos",Toast.LENGTH_LONG).show()
        }else{
            var datoRegistroAlumnos = StringBuilder()
            datoRegistroAlumnos.append(txtNombre.text.toString().trim()).append("|")
            datoRegistroAlumnos.append(txtCuenta.text.toString().trim()).append("|")
            datoRegistroAlumnos.append(txtCorreo.text.toString().trim()).append("|")
            valores.put(contador,datoRegistroAlumnos.toString())
            contador++
            Toast.makeText(this,"Alumno Añadido Exitosamente", Toast.LENGTH_SHORT).show()
        }
    }
    private fun regClase() {
        val intent = Intent(this,RegClaseActivity::class.java)
        intent.putExtra("valores",valores)
        startActivity(intent)
    }
}