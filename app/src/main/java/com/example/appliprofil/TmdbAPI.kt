package com.example.appliprofil

import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbAPI {
    @GET("/3/search/movie")
    suspend fun getFilmParMotCle(@Query("query") motcle: String,@Query("api_key") apikey: String): TmdbResult

    @GET("/3/trending/movie/week")
    suspend fun lastMovie(@Query("api_key") apikey: String): TmdbResult

}