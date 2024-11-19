package br.com.fiap.energyx

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class Temperatura(val temperatura: String, val s: String) {
    val label: CharSequence?
        get() {
            TODO()
        }
}

class TemperaturaAdapter(private val temperaturas: List<Temperatura>) : RecyclerView.Adapter<TemperaturaAdapter.TemperaturaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TemperaturaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_temperature, parent, false)
        return TemperaturaViewHolder(view)
    }

    override fun onBindViewHolder(holder: TemperaturaViewHolder, position: Int) {
        val temperatura = temperaturas[position]
        holder.bind(temperatura)
    }

    override fun getItemCount(): Int = temperaturas.size

    inner class TemperaturaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val temperaturaTextView: TextView = itemView.findViewById(R.id.temperaturaTextView)

        fun bind(temperatura: Temperatura) {
            temperaturaTextView.text = temperatura.temperatura
        }
    }
}
