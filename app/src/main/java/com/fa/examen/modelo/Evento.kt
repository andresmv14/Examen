package com.fa.examen.modelo

import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fa.examen.adaptador.AdaptadorEvento
import com.fa.examen.R
import com.fa.examen.modelo.Evento
import com.squareup.picasso.Picasso

class Evento (val AdaptadorEvento: ArrayList<listaEventos>):RecyclerView.Adapter<AdaptadorEvento.ViewHolder(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.vistaindividual,parent,false)
        return ViewHolder (view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Init){
        holder.tvEvento.text = AdaptadorEvento[position].evento
        holder.tvID.text = AdaptadorEvento[position].ID
        holder.tvlocation = AdaptadorEvento[position].location
        holder.tvDate = AdaptadorEvento[position].Date
        Picasso.get().load(AdaptadorEvento[position].foto).into(holder.ivPerfil)

    }

    override fun getItemCount(): Int {
        return AdaptadorEvento.size
    }

    class ViewHolder (vista : View): RecyclerView.ViewHolder(vista){
        val tvEvento: TextView
        val tvID: TextView
        val tvlocation: TextView
        val tvDate: TextView
        val ivPerfil: ImageView

        init{
            tvEvento = vista.findViewById(R.id.tvEvento)
            tvID = vista.findViewById(R.id.tv_IDs)
            tvlocation = vista.findViewById(R.id.tv_location)
            tvDate = vista.findViewById(R.id.tvDate)
            ivPerfil = vista.findViewById(R.id.ivPerfil)
        }


    }


}

