package com.example.android.architecture

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        roleBtn.setOnClickListener{OnClickHandler(it)}
    }

    private fun OnClickHandler(view: View) : Unit {
        val intent = Intent(this, DiceActivity::class.java)
        startActivity(intent)
    }
}