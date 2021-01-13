package com.example.myloginapplication.di.builder

import com.example.myloginapplication.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector()
   abstract fun providesMainActivity():MainActivity
}