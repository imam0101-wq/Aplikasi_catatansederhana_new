# Catatan Saya

Aplikasi catatan sederhana dengan mode gelap/terang. Dibuat untuk Tugas P10 Pemrograman Mobile (SharedPreferences), Prodi STI, Universitas Muhammadiyah Kotabumi.

Pilihan tema disimpan di SharedPreferences, jadi tetap sama walaupun aplikasi di-force stop lalu dibuka lagi. Isi catatan disimpan di SQLite.

## Fitur
- Daftar catatan (RecyclerView)
- Tambah catatan (judul + isi)
- Hapus catatan (tombol Hapus atau tekan lama)
- Switch mode gelap/terang

## Pembagian data
| Data | Disimpan di | Alasan |
|------|-------------|--------|
| `dark_mode` (boolean) | SharedPreferences (`pengaturan_catatan`) | Kecil, satu nilai, harus cepat dibaca saat buka aplikasi |
| Judul, isi, waktu catatan | SQLite (`catatan.db`) | Banyak, terus bertambah, perlu tambah/hapus per baris |

## Cara kerja SharedPreferences
`PrefsManager` -> `setDarkMode()` memakai `apply()` -> nilai dibaca lagi di `onResume()` pada `MainActivity`.

## Cara menjalankan
1. Buka folder proyek ini di Android Studio (Giraffe/Hedgehog atau lebih baru, JDK 17).
2. Tunggu Gradle sync selesai.
3. Jalankan di emulator atau HP (minimal Android 7.0 / API 24).

Tes bukti: geser switch ke mode gelap, lalu Pengaturan HP > Aplikasi > Catatan Saya > Paksa berhenti, buka lagi. Tema dan catatan masih ada.
