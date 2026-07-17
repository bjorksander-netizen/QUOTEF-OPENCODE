package com.bjorn.quotefilosofis

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat

object NotificationHelper {

    private const val CH_NORMAL     = "ch_normal"
    private const val CH_LOCKSCREEN = "ch_lockscreen"
    private const val CH_SILENT     = "ch_silent"

    fun createChannels(context: Context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return
        val nm = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        nm.createNotificationChannel(
            NotificationChannel(CH_NORMAL, "Normal", NotificationManager.IMPORTANCE_DEFAULT)
        )
        nm.createNotificationChannel(
            NotificationChannel(CH_LOCKSCREEN, "Lockscreen", NotificationManager.IMPORTANCE_HIGH).apply {
                lockscreenVisibility = NotificationCompat.VISIBILITY_PUBLIC
            }
        )
        nm.createNotificationChannel(
            NotificationChannel(CH_SILENT, "Silent", NotificationManager.IMPORTANCE_LOW).apply {
                setSound(null, null)
                enableVibration(false)
            }
        )
    }

    fun showQuote(context: Context) {
        val mode = Prefs.getNotifMode(context)
        if (mode == "off") return

        val lang    = Prefs.getLanguage(context)
        val schools = Prefs.getActiveSchools(context)
        val pool    = ALL_QUOTES.filter { it.school in schools }
        if (pool.isEmpty()) return

        val quote = pool.random()
        val index = ALL_QUOTES.indexOf(quote)
        Prefs.setLastQuoteIndex(context, index)

        val text   = quote.displayText(lang)
        val school = schoolLabel(quote.school, lang)

        val (channelId, visibility) = when (mode) {
            "lockscreen" -> CH_LOCKSCREEN to NotificationCompat.VISIBILITY_PUBLIC
            "silent"     -> CH_SILENT     to NotificationCompat.VISIBILITY_SECRET
            else         -> CH_NORMAL     to NotificationCompat.VISIBILITY_PRIVATE
        }

        // Aksi "Bagikan": buka ShareActivity dengan quote yang SAMA
        val shareIntent = Intent(context, ShareActivity::class.java)
            .putExtra(ShareActivity.EXTRA_QUOTE_INDEX, index)
        val sharePi = PendingIntent.getActivity(
            context, 200, shareIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val shareLabel = if (lang == "en") "Share" else "Bagikan"

        val nm = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notif = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(android.R.drawable.ic_menu_compass)
            .setContentTitle("${quote.author} · $school")
            .setContentText(text)
            .setStyle(NotificationCompat.BigTextStyle().bigText(text))
            .setVisibility(visibility)
            .setColor(0xFF4DB6AC.toInt())
            .setAutoCancel(true)
            .addAction(android.R.drawable.ic_menu_share, shareLabel, sharePi)
            .build()

        nm.notify(1, notif)
    }
}
