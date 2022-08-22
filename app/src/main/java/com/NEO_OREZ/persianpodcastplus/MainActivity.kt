package com.NEO_OREZ.persianpodcastplus


import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.NEO_OREZ.persianpodcastplus.Apollo.CallRequest
import com.NEO_OREZ.persianpodcastplus.adapters.ViewPagerAdapter
import com.NEO_OREZ.persianpodcastplus.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /////view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        ////////////////
        lifecycleScope.launch(Dispatchers.IO) {
         CallToken()
        }

        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        binding.viewPager2.adapter = adapter
        
        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position-> when(position){
                0->{tab.text = "Main"}
                1->{tab.text = "First"}
                2->{tab.text = "Second"}
            }
        }.attach()

    }
    suspend fun CallToken() {
        Log.d("logMainActivity_00", "CallToken()")
        val token = CallRequest().apolloToken()
        Log.d("logMainActivity_01", token)
        val sharedPreferences = this.getSharedPreferences("KeySave", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply{
            putString("key0", token)
        }.apply()
        Log.d("logMainActivity_02", "saveData()")
    }


//    override fun onSupportNavigateUp(): Boolean {
//        //onBackPressed()
//        return navController.navigateUp() || super.onSupportNavigateUp()
//    }
}