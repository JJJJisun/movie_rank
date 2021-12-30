package com.jjjjisun.rc2_week5_rank

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.jjjjisun.rc2_week5_rank.databinding.ActivityMainBinding
import com.jjjjisun.rc2_week5_rank.movie.MovieDto
import com.jjjjisun.rc2_week5_rank.movie.MovieResponse
import com.jjjjisun.rc2_week5_rank.movie.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    companion object {
        const val KEY = "54b408a67510f640f635378b77536444"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //1. 어제의 순위 버튼 클릭
        binding.ibYesterday.setOnClickListener {
            val cal: Calendar = Calendar.getInstance()
            cal.add(Calendar.DAY_OF_MONTH, -1)
            val dateFormat = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
            val targetDt = dateFormat.format(cal.time)
            getResult(targetDt)
        }

        //2. 2주 전 순위 버튼 클릭
        binding.ibTwoWeek.setOnClickListener {
            val cal: Calendar = Calendar.getInstance()
            cal.add(Calendar.DAY_OF_MONTH, -14)
            val dateFormat = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
            val targetDt = dateFormat.format(cal.time)
            getResult(targetDt)
        }

        //3. 한달 전 순위 버튼 클릭
        binding.ibMonth.setOnClickListener {
            val cal: Calendar = Calendar.getInstance()
            cal.add(Calendar.DAY_OF_MONTH, -30)
            val dateFormat = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
            val targetDt = dateFormat.format(cal.time)
            getResult(targetDt)
        }
    }

    private fun getResult(targetDt: String) {
        RetrofitBuilder.api.getMovieList(targetDt, KEY).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                val movieResponse = response.body()
                val list : List<MovieDto> =  movieResponse!!.boxofficeResult!!.dailyBoxOfficeList

                //데이터 전달하기
                val intent = Intent(this@MainActivity, RankActivity::class.java)
                val bundle = Bundle() // 보따리 느낌
                bundle.putSerializable("movieList", (list as Serializable)) // 리스트를 강제 형 변환
                intent.putExtras(bundle)
                startActivity(intent)
            }

            //통신 실패 시
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}










