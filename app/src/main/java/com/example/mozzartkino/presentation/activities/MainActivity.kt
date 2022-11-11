package com.example.mozzartkino.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.mozzartkino.R
import com.example.mozzartkino.databinding.ActivityMainBinding
import com.example.mozzartkino.presentation.adapters.KinoAdapter
import com.example.mozzartkino.presentation.view_models.KinoViewModel
import com.example.mozzartkino.presentation.view_models.KinoViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var factory: KinoViewModelFactory

    lateinit var viewModel: KinoViewModel

    @Inject
    lateinit var kinoAdapter: KinoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bnvDraws.setupWithNavController(navController)
        viewModel = ViewModelProvider(this, factory)[KinoViewModel::class.java]
    }
}