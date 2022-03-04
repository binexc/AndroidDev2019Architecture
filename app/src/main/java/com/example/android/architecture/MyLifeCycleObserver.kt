
package com.example.android.architecture

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

class MyLifeCycleObserver : LifecycleEventObserver {
    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) : Unit {

        when (event) {
            Lifecycle.Event.ON_CREATE ->
                Log.i("FOO", "On_Create")
            Lifecycle.Event.ON_START ->
                Log.i("FOO", "On_Start")
            Lifecycle.Event.ON_PAUSE ->
                Log.i("FOO", "On_Pause")
            Lifecycle.Event.ON_RESUME ->
                Log.i("FOO", "On_Resume")
            Lifecycle.Event.ON_STOP ->
                Log.i("FOO", "On_Stop")
            Lifecycle.Event.ON_DESTROY ->
                Log.i("FOO", "On_Destroy")
            else ->
                Log.i("FOO", "Not sure - Could be ON_ANY")
        }//End when
    }
}