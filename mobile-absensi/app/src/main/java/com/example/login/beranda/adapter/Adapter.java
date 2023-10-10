package com.example.login.beranda.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login.R;
import com.example.login.beranda.activity.Beranda;
import com.example.login.beranda.model.MyJadwal;

import java.util.HashMap;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.HolderData>{

    List<String> lisDatas;

    public Adapter(Context context, List<String> lisDatas) {
        this.lisDatas = lisDatas;
        this.inflater = LayoutInflater.from(context);

    }

    LayoutInflater inflater;

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_home,parent,false);
        return new HolderData(view);

    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        ImageView  a = holder.itemView.findViewById(R.id.iconIm);

        String data = lisDatas.get(position);
//        holder.binData(data,position);
        String[] splitData = data.split(","); // Ganti dengan pemisah yang sesuai antara elemen-elemen data

        holder.nama.setText(splitData[0]);
        holder.shift.setText(splitData[1]);
        holder.tanggal.setText(splitData[2]);
        holder.status.setText(splitData[3]);
        holder.no.setText(splitData[4]);
//        holder.no.setText(splitData[4]);
        String status = splitData[4];
        if (status.equals("0")){
            a.setImageResource(R.drawable.baseline_close_24);

        } else {
            a.setImageResource(R.drawable.baseline_check_circle_24);
        }

        for (int i = 0; i> splitData.length;i++){
            holder.no.setText(i);
        }

    }

    @Override
    public int getItemCount() {
        return lisDatas.size();
    }

    public class HolderData extends RecyclerView.ViewHolder{
        TextView no,nira,nama,shift,tanggal,status;
        ImageView a,h;
        public HolderData(@NonNull View itemView) {
            super(itemView);
            no = itemView.findViewById(R.id.nomor);
//            nira = itemView.findViewById(R.id.nira_id);
            nama = itemView.findViewById(R.id.nama_id);
            shift = itemView.findViewById(R.id.shift_id);
                tanggal = itemView.findViewById(R.id.tanggal_id);
            status = itemView.findViewById(R.id.status_id);


        }
//        public void binData(String data, int position){
////            no.setText(String.valueOf(position + 1));
//            nama.setText(data);
//            shift.setText(data);
//            tanggal.setText(data);
//            status.setText(data);
//        }

    }
}
