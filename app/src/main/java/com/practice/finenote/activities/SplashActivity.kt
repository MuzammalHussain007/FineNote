package com.practice.finenote.activities

import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.DisplayMetrics
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDelegate
import com.practice.finenote.R
import com.practice.finenote.utils.Constants.SPLASH_SCREEN_TIME
import com.practice.finenote.utils.TokenManager
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    @Inject
    lateinit var tokenManager: TokenManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setUpSplashScreen()


    }

    override fun onStart() {
        setUpLanguageAndTheme()
        super.onStart()

    }
    private fun setLanguage(languageName: String) {
        val myLocale = Locale(languageName)
        val res: Resources = resources
        val dm: DisplayMetrics = res.getDisplayMetrics()
        val conf: Configuration = res.getConfiguration()
        conf.locale = myLocale
        res.updateConfiguration(conf, dm)
        val refresh = Intent(this, MainActivity::class.java)
        finish()
        startActivity(refresh)
    }

    private fun setUpLanguageAndTheme() {
        when(tokenManager.getLanguage())
        {
            "en"->{
                setLanguage("en")
            }
            "ur"->{
                setLanguage("ur")
            }
        }

        when(tokenManager.getTheme())
        {
            "light"->{
                setLightMode()
            }
            "dark"->{
                setNightMode()
            }
            "system"->{
                setSystemDefaultMode()
            }
        }
    }
    private fun setLightMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    private fun setNightMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
    private fun setSystemDefaultMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
    }
    private fun setUpSplashScreen() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_SCREEN_TIME.toLong())
    }
}