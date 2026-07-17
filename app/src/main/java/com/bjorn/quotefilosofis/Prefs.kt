package com.bjorn.quotefilosofis

import android.content.Context
import android.content.SharedPreferences

object Prefs {
    private const val NAME = "qf_prefs"
    private fun sp(c: Context): SharedPreferences =
        c.getSharedPreferences(NAME, Context.MODE_PRIVATE)

    // ── Language ──────────────────────────────────────────────────────────────
    fun getLanguage(c: Context): String = sp(c).getString("language", "id") ?: "id"
    fun setLanguage(c: Context, lang: String) =
        sp(c).edit().putString("language", lang).apply()

    // ── Notification interval (minutes) ──────────────────────────────────────
    fun getIntervalMinutes(c: Context): Int = sp(c).getInt("interval_minutes", 60)
    fun setIntervalMinutes(c: Context, v: Int) =
        sp(c).edit().putInt("interval_minutes", v).apply()

    // ── Notification mode: "lockscreen" | "normal" | "silent" | "off" ────────
    fun getNotifMode(c: Context): String = sp(c).getString("notif_mode", "normal") ?: "normal"
    fun setNotifMode(c: Context, v: String) =
        sp(c).edit().putString("notif_mode", v).apply()

    // ── Active schools ────────────────────────────────────────────────────────
    private val ALL_SCHOOLS = listOf(
        "Optimisme", "Stoisisme", "Nihilisme",
        "Eksistensialisme", "Absurdisme", "Timur"
    )

    fun getActiveSchools(c: Context): Set<String> {
        val saved = sp(c).getStringSet("active_schools", null)
        return saved ?: ALL_SCHOOLS.toSet()
    }

    fun setActiveSchools(c: Context, schools: Set<String>) =
        sp(c).edit().putStringSet("active_schools", schools).apply()

    // ── Widget alpha level (0 = opaque … 4 = transparent) ────────────────────
    fun getWidgetAlphaLevel(c: Context): Int = sp(c).getInt("widget_alpha", 1)
    fun setWidgetAlphaLevel(c: Context, v: Int) =
        sp(c).edit().putInt("widget_alpha", v.coerceIn(0, 4)).apply()

    // ── Last shown quote (index di ALL_QUOTES) untuk fitur share ─────────────
    fun getLastQuoteIndex(c: Context): Int = sp(c).getInt("last_quote_index", -1)
    fun setLastQuoteIndex(c: Context, v: Int) =
        sp(c).edit().putInt("last_quote_index", v).apply()
}
