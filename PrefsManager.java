package com.umko.catatan;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Pembungkus SharedPreferences.
 * Hanya menyimpan satu nilai kecil: pilihan tema gelap/terang (boolean).
 */
public class PrefsManager {

    private static final String NAMA_PREFS = "pengaturan_catatan";
    private static final String KEY_DARK_MODE = "dark_mode";

    private final SharedPreferences prefs;

    public PrefsManager(Context context) {
        prefs = context.getApplicationContext()
                .getSharedPreferences(NAMA_PREFS, Context.MODE_PRIVATE);
    }

    /** apply() menyimpan di latar belakang (asinkron), jadi layar tidak tersendat. */
    public void setDarkMode(boolean aktif) {
        prefs.edit().putBoolean(KEY_DARK_MODE, aktif).apply();
    }

    /** Default false (mode terang) kalau belum pernah disimpan. */
    public boolean isDarkMode() {
        return prefs.getBoolean(KEY_DARK_MODE, false);
    }
}
