package com.metromart.repositoriesapp.domain

import com.google.gson.annotations.SerializedName

data class Repository(
    @SerializedName("id") var id: Int,
    @SerializedName("name") var name: String,
    @SerializedName("description") var description: String
)