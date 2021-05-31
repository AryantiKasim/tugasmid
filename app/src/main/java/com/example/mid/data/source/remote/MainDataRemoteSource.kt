package com.example.mid.data.source.remote

import android.util.Log
import com.example.mid.api.ApiService
import com.example.mid.api.dao.RepoDataDao
import com.example.mid.data.MainData
import com.example.mid.data.RepoData
import com.example.mid.data.source.MainDataSource
import com.example.mid.util.Constant
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object MainDataRemoteSource : MainDataSource {

    private val apiService = ApiService.create()

    override fun getMainData(callback: MainDataSource.GetMainDataCallback) {
        apiService.getMainData(Constant.username)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                run {

                    if (it.name != "") {
                        val mainData = MainData(it.name,
                            it.location,
                            it.avatar_url,
                            "${it.followers}\nFollowers",
                            "${it.following}\nFollowings",
                            "${it.public_repos}\nRepos"
                        )
                        callback.onDataLoaded(mainData)
                    } else {
                        callback.onNotAvailable()
                    }

                }
            }, {
                callback.onError(it.message)
            })
    }

    override fun getRepoData(callback: MainDataSource.GetRepoDataCallback) {
        apiService.getReposData(Constant.username)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                run {

                    if (it.isNotEmpty()) {
                        Log.i("xx", " ${it.size}")

                        val listRepo: MutableList<RepoData?> = mutableListOf<RepoData?>()
                        for (item: RepoDataDao in it) {
                            Log.i("xx", " -- ${item.description}")
                            val repoData = RepoData(
                                item.name,
                                item.language,
                                item.description,
                                item.html_url
                            )
                            listRepo.add(repoData)


                        }

                        callback.onDataLoaded(listRepo)
                    } else {
                        callback.onNotAvailable()
                    }

                }
            }, {
                callback.onError(it.message)
            })
    }

}