package com.example.devhub.data

import android.util.Log
import kotlinx.coroutines.flow.flow

class GitHubWebClient(
    private val service: DevHubRepository = DevHubApi().devHubService
) {

    fun buscaPerfilUsuario(usuario: String) = flow {
        try {
            emit(service.buscaDev(usuario))
        }catch (e: java.lang.Exception){
            Log.e("GitHubWebClient", "findProfileBy: falha ao buscar usuário", e)
        }

    }
}