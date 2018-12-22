package br.com.levaetras.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.levaetras.R;
import br.com.levaetras.model.Client;
import br.com.levaetras.model.Schedule;

public class AdapterAllClient extends RecyclerView.Adapter<AdapterAllClient.myViewHolder> implements View.OnClickListener {

    Context mContext;
    List<Client> mListClient;
    Client mClient;
    Dialog myDialog;


    public AdapterAllClient(List<Client> clients, Context context) {
        this.mContext = context;
        this.mListClient = clients;
        myDialog = new Dialog(context);
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return new myViewHolder(inflater.inflate(R.layout.item_employee, parent, false));

    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {

        mClient = mListClient.get(position);
        holder.status.setTypeface(null, Typeface.BOLD);
        holder.status.setText(mClient.getName());

        //clicks
        holder.chat.setOnClickListener(this);
        holder.email.setOnClickListener(this);
        holder.phone.setOnClickListener(this);
        holder.delete.setVisibility(View.GONE);
        holder.container_start.setOnClickListener(this);
        holder.icon.setImageResource(R.drawable.ic_add_shopping_cart_black_24dp);

        Picasso.get()
                .load(mClient.getPhoto())
                .placeholder(R.drawable.no_photo)
                .error(R.drawable.no_photo)
                .into(holder.photo);

    }


    @Override
    public int getItemCount() {
        return mListClient == null ? 0 : mListClient.size();
    }

    @Override
    public void onClick(View view) {

        Intent it;
        switch (view.getId()) {
            case R.id.container_start: showPopup(); break;

        }

    }


    private void myToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        ImageView photo, delete, chat, phone, email, icon;
        TextView name, status;
        LinearLayout container_start;


        public myViewHolder(View v) {
            super(v);
            photo = v.findViewById(R.id.photo);
            icon = v.findViewById(R.id.icon);
            name = v.findViewById(R.id.name);
            status = v.findViewById(R.id.status);
            delete = v.findViewById(R.id.delete);
            chat = v.findViewById(R.id.chat);
            phone = v.findViewById(R.id.phone);
            phone = v.findViewById(R.id.phone);
            email = v.findViewById(R.id.email);
            container_start = v.findViewById(R.id.container_start);
        }
    }

    public void showPopup() {

        //var
        final EditText zip;
        Button create, cancel;
        TextView close;


        //layout
        myDialog.setContentView(R.layout.pop_up_notification);

        //binds
        close = myDialog.findViewById(R.id.close);
        create = myDialog.findViewById(R.id.create);
        cancel = myDialog.findViewById(R.id.cancel);

        //set
        close.setText("X");

        //clicks
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myDialog.dismiss();

            }
        });

        //transparent
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //show
        myDialog.show();
    }
}
