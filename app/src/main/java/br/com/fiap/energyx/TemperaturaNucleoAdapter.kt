import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.energyx.R

// Classe de dados para TemperaturaNucleo
data class TemperaturaNucleo(
    val label: String,
    val value: String
)

// Adapter para a lista de temperaturas
class TemperaturaNucleoAdapter(private val temperaturaList: List<TemperaturaNucleo>) : RecyclerView.Adapter<TemperaturaNucleoAdapter.TemperaturaViewHolder>() {

    // ViewHolder que liga o layout do item com as variáveis
    class TemperaturaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val thermometerIcon: ImageView = itemView.findViewById(R.id.thermometer_icon)
        val tempLabel: TextView = itemView.findViewById(R.id.temp_label)
        val tempValue: TextView = itemView.findViewById(R.id.temp_value)
    }

    // Criação do ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TemperaturaViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_temperatura_nucleo, parent, false)
        return TemperaturaViewHolder(itemView)
    }

    // Populando o ViewHolder com dados
    override fun onBindViewHolder(holder: TemperaturaViewHolder, position: Int) {
        val currentItem = temperaturaList[position]
        holder.tempLabel.text = currentItem.label
        holder.tempValue.text = currentItem.value
    }

    // Número de itens
    override fun getItemCount(): Int {
        return temperaturaList.size
    }
}
