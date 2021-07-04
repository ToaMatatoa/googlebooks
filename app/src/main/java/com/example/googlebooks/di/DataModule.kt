package com.example.googlebooks.di

import com.example.googlebooks.data.RemoteDataStore
import com.example.googlebooks.data.RetrofitService
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
                remoteDataStore = instance()
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