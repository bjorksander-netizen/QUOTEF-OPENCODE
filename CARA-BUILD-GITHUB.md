# Cara mendapatkan APK lewat GitHub (tanpa install apa pun)

1. Buat akun di https://github.com (gratis) jika belum punya.
2. Klik tombol **+** (kanan atas) → **New repository** → beri nama `quote-filosofis` → **Create repository**.
3. Di halaman repo, klik **uploading an existing file**.
4. Ekstrak `QuoteFilosofis.zip` di komputermu, lalu seret SEMUA isi folder `QuoteFilosofis`
   (termasuk folder tersembunyi `.github`) ke halaman upload → **Commit changes**.
   - Jika folder `.github` tidak ikut terunggah lewat seret-lepas, buat filenya manual:
     **Add file → Create new file**, ketik nama `.github/workflows/build.yml`,
     lalu salin-tempel isi file `build.yml` dari zip.
5. Buka tab **Actions** → workflow "Build APK" akan berjalan otomatis (± 3–5 menit).
6. Setelah hijau (selesai), klik run tersebut → bagian **Artifacts** → unduh **QuoteFilosofis-APK**.
7. Ekstrak, kirim `app-debug.apk` ke HP Android, lalu install
   (izinkan "install dari sumber tidak dikenal" saat diminta).

Selesai — aplikasi siap dipakai.
