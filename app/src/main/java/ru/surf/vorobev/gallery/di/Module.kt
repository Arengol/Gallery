package ru.surf.vorobev.gallery.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.surf.vorobev.gallery.data.storage.LikedPostStorage
import ru.surf.vorobev.gallery.data.storage.UserInformationStorage
import ru.surf.vorobev.gallery.domain.MainRepository
import ru.surf.vorobev.gallery.network.NetworkRepository
import ru.surf.vorobev.gallery.network.NetworkRepositoryImpl
import ru.surf.vorobev.gallery.network.ServerApi
import ru.surf.vorobev.gallery.performance.viewmodel.MainViewModel
import ru.surf.vorobev.gallery.performance.viewmodel.MainViewModelImpl

@Module
@InstallIn(SingletonComponent::class)
class Module {
    @Provides
    fun provideUserInfoStorage (@ApplicationContext context: Context) = UserInformationStorage(context)

    @Provides
    fun provideLikedPostStorage (@ApplicationContext context: Context) = LikedPostStorage(context)

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

    @Provides
    fun provideNetworkRepository(serverApi: ServerApi): NetworkRepository = NetworkRepositoryImpl(serverApi)

    @Provides
    fun provideMainRepository(likedPostStorage:LikedPostStorage, userInformationStorage: UserInformationStorage, networkRepository: NetworkRepository): MainRepository = MainRepository(likedPostStorage, userInformationStorage, networkRepository)

    @Provides
    fun provideMainViewModel(mainRepository: MainRepository):MainViewModel = MainViewModelImpl(mainRepository)
}