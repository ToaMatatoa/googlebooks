package com.example.googlebooks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.googlebooks.databinding.ActivityMainBinding
import com.example.googlebooks.ui.ViewModel
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.KodeinTrigger
import org.kodein.di.android.closestKodein
import org.kodein.di.android.retainedKodein
import org.kodein.di.generic.instance


class MainActivity : AppCompatActivity(), KodeinAware {

    private lateinit var navController: NavController

    private val parentKodein: Kodein by closestKodein()
    override val kodein: Kodein by retainedKodein {
        extend(parentKodein)
    }
    override val kodeinTrigger = KodeinTrigger()

    private lateinit var binding: ActivityMainBinding

    private val viewModel: ViewModel by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        kodeinTrigger.trigger()

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()

        viewModel.saveBooksSetting(Constants.CHECK_ALL)
        viewModel.saveEnteredText("")
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}