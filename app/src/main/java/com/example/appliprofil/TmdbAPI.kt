package com.example.appliprofil

import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbAPI {
    @GET("/3/search/movie")
    suspend fun getFilmParMotCle(@Query("query") motcle: String,@Query("api_key") apikey: String): TmdbResult

    @GET("/3/trending/movie/week")
    suspend fun lastMovie(@Query("api_key") apikey: String): TmdbResult

    @GET("/3/search/tv")
    suspend fun getSerieParMotCle(@Query("query") motcle: String,@Query("api_key") apikey: String): TmdbResultSeries

    @GET("/3/trending/tv/week")
    suspend fun lastSerie(@Query("api_key") apikey: String): TmdbResultSeries

    @GET("/3/search/person")
    suspend fun getPersonneParMotCle(@Query("query") motcle: String,@Query("api_key") apikey: String): TmdbResultPersonne

    @GET("/3/trending/person/week")
    suspend fun lastPersonne(@Query("api_key") apikey: String): TmdbResultPersonne

}
