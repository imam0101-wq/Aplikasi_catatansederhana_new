package com.umko.catatan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PrefsManager prefsManager;
    private DatabaseHelper db;
    private CatatanAdapter adapter;

    private SwitchMaterial switchTema;
    private TextView tvKosong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefsManager = new PrefsManager(this);
        db = new DatabaseHelper(this);

        switchTema = findViewById(R.id.switchTema);
        tvKosong = findViewById(R.id.tvKosong);
        RecyclerView rv = findViewById(R.id.rvCatatan);
        FloatingActionButton fab = findViewById(R.id.fabTambah);

        adapter = new CatatanAdapter(this::konfirmasiHapus);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

        fab.setOnClickListener(v ->
                startActivity(new Intent(this, TambahCatatanActivity.class)));
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Baca lagi pilihan tema dari SharedPreferences, atur posisi switch,
        // lalu terapkan tema. Jadi selalu sesuai pilihan terakhir.
        boolean gelap = prefsManager.isDarkMode();

        switchTema.setOnCheckedChangeListener(null);
        switchTema.setChecked(gelap);
        terapkanTema(gelap);
        switchTema.setOnCheckedChangeListener((tombol, dicentang) -> {
            prefsManager.setDarkMode(dicentang);   // simpan pakai apply()
            terapkanTema(dicentang);
        });

        muatCatatan();
    }

    private void terapkanTema(boolean gelap) {
        AppCompatDelegate.setDefaultNightMode(
                gelap ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
    }

    private void muatCatatan() {
        List<Catatan> data = db.ambilSemua();
        adapter.setData(data);
        tvKosong.setVisibility(data.isEmpty() ? View.VISIBLE : View.GONE);
    }

    private void konfirmasiHapus(Catatan catatan) {
        new AlertDialog.Builder(this)
                .setTitle(R.string.hapus_judul)
                .setMessage(getString(R.string.hapus_pesan, catatan.getJudul()))
                .setPositiveButton(R.string.hapus, (d, w) -> {
                    db.hapusCatatan(catatan.getId());
                    muatCatatan();
                })
                .setNegativeButton(R.string.batal, null)
                .show();
    }

    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }
}
