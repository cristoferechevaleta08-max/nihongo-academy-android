package com.nihongoacademy.viewmodel

import androidx.lifecycle.ViewModel
import com.nihongoacademy.data.asset.LanguageCatalogRepository
import com.nihongoacademy.domain.model.LanguageEntry
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LanguageViewModel(
    private val repository: LanguageCatalogRepository
) : ViewModel() {
    private val _languages = MutableStateFlow<List<LanguageEntry>>(emptyList())
    val languages: StateFlow<List<LanguageEntry>> = _languages.asStateFlow()

    fun loadLanguages() {
        _languages.value = repository.loadLanguages()
    }
}
