package com.fa.examen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.fa.prueba.adaptador.AdaptadorEvento
import com.fa.prueba.modelo.Evento
class MainActivity : AppCompatActivity() {
    lateinit var miRecycler: RecyclerView
    lateinit var adaptador:AdaptadorEvento
    lateinit var listaEventos:ArrayList<Evento>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listaEventos = ArrayList<Evento>()

        miRecycler = findViewById(R.id.recyclerEventos)

        adaptador = AdaptadorEvento(listaEventos)

        miRecycler.adapter = adaptador

        getPersonajes()
        miRecycler.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

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
            adaptador.notifyDataChanged()
        },{
                Log.e("PersonajesApi","Error")
            }
        })
    queue.add(objectRequest)
    }
}