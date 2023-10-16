package br.com.igorbag.githubsearch.data

import br.com.igorbag.githubsearch.domain.Repository
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {
    // Define o endpoint da API para buscar repositórios de um usuário específico
    @GET("users/{username}/repos")
    fun getRepositoriesByUsername(@Path("username") username: String): Call<List<Repository>>
}
