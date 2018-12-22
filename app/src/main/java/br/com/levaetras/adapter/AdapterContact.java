package br.com.levaetras.adapter;

import android.content.Context;
import android.content.Intent;
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
import br.com.levaetras.model.Client;
import br.com.levaetras.model.Contact;
import br.com.levaetras.model.Schedule;

public class AdapterContact extends RecyclerView.Adapter<AdapterContact.myViewHolder> implements View.OnClickListener {

    Context mContext;
    List<Contact> mList;
    Contact mContact;



    public AdapterContact(List<Contact> contacts, Context context) {
        this.mContext = context;
        this.mList = contacts;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return new myViewHolder(inflater.inflate(R.layout.item_contact, parent, false));

    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {

        mContact = mList.get(position);
        holder.name.setText(mContact.getName());
        holder.status.setText(mContact.getDate_hour());

        Picasso.get()
                .load(mContact.getUrl_photo())
                .placeholder(R.drawable.no_photo)
                .error(R.drawable.no_photo)
                .into(holder.photo);

    }




    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {


        }

    }


    private void myToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        ImageView photo, delete, chat, phone, email;
        TextView name, status;


        public myViewHolder(View v) {
            super(v);
            photo = v.findViewById(R.id.photo);
            name = v.findViewById(R.id.name);
            status = v.findViewById(R.id.status);
            delete = v.findViewById(R.id.delete);
            chat = v.findViewById(R.id.chat);
            phone = v.findViewById(R.id.phone);
            phone = v.findViewById(R.id.phone);
            email = v.findViewById(R.id.email);
        }
    }
}
