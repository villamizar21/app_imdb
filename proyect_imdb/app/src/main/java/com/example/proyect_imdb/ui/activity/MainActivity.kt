package com.example.proyect_imdb.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.proyect_imdb.R
import com.example.proyect_imdb.databinding.ActivityMainBinding
import com.example.proyect_imdb.ui.fragmens.MoviesFavoritesFragment
import com.example.proyect_imdb.ui.fragmens.MoviesFragment
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var toogle: ActionBarDrawerToggle
    private lateinit var appBarConfiguration: AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toogle = ActionBarDrawerToggle(
            this,
            binding.draweLayout,
            R.string.open_drawer,
            R.string.close_drawer
        )
        binding.draweLayout.addDrawerListener(toogle)
        toogle.syncState()

        val drawerLayout: DrawerLayout = binding.draweLayout
        val navView: NavigationView = binding.navView

        supportActionBar?.setDisplayHomeAsUpEnabled(true)



        binding.navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_favorite -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.container_fragment, MoviesFavoritesFragment())
                        commit()
                    }
                }
                R.id.nav_home -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.container_fragment, MoviesFragment())
                        commit()
                    }
                }
            }
            binding.draweLayout.closeDrawer(GravityCompat.START)
            true
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (toogle.onOptionsItemSelected(item))
            return true

        return super.onOptionsItemSelected(item)
    }
}