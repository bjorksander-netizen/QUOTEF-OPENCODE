package com.bjorn.quotefilosofis

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews

class QuoteWidgetProvider : AppWidgetProvider() {

    companion object {
        private val BACKGROUNDS = intArrayOf(
            R.drawable.widget_bg_0,
            R.drawable.widget_bg_1,
            R.drawable.widget_bg_2,
            R.drawable.widget_bg_3,
            R.drawable.widget_bg_4
        )

        fun updateAll(context: Context) {
            val manager = AppWidgetManager.getInstance(context)
            val ids = manager.getAppWidgetIds(
                ComponentName(context, QuoteWidgetProvider::class.java)
            )
            for (id in ids) updateWidget(context, manager, id)
        }

        fun updateWidget(context: Context, manager: AppWidgetManager, widgetId: Int) {
            val lang    = Prefs.getLanguage(context)
            val schools = Prefs.getActiveSchools(context)
            val pool    = ALL_QUOTES.filter { it.school in schools }
            val quote   = if (pool.isEmpty()) null else pool.random()

            val views = RemoteViews(context.packageName, R.layout.widget_quote)

            if (quote != null) {
                val index  = ALL_QUOTES.indexOf(quote)
                Prefs.setLastQuoteIndex(context, index)

                val text   = quote.displayText(lang)
                val school = schoolLabel(quote.school, lang)
                views.setTextViewText(R.id.widget_text, "“$text”")
                views.setTextViewText(R.id.widget_author, "— ${quote.author} · $school  ⇪")

                // Ketuk baris penulis = bagikan quote yang SAMA dengan yang tampil
                val shareIntent = Intent(context, ShareActivity::class.java)
                    .putExtra(ShareActivity.EXTRA_QUOTE_INDEX, index)
                val sharePi = PendingIntent.getActivity(
                    context, 100000 + widgetId, shareIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )
                views.setOnClickPendingIntent(R.id.widget_author, sharePi)
            } else {
                views.setTextViewText(R.id.widget_text, if (lang == "en") "No school selected." else "Tidak ada aliran yang dipilih.")
                views.setTextViewText(R.id.widget_author, "")
            }

            val level = Prefs.getWidgetAlphaLevel(context).coerceIn(0, 4)
            views.setInt(R.id.widget_root, "setBackgroundResource", BACKGROUNDS[level])

            // Ketuk area quote = quote baru
            val intent = Intent(context, QuoteWidgetProvider::class.java).apply {
                action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
                putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, intArrayOf(widgetId))
            }
            val pending = PendingIntent.getBroadcast(
                context, widgetId, intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
            views.setOnClickPendingIntent(R.id.widget_text, pending)
            views.setOnClickPendingIntent(R.id.widget_root, pending)

            manager.updateAppWidget(widgetId, views)
        }
    }

    override fun onUpdate(context: Context, manager: AppWidgetManager, ids: IntArray) {
        for (id in ids) updateWidget(context, manager, id)
    }
}
