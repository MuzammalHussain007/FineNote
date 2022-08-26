package com.practice.finenote.activities
import android.app.AlertDialog
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import com.practice.finenote.R
import com.practice.finenote.databinding.ActivityMainBinding
import com.practice.finenote.utils.TokenManager
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var navCotroller: NavController;
    private lateinit var binding: ActivityMainBinding;
    private lateinit var appBarConfiguration: AppBarConfiguration;

    @Inject
    lateinit var tokenManager: TokenManager

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.language -> {
                showLanguageDialog()
            }

            R.id.theme -> {
                showThemeDialog()
            }
            R.id.logout -> {
//                tokenManager.destoryAll()
//                navCotroller.navigate(R.id.action_homeFragment_to_loginFragment)
            }
        }
        return item.onNavDestinationSelected(navCotroller) or super.onOptionsItemSelected(item)
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

    private fun showLanguageDialog() {
        val languageDialog = AlertDialog.Builder(this)
        languageDialog.setTitle(getString(R.string.chooseform))
        val languageName = arrayOf("English", "Urdu")
        languageDialog.setItems(
            languageName
        ) { _, which ->
            when (which) {
                0 -> {
                    setLanguage("en")
                    tokenManager.saveLanguage("en")
                }
                1 -> {
                    setLanguage("ur")
                    tokenManager.saveLanguage("ur")
                }

            }
        }
        languageDialog.show()
    }

    private fun showThemeDialog() {
        val themeDialog = AlertDialog.Builder(this)
        themeDialog.setTitle(getString(R.string.chooseform))
        val themeName = arrayOf("Light Mode", "Dark Mode", " System Default")
        themeDialog.setItems(
            themeName
        ) { dialog, which ->
            when (which) {
                0 -> {
                    setLightMode()
                }
                1 -> {
                    setNightMode()
                }
                2 -> {
                    setSystemDefaultMode()
                }

            }
        }
        themeDialog.show()
    }

    private fun setLightMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        tokenManager.saveTheme("light")
    }

    private fun setNightMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        tokenManager.saveTheme("dark")
    }

    private fun setSystemDefaultMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        tokenManager.saveTheme("system")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.host_fragment) as NavHostFragment
        navCotroller = navHostFragment.findNavController()
        setSupportActionBar(binding.toolBar)
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.loginFragment, R.id.homeFragment)
        )
        setupActionBarWithNavController(navCotroller, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navCotroller.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}