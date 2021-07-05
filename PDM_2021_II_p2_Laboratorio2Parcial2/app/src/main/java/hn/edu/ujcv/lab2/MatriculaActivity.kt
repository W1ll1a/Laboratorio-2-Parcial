package hn.edu.ujcv.lab2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_matricula.*
import kotlinx.android.synthetic.main.activity_reg_alumno.*
import kotlinx.android.synthetic.main.activity_reg_clase.*

var valores2: HashMap<Int,String> = hashMapOf()
var valores: HashMap<Int,String> = hashMapOf()
var matricula: HashMap<String,ArrayList<String>> = hashMapOf()

class MatriculaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matricula)
        valores = getIntent().getExtras()?.getSerializable("valores") as HashMap<Int, String>
        valores2 = getIntent().getSerializableExtra("valores2") as HashMap<Int, String>
        inicio()
        btnMatricular.setOnClickListener { matricular() }
        btnIngresarNotas.setOnClickListener { ingresarnotas() }
    }

    private fun ingresarnotas() {
        var intent = Intent(this,NotasActivity::class.java)
        intent.putExtra("valores",valores)
        intent.putExtra("valores2",valores2)
        intent.putExtra("valores3",matricula)
        startActivity(intent)
    }

    private fun matricular() {
        var nombreAlumnoSpinner: String = ""
        var nombreClasesSpinner: String = ""
        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                nombreAlumnoSpinner = valores.get(position).toString()
                Toast.makeText(this@MatriculaActivity,valores.get(position).toString(),Toast.LENGTH_LONG).show()

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        spinner2.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                nombreClasesSpinner= valores2.get(position).toString()
                Toast.makeText(this@MatriculaActivity,valores2.get(position).toString(),Toast.LENGTH_LONG).show()

            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        var listaDeClases = ArrayList<String>()
        listaDeClases.add(nombreClasesSpinner)
        matricula.put(nombreAlumnoSpinner,listaDeClases)
        /*for (n in 0..listaDeClases.size){
            if (nombreClasesSpinner==listaDeClases.get(n)){
                Toast.makeText(this@MatriculaActivity,"Esta Clase esta repetida",Toast.LENGTH_LONG).show()
            }else{
                listaDeClases.add(nombreClasesSpinner)
                matricula.put(nombreAlumnoSpinner,listaDeClases)
            }
        }*/
    }

    private fun inicio() {
        var listaA = arrayListOf<String>()
        var listaB = arrayListOf<String>()
        var nombreAlumno:String = ""
        var nombreClase:String = ""
        var contador:Int = 0
        var contador2:Int = 0
        for (valor in valores){
                var lista = valor.toString().split("|").toTypedArray()
            nombreAlumno     = lista[0]
            listaA.add(contador,nombreAlumno)
            contador++
        }
        var adapter = ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,listaA)
        spinner.adapter = adapter

        for (valor in valores2){
            var listaC = valor.toString().split("|").toTypedArray()
            nombreClase  = listaC[1]
            listaB.add(contador2,nombreClase)
            contador2++
        }
        var adapter2 = ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,listaB)
        spinner2.adapter = adapter2
    }

}