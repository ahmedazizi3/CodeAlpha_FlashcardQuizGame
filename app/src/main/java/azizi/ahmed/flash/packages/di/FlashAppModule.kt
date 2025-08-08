package azizi.ahmed.flash.packages.di

import android.content.Context
import androidx.room.Room
import azizi.ahmed.flash.packages.data.FlashDao
import azizi.ahmed.flash.packages.data.FlashDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object FlashAppModule {

    @Singleton
    @Provides
    fun provideFlashDao(flashDatabase: FlashDatabase): FlashDao = flashDatabase.flashDao()


    @Singleton
    @Provides
    fun provideFlashDatabase(@ApplicationContext context: Context): FlashDatabase =
        Room
            .databaseBuilder(
                context,
                FlashDatabase::class.java,
                "FlashDatabase")
            .fallbackToDestructiveMigration(false)
            .build()
}