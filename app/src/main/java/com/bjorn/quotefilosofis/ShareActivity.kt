package com.bjorn.quotefilosofis

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Typeface
import android.os.Bundle
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream
import kotlin.random.Random

class ShareActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val lang = Prefs.getLanguage(this)
        val idx = intent.getIntExtra(EXTRA_QUOTE_INDEX, -1)
        val quote = ALL_QUOTES.getOrNull(idx) ?: run {
            val schools = Prefs.getActiveSchools(this)
            val pool = ALL_QUOTES.filter { it.school in schools }
            (pool.ifEmpty { ALL_QUOTES }).random()
        }

        try {
            val bmp = renderCard(quote, lang)
            val dir = File(cacheDir, "shared").apply { mkdirs() }
            val file = File(dir, "quote.png")
            FileOutputStream(file).use { bmp.compress(Bitmap.CompressFormat.PNG, 100, it) }
            bmp.recycle()

            val uri = FileProvider.getUriForFile(this, "$packageName.fileprovider", file)
            val caption = "\u201c${quote.displayText(lang)}\u201d \u2014 ${quote.author}"
            val send = Intent(Intent.ACTION_SEND).apply {
                type = "image/png"
                putExtra(Intent.EXTRA_STREAM, uri)
                putExtra(Intent.EXTRA_TEXT, caption)
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
            val title = if (lang == "en") "Share quote" else "Bagikan quote"
            startActivity(Intent.createChooser(send, title))
        } catch (_: Exception) {
        }
        finish()
    }

    private fun renderCard(quote: Quote, lang: String): Bitmap {
        val fontSize = Prefs.getShareFontSize(this)
        val theme = Prefs.getShareTheme(this)

        val sizes = FONT_SIZES[fontSize]
        val colors = if (theme == "random") RANDOM_THEMES[Random.nextInt(RANDOM_THEMES.size)]
                     else BASIC_THEME

        val w = 1080
        val h = 1920
        val bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bmp)
        canvas.drawColor(colors.bg)

        val cardMargin = 120f
        val pad = 76f
        val contentWidth = (w - 2 * cardMargin - 2 * pad).toInt()

        val authorPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
            color = colors.textPrimary; textSize = sizes.author
            typeface = Typeface.create("sans-serif-medium", Typeface.NORMAL)
        }
        val schoolPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
            color = colors.textSecondary; textSize = sizes.school
        }
        val quotePaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
            color = colors.textPrimary; textSize = sizes.quote
            typeface = Typeface.create("sans-serif-medium", Typeface.NORMAL)
        }
        val brandPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
            color = colors.accent; textSize = sizes.brand
            typeface = Typeface.create("sans-serif", Typeface.BOLD)
        }
        val dividerPaint = Paint().apply { color = colors.divider }
        val cardPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = colors.card }

        val text = quote.displayText(lang)
        val quoteLayout = StaticLayout.Builder
            .obtain(text, 0, text.length, quotePaint, contentWidth)
            .setLineSpacing(0f, 1.45f)
            .setAlignment(Layout.Alignment.ALIGN_NORMAL)
            .build()

        val headerH = sizes.author + 58f
        val cardH = pad + headerH + 44f + 3f + 60f +
                quoteLayout.height + 68f + sizes.brand + pad
        val cardTop = ((h - cardH) / 2f).coerceAtLeast(80f)
        val rect = RectF(cardMargin, cardTop, w - cardMargin, cardTop + cardH)
        canvas.drawRoundRect(rect, 44f, 44f, cardPaint)

        val x = cardMargin + pad
        var y = cardTop + pad + sizes.author
        canvas.drawText(quote.author, x, y, authorPaint)
        y += 58f
        canvas.drawText(schoolLabel(quote.school, lang), x, y, schoolPaint)
        y += 44f
        canvas.drawRect(x, y, w - cardMargin - pad, y + 3f, dividerPaint)
        y += 60f
        canvas.save()
        canvas.translate(x, y)
        quoteLayout.draw(canvas)
        canvas.restore()
        y += quoteLayout.height + 68f + 32f
        canvas.drawText("\u275d QUOTEF", x, y, brandPaint)

        return bmp
    }

    companion object {
        const val EXTRA_QUOTE_INDEX = "quote_index"

        private data class CardSizes(val author: Float, val school: Float, val quote: Float, val brand: Float)
        private data class CardColors(
            val bg: Int, val card: Int,
            val textPrimary: Int, val textSecondary: Int,
            val accent: Int, val divider: Int
        )

        private val FONT_SIZES = arrayOf(
            CardSizes(38f, 32f, 48f, 36f),   // small
            CardSizes(46f, 40f, 62f, 44f),   // medium
            CardSizes(54f, 48f, 78f, 52f)    // large
        )

        private val BASIC_THEME = CardColors(
            bg = 0xFF0A2530.toInt(),
            card = 0xFF10333E.toInt(),
            textPrimary = 0xFFDCEFF2.toInt(),
            textSecondary = 0xFF7FA6AE.toInt(),
            accent = 0xFF8FB0B8.toInt(),
            divider = 0x26FFFFFF
        )

        private val RANDOM_THEMES = arrayOf(
            // Midnight Violet
            CardColors(
                bg = 0xFF0F0A1A.toInt(), card = 0xFF1A1230.toInt(),
                textPrimary = 0xFFE8DFF5.toInt(), textSecondary = 0xFF8B7FA8.toInt(),
                accent = 0xFFB39DDB.toInt(), divider = 0x26FFFFFF
            ),
            // Warm Sunset
            CardColors(
                bg = 0xFF1A0F0A.toInt(), card = 0xFF2D1A10.toInt(),
                textPrimary = 0xFFF5E6D3.toInt(), textSecondary = 0xFFA89080.toInt(),
                accent = 0xFFFFAB91.toInt(), divider = 0x26FFFFFF
            ),
            // Forest Deep
            CardColors(
                bg = 0xFF0A1A0F.toInt(), card = 0xFF122D1A.toInt(),
                textPrimary = 0xFFD5E8D4.toInt(), textSecondary = 0xFF7FA87E.toInt(),
                accent = 0xFFA5D6A7.toInt(), divider = 0x26FFFFFF
            ),
            // Ocean Dark
            CardColors(
                bg = 0xFF0A1220.toInt(), card = 0xFF102035.toInt(),
                textPrimary = 0xFFD0E4F5.toInt(), textSecondary = 0xFF7090B0.toInt(),
                accent = 0xFF90CAF9.toInt(), divider = 0x26FFFFFF
            ),
            // Rose Night
            CardColors(
                bg = 0xFF1A0A14.toInt(), card = 0xFF2D1020.toInt(),
                textPrimary = 0xFFF5DDE8.toInt(), textSecondary = 0xFFA87F95.toInt(),
                accent = 0xFFF48FB1.toInt(), divider = 0x26FFFFFF
            ),
            // Amber Gold
            CardColors(
                bg = 0xFF1A150A.toInt(), card = 0xFF2D2410.toInt(),
                textPrimary = 0xFFF5EDD3.toInt(), textSecondary = 0xFFA89E70.toInt(),
                accent = 0xFFFFD54F.toInt(), divider = 0x26FFFFFF
            ),
            // Slate Mono
            CardColors(
                bg = 0xFF111111.toInt(), card = 0xFF1C1C1C.toInt(),
                textPrimary = 0xFFD4D4D4.toInt(), textSecondary = 0xFF808080.toInt(),
                accent = 0xFFBDBDBD.toInt(), divider = 0x26FFFFFF
            ),
            // Neon Cyan
            CardColors(
                bg = 0xFF0A1A1A.toInt(), card = 0xFF102D2D.toInt(),
                textPrimary = 0xFFD4F5F5.toInt(), textSecondary = 0xFF7FA8A8.toInt(),
                accent = 0xFF00E5FF.toInt(), divider = 0x26FFFFFF
            )
        )
    }
}
