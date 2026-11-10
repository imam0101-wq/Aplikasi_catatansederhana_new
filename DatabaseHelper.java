package com.umko.catatan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Menyimpan isi catatan di SQLite (data banyak dan terus bertambah).
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String NAMA_DB = "catatan.db";
    private static final int VERSI_DB = 1;

    private static final String TABEL = "catatan";
    private static final String KOLOM_ID = "id";
    private static final String KOLOM_JUDUL = "judul";
    private static final String KOLOM_ISI = "isi";
    private static final String KOLOM_WAKTU = "waktu";

    public DatabaseHelper(Context context) {
        super(context, NAMA_DB, null, VERSI_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABEL + " ("
                + KOLOM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KOLOM_JUDUL + " TEXT, "
                + KOLOM_ISI + " TEXT, "
                + KOLOM_WAKTU + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versiLama, int versiBaru) {
        db.execSQL("DROP TABLE IF EXISTS " + TABEL);
        onCreate(db);
    }

    public long tambahCatatan(String judul, String isi, String waktu) {
        ContentValues nilai = new ContentValues();
        nilai.put(KOLOM_JUDUL, judul);
        nilai.put(KOLOM_ISI, isi);
        nilai.put(KOLOM_WAKTU, waktu);
        return getWritableDatabase().insert(TABEL, null, nilai);
    }

    /** Catatan terbaru ada di paling atas. */
    public List<Catatan> ambilSemua() {
        List<Catatan> daftar = new ArrayList<>();
        Cursor c = getReadableDatabase().query(TABEL, null, null, null,
                null, null, KOLOM_ID + " DESC");
        try {
            while (c.moveToNext()) {
                daftar.add(new Catatan(
                        c.getLong(c.getColumnIndexOrThrow(KOLOM_ID)),
                        c.getString(c.getColumnIndexOrThrow(KOLOM_JUDUL)),
                        c.getString(c.getColumnIndexOrThrow(KOLOM_ISI)),
                        c.getString(c.getColumnIndexOrThrow(KOLOM_WAKTU))));
            }
        } finally {
            c.close();
        }
        return daftar;
    }

    public void hapusCatatan(long id) {
        getWritableDatabase().delete(TABEL, KOLOM_ID + " = ?",
                new String[]{String.valueOf(id)});
    }
}
