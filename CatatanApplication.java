package com.umko.catatan;

import android.app.Application;

import androidx.appcompat.app.AppCompatDelegate;

/**
 * Menerapkan tema tersimpan sebelum activity tampil,
 * supaya layar tidak berkedip waktu aplikasi dibuka.
 */
public class CatatanApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        boolean gelap = new PrefsManager(this).isDarkMode();
        AppCompatDelegate.setDefaultNightMode(
                gelap ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
    }
}
