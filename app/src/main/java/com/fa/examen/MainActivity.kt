package com.fa.examen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.fa.examen.adaptador.AdapdadorEvento
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

import com.fa.examen.modelo.Evento


class MainActivity : AppCompatActivity() {
    lateinit var miRecycler: RecyclerView
    lateinit var adaptador: AdapdadorEvento
    lateinit var listaEventos:ArrayList<Evento>
    lateinit var creditosBtn:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listaEventos = ArrayList<Evento>()

        miRecycler = findViewById(R.id.recyclerEventos)

        adaptador = AdapdadorEvento(listaEventos)

        miRecycler.adapter = adaptador

        getPersonajes()
        miRecycler.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)



        creditosBtn = findViewById(R.id.btnCreditos)
        creditosBtn.setOnClickListener{
            val viewCreditos = Intent(this, creditosA::class.java)
            startActivity(viewCreditos)

        }

    }

    fun getPersonajes(){
        val queue = Volley.newRequestQueue(this)

        val url = "https://ll.thespacedevs.com/2.2.0/event/"

        val objectRequest = JsonObjectRequest(Request.Method.GET,url,null, { respuesta ->
            val eventosJson = respuesta.getJSONArray("results")
            for (indice in 0..eventosJson.length() - 1) {
                val eventoInJson = eventosJson.getJSONObject(indice)
                val evento = Evento(eventoInJson.getString("name"), eventoInJson.getString("id"), eventoInJson.getString("location"),
                    eventoInJson.getString("date"), eventoInJson.getString("feature_image"))
                listaEventos.add(evento)
            }
            adaptador.notifyDataSetChanged()
        },
            {
                Log.e("EventosApi","Error")
        })
    queue.add(objectRequest)
    }
}