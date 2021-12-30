package com.jjjjisun.rc2_week5_rank.movie

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitMovieAPI {
    @GET("/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json")
    fun getMovieList(
        @Query("targetDt") targetDt: String?,
        @Query("key") key: String?
    ): Call<MovieResponse>
}
// annotation : Get/Post/Delete/Put 중 하려는 작업을 선택하여 작업을 수행할 주소를 () 안에 적는다.
// Call<데이터 객체 타입> @Query("요청 매개변수") 변수
// 여기선  MovieResponse 타입으로 받아옴