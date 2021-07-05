package hn.edu.ujcv.lab2

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_matricula.*
import kotlinx.android.synthetic.main.activity_notas.*
import kotlinx.android.synthetic.main.activity_reg_alumno.*

class NotasActivity : AppCompatActivity() {
    var matricula: HashMap<String,ArrayList<String>> = hashMapOf()
    var valores: HashMap<Int,String> = hashMapOf()
    var valores2: HashMap<Int,String> = hashMapOf()
    var notasdatos: String = ""
    var notas: HashMap<String,ArrayList<String>> = hashMapOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        matricula = getIntent().getExtras()?.getSerializable("valores3") as HashMap<String,ArrayList<String>>
        valores2 = getIntent().getExtras()?.getSerializable("valores2") as HashMap<Int, String>
        valores = getIntent().getExtras()?.getSerializable("valores") as HashMap<Int, String>
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notas)
        btnEnviarMatriculaCorreo.setOnClickListener { matriculaCorreo() }
        btnEnviarNotasCorreo.setOnClickListener { notasCorreo() }
        btnGuardarNotas.setOnClickListener { prueba() }
        inicio()
    }

    private fun prueba() {
        var nota1: String = ""
        nota1 = txtNota.text.toString()
    }

    private fun inicio() {
        var clasesMatriculadas: String = ""
        var listaA = arrayListOf<String>()
        var listaB = arrayListOf<String>()
        var nombreAlumno:String = ""
        var contador:Int = 0
        var nombreLlave: String = ""
        for (valor in valores){
            var lista = valor.toString().split("|").toTypedArray()
            nombreAlumno     = lista[0]
            listaA.add(contador,nombreAlumno)
            contador++
        }
        var adapterAlumnos = ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,listaA)
        spinnerNotasAlumnos.adapter = adapterAlumnos

        spinnerNotasAlumnos.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                 nombreLlave =  spinnerNotasAlumnos.selectedItem.toString()
                Toast.makeText(this@NotasActivity,"$nombreLlave", Toast.LENGTH_LONG).show()

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        var contadora: Int = 0
        var adapter = ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,listaB)
        spinnerNotasClases.adapter = adapter

    }
    private fun notasCorreo() {
        var email = Intent(Intent.ACTION_SEND)
        email.setData(Uri.parse("mailto:"))
        email.setType("text/plain")
        email.putExtra(Intent.EXTRA_EMAIL,arrayOf("Ingrese su Correo aqui!"))
        email.putExtra(Intent.EXTRA_SUBJECT,"Notas de sus Clases")
        email.putExtra(Intent.EXTRA_TEXT,datosnotas())
        startActivity(Intent.createChooser(email,"Send Email"))
    }

    private fun datosnotas(): String? {
        var nombreClase: String = ""
        notasdatos=txtNota.text.toString()
        for (valor in valores2){
            var listaC = valor.toString().split("|").toTypedArray()
            nombreClase   = listaC[1]
        }
        return "Clase:"+nombreClase+"\n Nota:"+notasdatos
    }

    private fun matriculaCorreo() {
        var email = Intent(Intent.ACTION_SEND)
        email.setData(Uri.parse("mailto:"))
        email.setType("text/plain")
        email.putExtra(Intent.EXTRA_EMAIL,arrayOf(" Ingrese el Correo"))
        email.putExtra(Intent.EXTRA_SUBJECT,"Matricula de sus Clases")
        email.putExtra(Intent.EXTRA_TEXT,datosdematricula())
        startActivity(Intent.createChooser(email,"Send Email"))
    }

    private fun datosdematricula(): String? {
        var datos: String = ""
        var nombreAlumno: String = ""
        var cuentaAlumno: String = ""
        var correoAlumno: String = ""
        var nombreClase: String = ""
        var codigoClase: String = ""
        var seccionClase: String = ""
        var horaClase: String = ""
        var pisoClase: String = ""
        var aulaClase: String = ""
        var contador2: Int = 0
        var listaB = arrayListOf<String>()
        var contador: Int = 0
        for (n in 0..valores2.size){

        for (valor in valores){
            var lista = valor.toString().split("|").toTypedArray()
            nombreAlumno     = lista[0]
            cuentaAlumno = lista[1]
            correoAlumno = lista[2]
        }
        for (valor in valores2){
            var listaC = valor.toString().split("|").toTypedArray()
            codigoClase   = listaC[0]
            nombreClase   = listaC[1]
            seccionClase  = listaC[2]
            horaClase     = listaC[3]
            pisoClase     = listaC[4]
            aulaClase     = listaC[5]
            listaB.add(contador2,nombreClase)
            contador2++
        }
        datos = "Nombre Alumno :"+nombreAlumno+"\nNumero de Cuenta::"+cuentaAlumno+"\nCorreo:"+correoAlumno+"\n\nCodigo de Clase:"+codigoClase+"\nNombre de Clases:"+listaB[contador]+"\n Seccion de la Clase:"+seccionClase+
                "\n Hora de la Clase:"+horaClase+"\n Piso de la Clase:"+pisoClase+"Aula:"+aulaClase
            contador++
        }

    return datos
    }

}