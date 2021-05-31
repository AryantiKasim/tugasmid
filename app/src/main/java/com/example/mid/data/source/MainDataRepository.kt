package com.example.mid.data.source

import com.example.mid.data.MainData
import com.example.mid.data.RepoData

class MainDataRepository(
    val remoteDataSource: MainDataSource,
    val localDataSource: MainDataSource
) : MainDataSource {
    override fun getMainData(callback: MainDataSource.GetMainDataCallback) {
        remoteDataSource.getMainData(object : MainDataSource.GetMainDataCallback {
            override fun onNotAvailable() {
                callback.onNotAvailable()
            }

            override fun onError(msg: String?) {
                callback.onError(msg)
            }

            override fun onDataLoaded(mainData: MainData?) {
                callback.onDataLoaded(mainData)
            }
        })
    }

    override fun getRepoData(callback: MainDataSource.GetRepoDataCallback) {
        remoteDataSource.getRepoData(object : MainDataSource.GetRepoDataCallback {
            override fun onNotAvailable() {
                callback.onNotAvailable()
            }

            override fun onError(msg: String?) {
                callback.onError(msg)
            }

            override fun onDataLoaded(repoData: MutableList<RepoData?>) {
                callback.onDataLoaded(repoData)
            }
        })
    }


    companion object {
        private var INSTANCE: MainDataRepository? = null

        @JvmStatic
        fun getInstance(
            mainDataRemoteSource: MainDataSource,
        ) =
            INSTANCE ?: synchronized(MainDataRepository::class.java) {
                INSTANCE ?: MainDataRepository(mainDataRemoteSource, mainDataRemoteSource)
                    .also { INSTANCE = it }

            }

        @JvmStatic
        fun destroyInstance() {
            INSTANCE = null
        }
    }

}