package com.example.myloginapplication.di.component

import android.app.Application
import com.example.myloginapplication.app.LoginApp
import com.example.myloginapplication.di.builder.ActivityBuilder
import com.example.myloginapplication.di.builder.ViewModelBuilder
import com.example.myloginapplication.di.module.DatabaseModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class,AndroidSupportInjectionModule::class,ActivityBuilder::class,DatabaseModule::class,ViewModelBuilder::class])
interface LoginComponent:AndroidInjector<LoginApp> {

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: Application):Builder

        fun build():LoginComponent
    }
}