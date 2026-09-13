package com.voicetasks.next

import android.webkit.JavascriptInterface

class AndroidBridge(private val activity:MainActivity) {
    @JavascriptInterface fun getCoreVersion()="0.1.2"
    @JavascriptInterface fun scheduleAlarm(taskId:String,text:String,triggerAtMillis:Long){ AlarmScheduler.schedule(activity,StoredAlarm(taskId,text,triggerAtMillis)) }
    @JavascriptInterface fun cancelAlarm(taskId:String){ AlarmScheduler.cancel(activity,taskId) }
    @JavascriptInterface fun startListening(language:String){ activity.startVoiceRecognition(language) }
    @JavascriptInterface fun exportTasks(json:String,filename:String){ activity.exportTasks(json,filename) }
    @JavascriptInterface fun importTasks(){ activity.importTasks() }
}
