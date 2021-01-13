package com.example.myloginapplication.app

import com.example.myloginapplication.di.component.DaggerLoginComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class LoginApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {

        return DaggerLoginComponent.builder().application(this).build()

    }
}