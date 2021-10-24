package com.example.texttospeechdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.View.INVISIBLE
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStop.visibility = INVISIBLE
        btnStop.isEnabled = false

    }
}