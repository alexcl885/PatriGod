package com.example.patrigod.data.user.network

import com.example.patrigod.data.user.network.repository.ApiUsuarioServiceInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * INSTANCIA DE RETROFIT PARA EL USUARIO
 */
@Module
@InstallIn(SingletonComponent::class)
object InstanciaRetrofit {

    private const val URL_BASE = "http://10.0.2.2:8085"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit
        .Builder()
        .baseUrl(URL_BASE)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideApiUsuarioServiceInterface(retrofit: Retrofit): ApiUsuarioServiceInterface {
        return retrofit.create(ApiUsuarioServiceInterface::class.java)
    }


}
