package com.example.myloginapplication.di.builder

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.PROPERTY_GETTER,
AnnotationTarget.PROPERTY_SETTER,
AnnotationTarget.FUNCTION)
@MapKey
annotation class viewModelKey(val viemodel:KClass<out ViewModel>)
