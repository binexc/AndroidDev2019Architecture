package com.example.android.architecture

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

/**
 * AndroidViewModel is a sub-class of another class simply called ViewModel
 * AndroidViewModel though gives you access to your application's context
 *
 * Changes to data within the viewmodel will be broadcast to the activity which
 * has a reference to the instance of the view mode.
 *
 * Note the Application is being passed in when the viewmodel is created BUT
 * it will NOT be active/persists for the entire life of the view-model so be sure
 * to save a reference to it.
 */
class JeffsViewModel(app:Application) : AndroidViewModel(app) {
    //Be certain this is mutable live since it will be getting accessed
    //via multiple threads, depending on when it is update and later consumed.
    val moreInfo = MutableLiveData<String>()

    private val myAppRef:Application = app //So we can later get a context

    init {

        Log.i("FOO", "View Model Created")
    }
}