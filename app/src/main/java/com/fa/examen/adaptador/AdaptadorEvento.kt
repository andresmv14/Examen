
package com.fa.examen.adaptador
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fa.examen.R
import com.fa.examen.modelo.Evento
import com.squareup.picasso.Picasso

class AdapdadorEvento (val listaEventos: ArrayList<Evento>):RecyclerView.Adapter<AdapdadorEvento.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.vistaindividual,parent,false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        holder.tvEvento.text = listaEventos[position].nombre
        holder.tvID.text = listaEventos[position].id
        holder.tvlocation.text = listaEventos[position].location
        holder.tvDate.text = listaEventos[position].Date
        Picasso.get().load(listaEventos[position].foto).into(holder.ivPerfil)

    }

    override fun getItemCount(): Int {
        return listaEventos.size
    }

    class ViewHolder (vista : View): RecyclerView.ViewHolder(vista){
        val tvEvento: TextView
        val tvID: TextView
        val tvlocation: TextView
        val tvDate: TextView
        val ivPerfil: ImageView

        init{
            tvEvento = vista.findViewById(R.id.tvEvento)
            tvID = vista.findViewById(R.id.tv_ID)
            tvlocation = vista.findViewById(R.id.tv_location)
            tvDate = vista.findViewById(R.id.tvDate)
            ivPerfil = vista.findViewById(R.id.ivPerfil)
        }


    }


}