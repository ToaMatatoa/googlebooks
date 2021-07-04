package com.example.googlebooks.di

import com.example.googlebooks.ui.ViewModel
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

object UIModule {

    val uiModule = Kodein.Module("ui module", false) {

        bind() from provider { ViewModel(instance()) }
    }
}