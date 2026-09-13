package com.voicetasks.next

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.graphics.Color
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.view.Gravity
import android.widget.*
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AlarmActivity:Activity(){
    private var player:MediaPlayer?=null
    private var vibrator:Vibrator?=null

    override fun onCreate(b:Bundle?){
        super.onCreate(b)
        if(Build.VERSION.SDK_INT>=27){
            setShowWhenLocked(true)
            setTurnScreenOn(true)
        }

        val text=intent.getStringExtra("text")?:"Задача"

        val box=LinearLayout(this).apply{
            orientation=LinearLayout.VERTICAL
            gravity=Gravity.CENTER
            setPadding(48,48,48,48)
            setBackgroundColor(Color.WHITE)
        }

        val time=TextView(this).apply{
            this.text=SimpleDateFormat("HH:mm",Locale.getDefault()).format(Date())
            textSize=48f
            setTextColor(Color.BLACK)
            gravity=Gravity.CENTER
        }

        val task=TextView(this).apply{
            this.text=text
            textSize=24f
            setTextColor(Color.BLACK)
            gravity=Gravity.CENTER
            setPadding(0,40,0,50)
        }

        val stop=Button(this).apply{
            this.text="ОТКЛЮЧИТЬ"
            setOnClickListener{finish()}
        }

        box.addView(time)
        box.addView(task)
        box.addView(stop)
        setContentView(box)

        startAlarmSound()
        startVibration()
    }

    private fun startAlarmSound(){
        val uri=RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
            ?: RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        try{
            player=MediaPlayer().apply{
                setAudioAttributes(
                    AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_ALARM)
                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                        .build()
                )
                setDataSource(this@AlarmActivity,uri)
                isLooping=true
                prepare()
                start()
            }
        }catch(_:Exception){
            player?.release()
            player=null
        }
    }

    private fun startVibration(){
        vibrator=if(Build.VERSION.SDK_INT>=31){
            getSystemService(VibratorManager::class.java).defaultVibrator
        }else{
            @Suppress("DEPRECATION")
            getSystemService(VIBRATOR_SERVICE) as Vibrator
        }

        val pattern=longArrayOf(0,700,450,700,450)
        vibrator?.vibrate(VibrationEffect.createWaveform(pattern,0))
    }

    override fun onDestroy(){
        vibrator?.cancel()
        player?.stop()
        player?.release()
        player=null
        super.onDestroy()
    }
}
