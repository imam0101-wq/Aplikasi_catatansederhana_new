package com.umko.catatan;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TambahCatatanActivity extends AppCompatActivity {

    private TextInputEditText etJudul;
    private TextInputEditText etIsi;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_catatan);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        db = new DatabaseHelper(this);
        etJudul = findViewById(R.id.etJudul);
        etIsi = findViewById(R.id.etIsi);
        Button btnSimpan = findViewById(R.id.btnSimpan);

        btnSimpan.setOnClickListener(v -> simpan());
    }

    private void simpan() {
        String judul = etJudul.getText() == null ? "" : etJudul.getText().toString().trim();
        String isi = etIsi.getText() == null ? "" : etIsi.getText().toString().trim();

        if (judul.isEmpty()) {
            etJudul.setError(getString(R.string.judul_kosong));
            return;
        }

        String waktu = new SimpleDateFormat("dd MMM yyyy, HH:mm", new Locale("id", "ID"))
                .format(new Date());
        db.tambahCatatan(judul, isi, waktu);

        Toast.makeText(this, R.string.catatan_tersimpan, Toast.LENGTH_SHORT).show();
        finish(); // daftar di halaman utama dimuat ulang di onResume()
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }
}
