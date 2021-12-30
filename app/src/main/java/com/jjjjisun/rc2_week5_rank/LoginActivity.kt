package com.jjjjisun.rc2_week5_rank

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.jjjjisun.rc2_week5_rank.databinding.ActivityLoginBinding
import com.nhn.android.naverlogin.OAuthLogin
import com.nhn.android.naverlogin.OAuthLoginHandler

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding

    lateinit var mOAuthLoginInstance: OAuthLogin
    lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val naver_client_id = "zKLgQO_3e75RYidHAyOd"
        val naver_client_secret = "NpGNGB8_sK"
        val naver_client_name = "Movie Search"

        mContext = this
        mOAuthLoginInstance = OAuthLogin.getInstance()
        mOAuthLoginInstance.init(mContext, naver_client_id, naver_client_secret, naver_client_name)

        //로그인 버튼 클릭
        binding.ibNaverLogin.setOAuthLoginHandler(mOAuthLoginHandler)
    }

    //네이버 로그인
    val mOAuthLoginHandler: OAuthLoginHandler = object : OAuthLoginHandler() {
        override fun run(success: Boolean) {
            if (success) {
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            } else {
                val errorCode: String = mOAuthLoginInstance.getLastErrorCode(mContext).code
                val errorDesc = mOAuthLoginInstance.getLastErrorDesc(mContext)

                Toast.makeText(
                    baseContext,
                    "errorCode:" + errorCode + ", errorDesc:" + errorDesc,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}