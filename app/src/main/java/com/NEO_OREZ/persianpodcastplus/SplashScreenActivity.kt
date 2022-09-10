package com.NEO_OREZ.persianpodcastplus

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.NEO_OREZ.persianpodcastplus.Apollo.CallRequest
import com.NEO_OREZ.persianpodcastplus.databinding.ActivitySplashScreenBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var bindingSplash: ActivitySplashScreenBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_splash_screen)
        /////binding
        bindingSplash = ActivitySplashScreenBinding.inflate(layoutInflater)
        val view = bindingSplash.root
        setContentView(view)
        ////////////
        bindingSplash.SplashLogoID.alpha=0f
        bindingSplash.SplashLogoID.animate().setDuration(3000).alpha(1f).withEndAction {
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.slide_out_right)
            finish()
        }

        lifecycleScope.launch(Dispatchers.IO) {
            CallToken()
        }
        bindingSplash.SplashTextID.alpha=0f
        bindingSplash.SplashTextID.animate().setDuration(3000).alpha(1f).withEndAction{
            val X = Intent(this, MainActivity::class.java)
            overridePendingTransition(android.R.anim.fade_in , android.R.anim.fade_out )
            startActivity(X)
            finish()
        }
    }

    suspend fun CallToken() {
        Log.d("logSplashActivity_00", "CallToken()")
        val token = CallRequest().apolloToken()
        Log.d("logSplashActivity_01", token)
        val sharedPreferences = this.getSharedPreferences("KeySave", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply{
            putString("key01", token)
        }.apply()
        Log.d("logSplashActivity_03", "saveData()")
    }
}