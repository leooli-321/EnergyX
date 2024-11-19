package br.com.fiap.energyx

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.energyx.api.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FluxoRefrigeracaoActivity : AppCompatActivity() {

    private lateinit var horarioAdapter: HorarioAdapter
    private val horarios = mutableListOf<Horario>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fluxo_refrigeracao)

        // Configura o botão de voltar
        val btnBack: ImageView = findViewById(R.id.back_button)
        btnBack.setOnClickListener {
            finish() // Fecha a atividade atual e retorna à anterior
        }

        // Inicializa o RecyclerView para os horários
        val horarioRecyclerView: RecyclerView = findViewById(R.id.horarioRecyclerView)
        horarioRecyclerView.layoutManager = LinearLayoutManager(this)

        horarioAdapter = HorarioAdapter(horarios)
        horarioRecyclerView.adapter = horarioAdapter

        // Carrega os horários da API de forma assíncrona
        getHorariosFromApi()

        // Dados de pressão (não alterados)
        val pressaoList = listOf(
            "7.8 x 10⁶ N/m²",
            "7.9 x 10⁶ N/m²",
            "8.0 x 10⁶ N/m²",
            "7.7 x 10⁶ N/m²"
        )

        // Inicializa o RecyclerView para a pressão
        val pressaoRecyclerView: RecyclerView = findViewById(R.id.tempRecyclerView)
        pressaoRecyclerView.layoutManager = LinearLayoutManager(this)

        // Configura o adapter para o RecyclerView de pressão
        val pressaoAdapter = PressaoAdapter(pressaoList)
        pressaoRecyclerView.adapter = pressaoAdapter
    }

    // Faz a requisição para a API de forma assíncrona utilizando corrotinas
    private fun getHorariosFromApi() {
        // Usa lifecycleScope para garantir que a corrotina seja executada no ciclo de vida da Activity
        lifecycleScope.launch {
            try {
                // Chama a API em um thread de background usando Dispatchers.IO
                val response = withContext(Dispatchers.IO) {
                    ApiClient.apiService.getHorarios() // Chamada suspend
                }

                // Verifica se a resposta foi bem-sucedida
                if (response.isNotEmpty()) {
                    horarios.clear()  // Limpa a lista existente
                    horarios.addAll(response)  // Adiciona os novos horários
                    horarioAdapter.notifyDataSetChanged()  // Notifica o adapter
                }
            } catch (e: Exception) {
                e.printStackTrace()  // Caso haja falha na requisição
            }
        }
    }
}
