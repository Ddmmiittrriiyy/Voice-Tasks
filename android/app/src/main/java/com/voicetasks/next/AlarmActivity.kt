package com.voicetasks.next

import android.app.*
import android.os.*
import android.graphics.Color
import android.media.RingtoneManager
import android.view.Gravity
import android.widget.*

class AlarmActivity:Activity(){
    private var ringtone:android.media.Ringtone?=null

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
            this.text=java.text.SimpleDateFormat("HH:mm").format(java.util.Date())
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

        ringtone=RingtoneManager.getRingtone(
            this,
            RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        )
        ringtone?.play()
    }

    override fun onDestroy(){
        ringtone?.stop()
        super.onDestroy()
    }
}
