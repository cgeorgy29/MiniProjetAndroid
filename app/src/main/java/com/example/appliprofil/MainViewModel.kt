package com.example.appliprofil

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainViewModel : ViewModel(){

    val movies = MutableStateFlow<List<Movie>>(listOf())
    val onemovie = MutableStateFlow<DetailFilm?>(null)

    val series = MutableStateFlow<List<Serie>>(listOf())
    val oneserie = MutableStateFlow<DetailSerie?>(null)

    val personnes = MutableStateFlow<List<Personnes>>(listOf())

    private val apikey = "e29cd9d25ce42fab8b09ed1aeb4e2ea0"
    private val service: TmdbAPI = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(TmdbAPI::class.java)

    fun searchMovie(motcle: String){
        viewModelScope.launch {
            movies.value = service.getFilmParMotCle(motcle,apikey,"fr").results
        }
    }
    fun lastMovie(){
        viewModelScope.launch {
            movies.value = service.lastMovie(apikey, "fr").results
        }
    }
    fun oneMovie(id:String){
        viewModelScope.launch {
            onemovie.value = service.oneMovie(id,apikey,"credits","fr")
        }

    }



    fun searchSerie(motcle: String){
        viewModelScope.launch {
            series.value = service.getSerieParMotCle(motcle,apikey,"fr").results
        }
    }
    fun lastSerie(){
        viewModelScope.launch {
            series.value = service.lastSerie(apikey,"fr").results
        }
    }
    fun oneSerie(id: String){
        viewModelScope.launch {
            oneserie.value = service.oneSerie(id, apikey, "credits", "fr")
        }
    }



    fun searchPersonne(motcle: String){
        viewModelScope.launch {
            personnes.value = service.getPersonneParMotCle(motcle,apikey,"fr").results
        }
    }
    fun lastPersonne(){
        viewModelScope.launch {
            personnes.value = service.lastPersonne(apikey,"fr").results
        }
    }

}