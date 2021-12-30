package com.jjjjisun.rc2_week5_rank.movie

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("boxOfficeResult")
    var boxofficeResult: BoxOfficeResult?
)