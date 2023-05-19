package com.example.randomizer

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Random

class MainActivity() : AppCompatActivity(), Parcelable {
    constructor(parcel: Parcel) : this() {
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        val player = findViewById<TextView>(R.id.player)
        val map = findViewById<TextView>(R.id.map)
        val colonies = findViewById<TextView>(R.id.colonies)
        val options = arrayOf("Hellas", "Ellyssium", "Standard")
        val seekbar= findViewById<SeekBar>(R.id.seekBar)
        val progresstext = findViewById<TextView>(R.id.progresstext)

        button.setOnClickListener {
            val count = seekbar.progress + 2
            val numbers = generateUniqueNumbers(count, 1, 11)
            val sortedNumbers = numbers.sorted()
            val numbersText = sortedNumbers.joinToString(", ")
            colonies.text = numbersText
            val rand = Random().nextInt(seekbar.progress)+1
            player.text = "First Player: $rand"
            val randa = Random().nextInt(options.size)
            val chosenOption = options[randa]
            map.text = chosenOption
        }

        seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                progresstext.text = progress.toString()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // Not needed for this example
            }
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // Not needed for this example
            }
        })

    }


    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MainActivity> {
        override fun createFromParcel(parcel: Parcel): MainActivity {
            return MainActivity(parcel)
        }

        override fun newArray(size: Int): Array<MainActivity?> {
            return arrayOfNulls(size)
        }

    }
    private fun generateUniqueNumbers(count: Int, start: Int, end: Int): List<Int> {
        val numbers = mutableListOf<Int>()
        val range = (start..end).toList()
        while (numbers.size < count) {
            val number = range.random()
            if (!numbers.contains(number)) {
                numbers.add(number)
            }
        }
        return numbers
    }

}
