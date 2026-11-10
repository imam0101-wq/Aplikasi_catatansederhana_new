package com.umko.catatan;

public class Catatan {

    private final long id;
    private final String judul;
    private final String isi;
    private final String waktu;

    public Catatan(long id, String judul, String isi, String waktu) {
        this.id = id;
        this.judul = judul;
        this.isi = isi;
        this.waktu = waktu;
    }

    public long getId() {
        return id;
    }

    public String getJudul() {
        return judul;
    }

    public String getIsi() {
        return isi;
    }

    public String getWaktu() {
        return waktu;
    }
}
