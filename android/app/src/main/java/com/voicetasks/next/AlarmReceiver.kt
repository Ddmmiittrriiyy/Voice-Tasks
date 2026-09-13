package com.voicetasks.next

import android.content.*
import android.app.*

class AlarmReceiver:BroadcastReceiver(){
    override fun onReceive(c:Context,i:Intent){
        val id=i.getStringExtra("id")?:""
        val text=i.getStringExtra("text")?:"Задача"
        AlarmStore.remove(c,id)
        AlarmStore.markTriggered(c,id)
        val launch=Intent(c,AlarmActivity::class.java).putExtra("id",id).putExtra("text",text).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        c.startActivity(launch)
    }
}
