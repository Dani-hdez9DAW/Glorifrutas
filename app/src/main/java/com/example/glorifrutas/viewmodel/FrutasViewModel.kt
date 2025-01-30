package com.example.glorifrutas.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.glorifrutas.model.Fruta
import com.example.glorifrutas.repository.FrutasRepository

class FrutasViewModel : ViewModel() {
    private val frutasRepository = FrutasRepository()
    private val _frutas = MutableLiveData<List<Fruta>>()
    val frutas: LiveData<List<Fruta>> get() = _frutas

    init {
        loadFrutas()
    }

    private fun loadFrutas() {
        _frutas.value = frutasRepository.getFrutas()
    }
}