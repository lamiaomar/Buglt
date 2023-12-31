package com.example.buglt.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.buglt.domin.repository.AppRepositoryImpl
import com.example.buglt.TicketsViewModel

@Suppress("UNCHECKED_CAST")
class TicketViewModelFactory constructor(
    private val appRepositoryImpl: AppRepositoryImpl,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return when {
            modelClass.isAssignableFrom(TicketsViewModel::class.java) ->
                return TicketsViewModel(appRepositoryImpl) as T

            else ->
                throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}