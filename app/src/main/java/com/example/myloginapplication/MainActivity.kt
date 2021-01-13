package com.example.myloginapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myloginapplication.di.builder.ViewModelFactory
import com.example.myloginapplication.ui.viewmodel.MainViewModel
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var mainViewModel: MainViewModel
    internal var eMail:String =""
    internal var password:String =""
    internal var userName:String =""

    override fun onCreate(savedInstanceState: Bundle?) {
//        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel= viewModelFactory.create(MainViewModel::class.java)

    }

    override fun onBackPressed() {
//        super.onBackPressed()
    }
}