package com.practice.finenote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.practice.finenote.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var navCotroller: NavController;
    private lateinit var binding: ActivityMainBinding;
    private lateinit var appBarConfiguration: AppBarConfiguration;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.host_fragment) as NavHostFragment
        navCotroller = navHostFragment.findNavController()
        setSupportActionBar(binding.toolBar)
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.loginFragment)
        )
        setupActionBarWithNavController(navCotroller, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navCotroller.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}