package com.example.googlebooks.di

import com.example.googlebooks.data.local.LocalDataStore
import com.example.googlebooks.data.local.SharedPreferences
import com.example.googlebooks.data.remote.RemoteDataStore
import com.example.googlebooks.data.remote.RetrofitService
import com.example.googlebooks.domain.Repository
import com.example.googlebooks.domain.UseCase
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit

object DataModule {

    val dataModule = Kodein.Module("data module", false) {

        //Remote
        bind<RetrofitService>() with singleton {
            instance<Retrofit>().create(RetrofitService::class.java)
        }

        bind() from singleton { RemoteDataStore() }

        bind<Repository>() with singleton {
            Repository(
                remoteDataStore = instance(),
                localDataStore = instance()
            )
        }

        //Local
        bind() from singleton { SharedPreferences(prefs = instance()) }

        bind<LocalDataStore>() with singleton {
            LocalDataStore(
                sharedPreferences = instance()
            )
        }


        //UseCase
        bind<UseCase>() with singleton {
            UseCase(
                repository = instance()
            )
        }
    }
}