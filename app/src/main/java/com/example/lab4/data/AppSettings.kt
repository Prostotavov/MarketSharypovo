package com.example.lab4.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "settings")

class AppSettings(private val context: Context) {
    companion object {
        private val SHOW_INSTRUCTION = booleanPreferencesKey("show_instruction")
    }

    val showInstruction: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[SHOW_INSTRUCTION] ?: true
        }

    suspend fun setShowInstruction(show: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[SHOW_INSTRUCTION] = show
        }
    }
} 