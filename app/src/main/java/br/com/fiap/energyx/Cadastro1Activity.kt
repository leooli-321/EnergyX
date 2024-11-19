package br.com.fiap.energyx

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.radiobutton.MaterialRadioButton
import com.google.android.material.textfield.TextInputEditText
import android.widget.ImageView
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException
import java.util.concurrent.TimeUnit

class Cadastro1Activity : AppCompatActivity() {

    private lateinit var nomeInput: TextInputEditText
    private lateinit var cargoInput: TextInputEditText
    private lateinit var turnoManha: MaterialRadioButton
    private lateinit var turnoTarde: MaterialRadioButton
    private lateinit var turnoNoite: MaterialRadioButton
    private lateinit var nextButton: ImageView // Alterado para ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro1)

        // Inicializa os campos de entrada
        nomeInput = findViewById(R.id.input_nome)
        cargoInput = findViewById(R.id.input_cargo)
        turnoManha = findViewById(R.id.turno_manha)
        turnoTarde = findViewById(R.id.turno_tarde)
        turnoNoite = findViewById(R.id.turno_noite)
        nextButton = findViewById(R.id.next_button)

        // Ação do botão "Próximo"
        nextButton.setOnClickListener {
            // Validar campos e enviar os dados
            val nome = nomeInput.text.toString().trim()
            val cargo = cargoInput.text.toString().trim()

            // Validação do nome e cargo
            if (nome.isEmpty() || cargo.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Validar se um turno foi selecionado
            val turnoId = when {
                turnoManha.isChecked -> 1
                turnoTarde.isChecked -> 2
                turnoNoite.isChecked -> 3
                else -> {
                    Toast.makeText(this, "Por favor, selecione um turno.", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }

            // Preparar os dados para enviar à API
            val jsonBody = """
                {
                    "nome": "$nome",
                    "cargo": "$cargo",
                    "turno_id": $turnoId
                }
            """.trimIndent()

            // Enviar os dados para a API (API de intermediário)
            sendDataToApi(jsonBody)
        }
    }

    private fun sendDataToApi(jsonBody: String) {
        val url = "http://172.16.71.42:8080/api/operadores/cadastro"

        val client = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

        val requestBody = jsonBody
            .toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())

        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .addHeader("Content-Type", "application/json")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body?.string()
                runOnUiThread {
                    when {
                        response.isSuccessful -> {
                            // Parse da resposta, se necessário
                            Toast.makeText(this@Cadastro1Activity, "Cadastro realizado!", Toast.LENGTH_SHORT).show()

                            // Aqui você pode passar dados para a próxima Activity
                            val intent = Intent(this@Cadastro1Activity, Cadastro2Activity::class.java)
                            // Passar dados necessários
                            startActivity(intent)
                        }
                        response.code == 400 -> {
                            Toast.makeText(this@Cadastro1Activity, "Erro de validação", Toast.LENGTH_SHORT).show()
                        }
                        else -> {
                            Toast.makeText(this@Cadastro1Activity, "Erro ao cadastrar", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    Toast.makeText(this@Cadastro1Activity, "Falha na conexão", Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
            }
        })
    }
}
