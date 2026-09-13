# Voice Tasks Android Core

Android-оболочка для общей PWA Voice Tasks.

Открывает: https://ddmmiittrriiyy.github.io/Voice-Tasks/

Нативная часть предоставляет JavaScript bridge `AndroidBridge`:
- `getCoreVersion()`
- `scheduleAlarm(taskId, text, triggerAtMillis)`
- `cancelAlarm(taskId)`

Будильник использует AlarmManager. После перезагрузки BootReceiver восстанавливает будущие активные будильники из SharedPreferences.

Откройте папку `android` как проект в Android Studio.
