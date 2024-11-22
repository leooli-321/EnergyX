package br.com.fiap.energyx.api

// Classe ApiResponse genérica que pode ser reutilizada para diferentes tipos de resposta
data class ApiResponse<T>(
    val success: Boolean,      // Indica se a resposta foi bem-sucedida
    val data: T?,              // Contém os dados da resposta (genérico)
    val message: String? = null // Mensagem de erro ou sucesso (caso necessário)
)