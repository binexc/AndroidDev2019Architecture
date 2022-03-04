package com.example.android.architecture

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
    //This has a problem - you haven't loaded the layout file yet, so you
    //don't actually have access to the die variables. ie: You're going to crash!
    /***** private val imageViewsBad = arrayOf<ImageView>(die1, die2, die3, die4, die5) ******/

    //This works since you don't instantiate until you need/use it - which is
    //after the layout is fully loaded.
    private val imageViews by lazy { arrayOf<ImageView>(die1, die2, die3, die4, die5) }

    private lateinit var vwMod : JeffsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        //It returns a reference.
        //Once we have this reference, we can store data within the view-model.
        vwMod = ViewModelProvider(this).get(JeffsViewModel::class.java)
        headline.text = savedInstanceState?.getString(HEADLINE_KEY) ?: "Empty"

        //Here we're just registering so that when the variable is updated, here's where we'll
        //enter into the code to update our UI
        vwMod.moreInfo.observe(this, Observer {
            headline.text = it
        })

//        fab.setOnClickListener(fun(view: View) {
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        })

//        fab.setOnClickListener{ view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }

//        fab.setOnClickListener{view -> DoTheSnackBarStuff(view)}

        //Used an explicit intent [cmd + return] to explicitly convert the
        // explicit "view" parameter to 'it'
        fab.setOnClickListener{ DoTheSnackBarStuff(it)}

        lifecycle.addObserver(MyLifeCycleObserver())

        //Makes all the dice images show the number 6.
        for(imgVw in imageViews) {
            imgVw.setImageResource(R.drawable.die_6)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(HEADLINE_KEY, HEADLINE_TEXT)//Call before super

        super.onSaveInstanceState(outState)
    }

    private fun DoTheSnackBarStuff(view: View) : Unit {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
    }
}