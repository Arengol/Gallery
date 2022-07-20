package ru.surf.vorobev.gallery.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.surf.vorobev.gallery.BuildConfig
import ru.surf.vorobev.gallery.network.ServerApi

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun provideHttpClient (): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        ).build()
    @Provides
    fun provideRetrofit (okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create().asLenient())
        .baseUrl("https://pictures.chronicker.fun/api/")
        .client(okHttpClient)
        .build()

    @Provides
    fun provideServerAPI(retrofit: Retrofit): ServerApi = retrofit.create(ServerApi::class.java)
}