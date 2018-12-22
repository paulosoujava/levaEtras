package br.com.levaetras.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.levaetras.R;
import br.com.levaetras.activity.ChatActivity;
import br.com.levaetras.model.Client;
import br.com.levaetras.model.Employee;
import br.com.levaetras.model.Schedule;
import br.com.levaetras.utils.Consts;

public class AdapterClient extends RecyclerView.Adapter<AdapterClient.myViewHolder> implements View.OnClickListener {

    Context mContext;
    List<Client> mListClient;
    Client mClient;
    Schedule mSchedule;


    public AdapterClient( Schedule schedule, Context context) {
        this.mContext = context;
        this.mListClient = schedule.getClients();
        this.mSchedule = schedule;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return new myViewHolder(inflater.inflate(R.layout.item_client, parent, false));

    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {

        mClient = mListClient.get(position);
        holder.name_client.setText(mClient.getName());
        holder.day_client.setText(String.format("%s/%s", mSchedule.getDay(), mSchedule.getMonth()));
        holder.hour_client.setText(mSchedule.getHour());


        //clicks
        holder.container.setOnClickListener(this);

        Picasso.get()
                .load(mClient.getPhoto())
                .placeholder(R.drawable.no_photo)
                .error(R.drawable.no_photo)
                .into(holder.image);

    }




    @Override
    public int getItemCount() {
        return mListClient == null ? 0 : mListClient.size();
    }

    @Override
    public void onClick(View view) {

        Intent it;

        switch (view.getId()) {


        }

    }


    private void myToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name_client, hour_client, day_client;
        CardView container;


        public myViewHolder(View v) {
            super(v);
            container = v.findViewById(R.id.container);
            name_client = v.findViewById(R.id.name_client);
            hour_client = v.findViewById(R.id.hour_client);
            day_client = v.findViewById(R.id.day_client);
            image = v.findViewById(R.id.image);
        }
    }
}
