package ru.surf.vorobev.gallery.performance.viewmodel

sealed interface UiState {
    object Wait: UiState
    object Succes: UiState
    object Unathorized: UiState
    object InvalidLoginOrPass: UiState
    object Loading: UiState
    object DataError: UiState
    object NoSearchResult: UiState
}