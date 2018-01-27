package com.bmpl.android.mediafiles

import android.media.MediaPlayer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var mediaPlayer : MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mediaPlayer = MediaPlayer.create(this, R.raw.sample_music)

        playButton.setOnClickListener{
            mediaPlayer.start() // will start playing the song
        }
        pauseButton.setOnClickListener{
            mediaPlayer.pause() // will pause the current playing song
        }

        stopButton.setOnClickListener {
            mediaPlayer.stop()  // will stop playing music
        }

    }
}
