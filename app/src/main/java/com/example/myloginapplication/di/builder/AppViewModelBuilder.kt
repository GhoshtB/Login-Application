package com.example.myloginapplication.di.builder

import androidx.lifecycle.ViewModel
import com.example.myloginapplication.ui.viewmodel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AppViewModelBuilder {

    @IntoMap()
    @Binds
    @viewModelKey(MainViewModel::class)
    abstract fun providesMainViewModel(mainViewModel: MainViewModel): ViewModel
}