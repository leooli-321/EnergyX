package br.com.fiap.energyx

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Modelo de dados
data class Horario(val timeLabel: String, val time: String)

// Adaptador para RecyclerView
class HorarioAdapter(private val horarios: List<Horario>) : RecyclerView.Adapter<HorarioAdapter.HorarioViewHolder>() {

    // Cria a ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorarioViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_horario, parent, false)
        return HorarioViewHolder(view)
    }

    // Preenche os dados na ViewHolder
    override fun onBindViewHolder(holder: HorarioViewHolder, position: Int) {
        val horario = horarios[position]
        holder.timeLabel.text = horario.timeLabel
        holder.time.text = horario.time
    }

    // Retorna o número de itens
    override fun getItemCount(): Int {
        return horarios.size
    }

    // ViewHolder que irá armazenar as referências das views
    class HorarioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val timeLabel: TextView = itemView.findViewById(R.id.time_label_item)
        val time: TextView = itemView.findViewById(R.id.time_item)
    }
}
