package com.umko.catatan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CatatanAdapter extends RecyclerView.Adapter<CatatanAdapter.CatatanHolder> {

    public interface OnHapusListener {
        void onHapus(Catatan catatan);
    }

    private final List<Catatan> daftar = new ArrayList<>();
    private final OnHapusListener listener;

    public CatatanAdapter(OnHapusListener listener) {
        this.listener = listener;
    }

    public void setData(List<Catatan> dataBaru) {
        daftar.clear();
        daftar.addAll(dataBaru);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CatatanHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_catatan, parent, false);
        return new CatatanHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CatatanHolder holder, int position) {
        Catatan c = daftar.get(position);
        holder.tvJudul.setText(c.getJudul());
        holder.tvIsi.setText(c.getIsi());
        holder.tvWaktu.setText(c.getWaktu());

        // Hapus lewat tombol atau tekan lama
        holder.btnHapus.setOnClickListener(v -> listener.onHapus(c));
        holder.itemView.setOnLongClickListener(v -> {
            listener.onHapus(c);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return daftar.size();
    }

    static class CatatanHolder extends RecyclerView.ViewHolder {
        final TextView tvJudul;
        final TextView tvIsi;
        final TextView tvWaktu;
        final View btnHapus;

        CatatanHolder(@NonNull View itemView) {
            super(itemView);
            tvJudul = itemView.findViewById(R.id.tvJudul);
            tvIsi = itemView.findViewById(R.id.tvIsi);
            tvWaktu = itemView.findViewById(R.id.tvWaktu);
            btnHapus = itemView.findViewById(R.id.btnHapus);
        }
    }
}
