package com.jjjjisun.rc2_week5_rank.movie

import com.google.gson.annotations.SerializedName

data class BoxOfficeResult(
    @SerializedName("dailyBoxOfficeList")
    var dailyBoxOfficeList: List<MovieDto> = arrayListOf()
    //받아온 결과를 MovieDto list 형태로 만든다.
)