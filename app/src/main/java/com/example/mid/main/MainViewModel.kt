package com.example.mid.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.databinding.ObservableField
import android.widget.Toast
import com.example.mid.data.MainData
import com.example.mid.data.source.MainDataRepository
import com.example.mid.data.source.MainDataSource
import com.example.mid.util.SingleLiveEvent


class MainViewModel(application: Application, private val mainDataRepository: MainDataRepository) : AndroidViewModel(application) {

    val mainDataField: ObservableField<MainData> = ObservableField()
    internal val openRepo = SingleLiveEvent<MainData>()

    fun start(){
        getMainData()
    }

    fun openRepo(){
        openRepo.value = mainDataField.get()
    }

    private fun getMainData(){
        mainDataRepository.getMainData(object : MainDataSource.GetMainDataCallback{
            override fun onDataLoaded(mainData: MainData?) {
                mainDataField.set(mainData)
            }

            override fun onError(msg: String?) {
                Toast.makeText(getApplication(), "Error: $msg", Toast.LENGTH_LONG).show()
            }

            override fun onNotAvailable() {
                Toast.makeText(getApplication(), "Data not available", Toast.LENGTH_LONG).show()
            }
        })
    }

}