package com.example.mid.data

import android.os.Parcel
import android.os.Parcelable

class MainData (
    var name: String? = "-",
    var location: String? = "-",
    var avatarUrl: String? ="-",
    var followers: String? = "0",
    var following: String? = "0",
    var publicRepos: String? = "0"
)