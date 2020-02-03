package com.stefanustj.dicoding.moviecatalogue.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShow(var poster:String, var name:String, var overview: String, var rating:String, var date:String) : Parcelable {
    constructor():this("", "", "", "", "")
}