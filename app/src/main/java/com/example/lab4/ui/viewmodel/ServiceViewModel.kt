package com.example.lab4.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.lab4.data.AppDatabase
import com.example.lab4.data.model.Comment
import com.example.lab4.data.model.Service
import com.example.lab4.data.model.ServiceCategory
import com.example.lab4.data.repository.ServiceRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.Date

class ServiceViewModel(application: Application) : AndroidViewModel(application) {
    private val database = AppDatabase.getDatabase(application)
    private val serviceDao = database.serviceDao()
    private val commentDao = database.commentDao()

    private val _services = MutableStateFlow<List<Service>>(emptyList())
    val services: StateFlow<List<Service>> = _services.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedCategory = MutableStateFlow<ServiceCategory?>(null)
    val selectedCategory: StateFlow<ServiceCategory?> = _selectedCategory.asStateFlow()

    private val _sortOrder = MutableStateFlow<SortOrder>(SortOrder.NONE)
    val sortOrder: StateFlow<SortOrder> = _sortOrder.asStateFlow()

    init {
        loadServices()
    }

    private fun loadServices() {
        viewModelScope.launch {
            val allServices = serviceDao.getAllServices().first()
            _services.value = allServices
        }
    }

    val servicesFiltered: StateFlow<List<Service>> = combine(
        _services,
        _selectedCategory
    ) { services, category ->
        if (category != null) {
            services.filter { it.category == category }
        } else {
            services
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

    fun getFilteredServices(): List<Service> {
        return _selectedCategory.value?.let { category ->
            _services.value.filter { it.category == category }
        } ?: _services.value
    }

    fun getCommentsForService(serviceId: Long) = commentDao.getCommentsForService(serviceId)

    fun addComment(serviceId: Long, text: String, rating: Float) {
        viewModelScope.launch {
            val comment = Comment(
                serviceId = serviceId,
                userName = "Пользователь", // В реальном приложении здесь будет имя текущего пользователя
                text = text,
                rating = rating,
                date = Date()
            )
            commentDao.insertComment(comment)
        }
    }

    fun deleteComment(comment: Comment) {
        viewModelScope.launch {
            commentDao.deleteComment(comment)
        }
    }

    suspend fun getAverageRatingForService(serviceId: Long): Float? {
        return commentDao.getAverageRatingForService(serviceId)
    }

    fun addService(service: Service) {
        viewModelScope.launch {
            serviceDao.insertService(service)
        }
    }

    fun updateService(service: Service) {
        viewModelScope.launch {
            serviceDao.updateService(service)
        }
    }

    fun deleteService(service: Service) {
        viewModelScope.launch {
            serviceDao.deleteService(service)
        }
    }

    class Factory(private val application: Application) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ServiceViewModel::class.java)) {
                return ServiceViewModel(application) as T
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