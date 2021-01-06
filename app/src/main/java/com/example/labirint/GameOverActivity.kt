package com.example.labirint

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.widget.TextView

class GameOverActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)
        var v = findViewById<TextView>(R.id.textView2)
        val message = intent.getStringExtra(EXTRA_MESSAGE)
        v.setText("Koniec gry! Wynik: " + message)
    }
}