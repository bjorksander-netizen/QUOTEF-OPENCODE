# Quote Filosofis (Android)

Aplikasi Android ringan yang mengirim quote motivasi filosofis (Optimisme, Stoisisme,
Nihilisme, Eksistensialisme, Absurdisme, Filsafat Timur) lewat notifikasi dan widget.

## Fitur
- Notifikasi quote berkala (15 menit s/d sekali sehari)
- 4 mode notifikasi yang bisa dipilih:
  - **Lockscreen** — quote tampil penuh di layar kunci
  - **Biasa** — isi disembunyikan di layar kunci
  - **Senyap** — tanpa suara & getar
  - **Mati** — hanya widget
- Widget homescreen (ketuk widget = quote baru)
- Filter aliran filosofi
- Tombol "Tampilkan quote sekarang"
- Tetap berjalan setelah HP restart

## Catatan widget lockscreen
Android menghapus widget lockscreen sejak Android 5 (baru kembali terbatas di tablet
Android 15+). Sebagai gantinya, mode **Lockscreen** menampilkan quote lengkap sebagai
notifikasi di layar kunci — efeknya sama.

## Keamanan
- **Tanpa izin internet** — 100% offline, tidak ada data yang keluar dari HP
- Tanpa iklan, tanpa tracking, tanpa analytics
- `allowBackup=false`, semua komponen non-exported
- Hanya 2 izin: notifikasi (diminta saat dibuka) & boot (untuk melanjutkan jadwal)

## Cara build (menghasilkan APK)
1. Install [Android Studio](https://developer.android.com/studio)
2. Buka Android Studio → **Open** → pilih folder `QuoteFilosofis` → tunggu Gradle sync
3. Menu **Build → Build App Bundle(s) / APK(s) → Build APK(s)**
4. APK ada di `app/build/outputs/apk/debug/app-debug.apk`
5. Kirim ke HP dan install (izinkan "install dari sumber tidak dikenal")

Teknologi: Kotlin, WorkManager, AppWidgetProvider. minSdk 24 (Android 7) – target Android 14.
