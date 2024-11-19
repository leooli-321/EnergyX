package br.com.fiap.energyx.api

import br.com.fiap.energyx.Horario
import retrofit2.http.GET

interface ApiService {
    @GET("get-horarios")
    suspend fun getHorarios(): List<Horario>
}
