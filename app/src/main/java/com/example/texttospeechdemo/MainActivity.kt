package com.example.texttospeechdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private var tts: TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //第1引数にアクティビティ、第2にリスナー
        tts = TextToSpeech(this, this)

        btnStop.visibility = View.INVISIBLE
        btnStop.isEnabled = false

        btnSpeak.setOnClickListener {

            if (etTargetText.text.isEmpty()) {
                Toast.makeText(this, "テキストを入力してください", Toast.LENGTH_SHORT).show()
            } else {
                //QUEUE_FLUSHは止めたときにキューを消去
                btnSpeak.visibility = View.INVISIBLE
                btnSpeak.isEnabled = false
                btnStop.visibility = View.VISIBLE
                btnStop.isEnabled = true
                tts!!.speak(etTargetText.text.toString(), TextToSpeech.QUEUE_FLUSH, null, "")
            }
        }

        btnStop.setOnClickListener {
            btnStop.visibility = View.INVISIBLE
            btnStop.isEnabled = true
            btnSpeak.visibility = View.VISIBLE
            btnSpeak.isEnabled = false
            tts!!.stop()
        }
    }

    override fun onInit(status: Int) {

        if (status == TextToSpeech.SUCCESS) {
            val result = tts!!.setLanguage(Locale.getDefault())

            if (result == TextToSpeech.LANG_NOT_SUPPORTED || result == TextToSpeech.LANG_MISSING_DATA) {
                Log.e("tts", "その言語はサポートされていません。")
            }
        } else {
            Log.e("tts", "初期化に失敗しました。")
        }
    }

    public override fun onDestroy() {

        if(tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }

        super.onDestroy()
    }
}