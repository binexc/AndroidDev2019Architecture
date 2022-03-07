package com.example.android.architecture

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.android.architecture.viewModel.DiceViewModel
import kotlinx.android.synthetic.main.activity_dice.*

val configChangeKey:String = "CONFIG_CHANGE"

class DiceActivity : AppCompatActivity() {

    private lateinit var viewModel : DiceViewModel

    private val imageViews by lazy {
        arrayOf<ImageView>(
            findViewById(R.id.die1),
            findViewById(R.id.die2),
            findViewById(R.id.die3),
            findViewById(R.id.die4),
            findViewById(R.id.die5)
        )
    }

    private val headline by lazy {
        findViewById<TextView>(R.id.headline)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dice)
        setSupportActionBar(toolbar)

        //This is all it takes to get an arrow button "back" to the parent activity.
        supportActionBar?.setDisplayHomeAsUpEnabled(true) //Will default to the "arrow" image
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_come_back) //Custom image

        viewModel = ViewModelProvider(this).get(DiceViewModel::class.java)

        viewModel.headline.observe(this, Observer {
            headline.text = it
        })
        viewModel.dice.observe(this, Observer {
            updateDisplay(it)
        })

        lifecycle.addObserver(MyLifecycleObserver())

        //fab.setOnClickListener { viewModel.rollDice() }

        if ((savedInstanceState?.getBoolean(configChangeKey) ?: false) == false) {
            viewModel.rollDice()
        }
    }

    private fun updateDisplay(dice: IntArray) {
        for (i in 0 until imageViews.size) {
            val drawableId = when (dice[i]) {
                1 -> R.drawable.die_1
                2 -> R.drawable.die_2
                3 -> R.drawable.die_3
                4 -> R.drawable.die_4
                5 -> R.drawable.die_5
                6 -> R.drawable.die_6
                else -> R.drawable.die_6
            }
            imageViews[i].setImageResource(drawableId)
        }
    }

    //This is called when you're destroying an activity - potentially due to
    // configuration changes such as device rotation
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(configChangeKey, true)
        super.onSaveInstanceState(outState)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_foo, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.i("FOO", "The R.MENU.ID is: ${R.menu.menu_foo}")
        Log.i("FOO", "The R.ID is: ${R.id.menuFooID}")
        Log.i("FOO", "The Item ID is: ${item.itemId}")

        return when (item.itemId) {
            //R.menu.menu_foo -> shareResults()
            R.id.menuFooID -> shareResults()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun shareResults(): Boolean {

        Log.i("FOO", "YOU GOT THE AIRPLANE CLICK!!!!!!!!!!!!")
        return true

        var shareIntent = Intent().apply {
            //Sending data to another application on the same device.
            //Also note - 'viewModel.headline' in the DiceViewModel defined on line 18.
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "Rolled the dice ${viewModel.headline.value}")
            type = "text/plain" //This is nothing more than your mime type.
        }//At this point - we have EVERYTHING we need to start the other activity

        //This will now "display" an O/S layer UI which will display all the applications on
        // the device which have "intent-filters" that can receive simple text. ie: Message
        // or email.  The user will chose which app to use to "share" the data you proviced
        // in the "putExtra" component of the menu action item.
        startActivity(shareIntent)

        return true
    }

}
