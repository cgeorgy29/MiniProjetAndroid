package com.example.appliprofil

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainViewModel : ViewModel(){
    val movies = MutableStateFlow<List<Movie>>(listOf())
    val series = MutableStateFlow<List<Serie>>(listOf())
    val personnes = MutableStateFlow<List<Personnes>>(listOf())


    val apikey = "e29cd9d25ce42fab8b09ed1aeb4e2ea0"
    val service = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(TmdbAPI::class.java)

    fun searchMovie(motcle: String){
        viewModelScope.launch {
            movies.value = service.getFilmParMotCle(motcle,apikey).results
        }
    }

    fun lastMovie(){
        viewModelScope.launch {
            movies.value = service.lastMovie(apikey).results
        }
    }

    fun searchSerie(motcle: String){
        viewModelScope.launch {
            series.value = service.getSerieParMotCle(motcle,apikey).results
        }
    }

    fun lastSerie(){
        viewModelScope.launch {
            series.value = service.lastSerie(apikey).results
        }
    }
    fun searchPersonne(motcle: String){
        viewModelScope.launch {
            personnes.value = service.getPersonneParMotCle(motcle,apikey).results
        }
    }

    fun lastPersonne(){
        viewModelScope.launch {
            personnes.value = service.lastPersonne(apikey).results
        }
    }

}