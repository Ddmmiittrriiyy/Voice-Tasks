package com.voicetasks.next

import android.content.Context
import org.json.JSONObject

data class StoredAlarm(val id:String,val text:String,val at:Long)

object AlarmStore {
    private const val PREFS="voice_tasks_alarms"
    fun put(c:Context,a:StoredAlarm){ c.getSharedPreferences(PREFS,0).edit().putString(a.id,JSONObject().put("text",a.text).put("at",a.at).toString()).apply() }
    fun remove(c:Context,id:String){ c.getSharedPreferences(PREFS,0).edit().remove(id).apply() }
    fun all(c:Context):List<StoredAlarm>{
        return c.getSharedPreferences(PREFS,0).all.mapNotNull { (id,v) ->
            try { val j=JSONObject(v as String); StoredAlarm(id,j.optString("text"),j.getLong("at")) } catch(_:Exception){ null }
        }
    }
}
