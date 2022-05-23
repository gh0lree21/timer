package com.holley.timer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.view.View
import android.widget.Chronometer
import android.widget.Toast
import android.os.SystemClock


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        var pauseOffset : Long = 0
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val meter = findViewById<Chronometer>(R.id.timer)

        val startButton = findViewById<Button>(R.id.startButton)
        val stopButton = findViewById<Button>(R.id.stopButton)
        startButton.setText(R.string.start)
        stopButton.setText(R.string.reset)
        startButton?.setOnClickListener(object : View.OnClickListener {
            var isRunning = false

            override fun onClick(v: View) {
                if (!isRunning) {
                    meter.setBase(SystemClock.elapsedRealtime() - pauseOffset)
                    meter.start()
                    isRunning = true
                } else {
                    meter.stop()
                    pauseOffset = SystemClock.elapsedRealtime() - meter.getBase()
                    isRunning = false
                }

                startButton.setText(if (isRunning) R.string.pause else R.string.start)

                Toast.makeText(this@MainActivity, getString(
                    if(isRunning)
                        R.string.working
                    else
                        R.string.interrupt),
                    Toast.LENGTH_SHORT).show()

            }
        })
        stopButton?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                meter.setBase(SystemClock.elapsedRealtime())
                pauseOffset = 0

                Toast.makeText(this@MainActivity, getString(
                    R.string.reset),
                    Toast.LENGTH_SHORT).show()

            }
        })
    }
}