package com.example.lab4.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.lab4.data.model.Service
import com.example.lab4.data.model.ServiceCategory
import com.example.lab4.data.repository.ServiceRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ServiceViewModel(private val repository: ServiceRepository) : ViewModel() {
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedCategory = MutableStateFlow<ServiceCategory?>(null)
    val selectedCategory: StateFlow<ServiceCategory?> = _selectedCategory.asStateFlow()

    private val _sortOrder = MutableStateFlow<SortOrder>(SortOrder.NONE)
    val sortOrder: StateFlow<SortOrder> = _sortOrder.asStateFlow()

    val services: StateFlow<List<Service>> = combine(
        searchQuery,
        selectedCategory,
        sortOrder
    ) { query, category, sort ->
        var services = repository.getAllServices().first()

        if (query.isNotEmpty()) {
            services = services.filter { service ->
                service.name.contains(query, ignoreCase = true) ||
                        service.description.contains(query, ignoreCase = true)
            }
        }

        if (category != null) {
            services = services.filter { it.category == category }
        }

        when (sort) {
            SortOrder.PRICE_ASC -> services.sortedBy { it.pricePerHour }
            SortOrder.PRICE_DESC -> services.sortedByDescending { it.pricePerHour }
            SortOrder.RATING -> services.sortedByDescending { it.rating }
            SortOrder.NONE -> services
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun setCategory(category: ServiceCategory?) {
        _selectedCategory.value = category
    }

    fun setSortOrder(sortOrder: SortOrder) {
        _sortOrder.value = sortOrder
    }

    fun addService(service: Service) {
        viewModelScope.launch {
            repository.insertService(service)
        }
    }

    fun updateService(service: Service) {
        viewModelScope.launch {
            repository.updateService(service)
        }
    }

    fun deleteService(service: Service) {
        viewModelScope.launch {
            repository.deleteService(service)
        }
    }

    class Factory(private val repository: ServiceRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ServiceViewModel::class.java)) {
                return ServiceViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}

enum class SortOrder {
    PRICE_ASC,
    PRICE_DESC,
    RATING,
    NONE
} 