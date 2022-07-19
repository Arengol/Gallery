package ru.surf.vorobev.gallery.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.surf.vorobev.gallery.domain.LikedPostStorage
import ru.surf.vorobev.gallery.domain.UserInformationStorage

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {
    @Provides
    fun provideUserInfoStorage (@ApplicationContext context: Context) = UserInformationStorage(context)

    @Provides
    fun provideLikedPostStorage (@ApplicationContext context: Context) = LikedPostStorage(context)
}