package hn.edu.ujcv.lab2
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_reg_clase.*

class RegClaseActivity : AppCompatActivity() {
    var valores2: HashMap<Int,String> = hashMapOf()
    var valores: HashMap<Int,String> = hashMapOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg_clase)
        btnRegCl.setOnClickListener{registrar()}
        btnMatricula.setOnClickListener{Matricula()}
    }
    var n : Int = 0
    private fun registrar() {
        if(txtCodigoClase.text.toString().isEmpty()||txtAula.text.toString().isEmpty()||
            txtHora.text.toString().isEmpty()||txtNombreClase.text.toString().isEmpty()||
            txtPiso.text.toString().isEmpty()||txtSeccion.text.toString().isEmpty()){
            Toast.makeText(this,"Introduzca todos los datos", Toast.LENGTH_LONG).show()
        }else{
            var datoRegistroClases = StringBuilder()
            datoRegistroClases.append(txtCodigoClase.text.toString()).append("|")
            datoRegistroClases.append(txtNombreClase.text.toString()).append("|")
            datoRegistroClases.append(txtSeccion.text.toString().toInt()).append("|")
            datoRegistroClases.append(txtHora.text.toString()).append("|")
            datoRegistroClases.append(txtPiso.text.toString().toInt()).append("|")
            datoRegistroClases.append(txtAula.text.toString().toInt()).append("|")
            valores2.put(n,datoRegistroClases.toString())
            n++
            Toast.makeText(this,"Clase Añadida Extosamente", Toast.LENGTH_SHORT).show()
        }
    }
    private fun Matricula() {
        val intent = Intent(this,MatriculaActivity::class.java)
        valores = getIntent().getSerializableExtra("valores") as HashMap<Int, String>
        intent.putExtra("valores",valores)
        intent.putExtra("valores2",valores2)
        startActivity(intent)
    }

}