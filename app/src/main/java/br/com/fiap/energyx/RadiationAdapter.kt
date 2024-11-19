package br.com.fiap.energyx

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView

class RadiationAdapter(private val radiationLevels: List<String>) : RecyclerView.Adapter<RadiationAdapter.RadiationViewHolder>() {

    // ViewHolder que representa cada item na lista
    class RadiationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val radiationLevelTextView: TextView = itemView.findViewById(R.id.radiation_level)
    }

    // Cria a nova instância do ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RadiationViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_radiacao, parent, false)
        return RadiationViewHolder(itemView)
    }

    // Vincula os dados ao ViewHolder
    override fun onBindViewHolder(holder: RadiationViewHolder, position: Int) {
        val radiationLevel = radiationLevels[position]
        holder.radiationLevelTextView.text = radiationLevel
    }

    // Retorna o número de itens na lista
    override fun getItemCount(): Int {
        return radiationLevels.size
    }
}
