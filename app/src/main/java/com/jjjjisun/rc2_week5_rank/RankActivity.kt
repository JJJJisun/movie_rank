package com.jjjjisun.rc2_week5_rank

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jjjjisun.rc2_week5_rank.databinding.ActivityRankBinding
import com.jjjjisun.rc2_week5_rank.movie.MovieDto

class RankActivity : AppCompatActivity() {

    lateinit var binding: ActivityRankBinding
    lateinit var list: List<MovieDto>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRankBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //데이더 받기
        val bundle = intent.extras
        list = bundle?.getSerializable("movieList") as List<MovieDto>

        binding.recyclerResult.adapter = ResultRecyclerViewAdpater()
        binding.recyclerResult.layoutManager = LinearLayoutManager(applicationContext)
        binding.recyclerResult.setHasFixedSize(true)
    }

    //어댑터
    inner class ResultRecyclerViewAdpater :
        RecyclerView.Adapter<ResultRecyclerViewAdpater.ViewHolder>() {

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.rank_item, parent, false)
            return ViewHolder(view)
        }

        @SuppressLint("ResourceAsColor")
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.apply {
                rankTextView.text = list[position].rank
                val rankInten = list[position].rankInten
                rankIntenTextView.text = rankInten
                movieNameTextView.text = list[position].movieNm

                if (rankInten?.toInt()!! < 0) {
                    rankIntenImageView.setImageResource(R.drawable.old_img)
                    rankIntenTextView.setTextColor(
                        ContextCompat.getColor(
                            applicationContext!!,
                            R.color.red
                        )
                    )
                }
            }
        }

        override fun getItemCount(): Int {
            return list.size
        }

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val rankTextView: TextView = view.findViewById(R.id.rank)
            val movieNameTextView: TextView = view.findViewById(R.id.movie_name)
            val rankIntenTextView: TextView = view.findViewById(R.id.tv_rankInten)
            val rankIntenImageView: ImageView = view.findViewById(R.id.iv_rankInten)
        }
    }
}