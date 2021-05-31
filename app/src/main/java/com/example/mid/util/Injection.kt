package com.example.mid.util

import android.content.Context
import android.preference.PreferenceManager
import com.example.mid.data.source.MainDataRepository
import com.example.mid.data.source.local.MainDataLocalSource
import com.example.mid.data.source.remote.MainDataRemoteSource

object Injection {
    fun providedMainDataRepository(context: Context) = MainDataRepository.getInstance(MainDataRemoteSource, MainDataLocalSource.getInstance(PreferenceManager.getDefaultSharedPreferences(context)))
}