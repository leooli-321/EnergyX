package br.com.fiap.energyx

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PressaoAdapter(private val pressaoList: List<String>) : RecyclerView.Adapter<PressaoAdapter.PressaoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PressaoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pressao, parent, false)
        return PressaoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PressaoViewHolder, position: Int) {
        val pressao = pressaoList[position]
        holder.tvPressureValue.text = pressao
    }

    override fun getItemCount(): Int {
        return pressaoList.size
    }

    class PressaoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvPressureLabel: TextView = itemView.findViewById(R.id.tv_pressure_label)
        val tvPressureValue: TextView = itemView.findViewById(R.id.tv_pressure_value)
    }
}
