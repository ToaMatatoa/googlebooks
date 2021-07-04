package com.example.googlebooks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.KodeinTrigger
import org.kodein.di.android.closestKodein
import org.kodein.di.android.retainedKodein

class MainActivity : AppCompatActivity(), KodeinAware {

    private val parentKodein: Kodein by closestKodein()
    override val kodein: Kodein by retainedKodein {
        extend(parentKodein)
    }
    override val kodeinTrigger = KodeinTrigger()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        kodeinTrigger.trigger()
    }
}