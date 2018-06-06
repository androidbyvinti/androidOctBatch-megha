package com.bmpl.backgroundservice

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.widget.Toast

class MyService : Service() {

    lateinit var mediaPlayer: MediaPlayer

    // Bound service
    override fun onBind(intent: Intent): IBinder? {
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {

        mediaPlayer = MediaPlayer.create(this, R.raw.song)
        mediaPlayer.start()
        //mediaPlayer.isLooping = true
        return Service.START_NOT_STICKY //Service -- auto restart pending intent generate
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show()
    }
}