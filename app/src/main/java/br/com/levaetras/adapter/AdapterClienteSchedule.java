package br.com.levaetras.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import br.com.levaetras.R;
import br.com.levaetras.activity.ChatActivity;
import br.com.levaetras.model.Client;
import br.com.levaetras.model.Employee;
import br.com.levaetras.model.Schedule;
import br.com.levaetras.utils.Consts;

public class AdapterClienteSchedule extends RecyclerView.Adapter<AdapterClienteSchedule.myViewHolder> implements View.OnClickListener {

    Context mContext;
    Schedule mSchedule;
    Client mClient;
    String mConfirmation;

    public AdapterClienteSchedule(Schedule schedule, Context context, String confirmation) {
        this.mContext = context;
        this.mSchedule = schedule;
        this.mConfirmation = confirmation;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return new myViewHolder(inflater.inflate(R.layout.item_employee, parent, false));

    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {

        mClient = mSchedule.getClients().get(position);
        holder.name.setText(mClient.getName());

        holder.status.setText("12/02 13:30 Hs");

        //clicks
        holder.chat.setOnClickListener(this);
        holder.email.setOnClickListener(this);
        holder.phone.setOnClickListener(this);
        holder.delete.setVisibility(View.GONE);
        holder.container_start.setOnClickListener(this);
        Picasso.get()
                .load(mClient.getPhoto())
                .placeholder(R.drawable.no_photo)
                .error(R.drawable.no_photo)
                .into(holder.photo);
        Log.e("LOG", mClient.getStatus() );
        if (mConfirmation.equals(Consts.CONFIRM)) {
            holder.icon.setImageResource(R.drawable.ic_check_white_24dp);
        } else {
            holder.icon.setImageResource(R.drawable.ic_delete_white_24dp);

        }


    }


    @Override
    public int getItemCount() {
        return mSchedule.getClients() == null ? 0 : mSchedule.getClients().size();
    }

    @Override
    public void onClick(View view) {

        Intent it;

        switch (view.getId()) {
            case R.id.email:
                it = new Intent(Intent.ACTION_SEND);
                it.setType("text/html");
                it.putExtra(Intent.EXTRA_EMAIL, mClient.getEmail());
                it.putExtra(Intent.EXTRA_SUBJECT, "Assunto");
                it.putExtra(Intent.EXTRA_TEXT, "Escreva aqui...");
                mContext.startActivity(Intent.createChooser(it, "Enviar Mensagem"));
                break;
            case R.id.chat:
                it = new Intent(mContext, ChatActivity.class);
                it.putExtra(Employee.KEY_EMPLOYEE, mClient);
                mContext.startActivity(it);
                break;
            case R.id.phone:
                it = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mClient.getPhone()));
                mContext.startActivity(it);
                break;
            case R.id.container_start:
                if (mClient.getStatus().equals(Consts.CONFIRMED)) {
                    showWhat(Consts.CONFIRM);
                } else {
                    showWhat(Consts.CANCEL);
                }
                break;
        }

    }

    private void showWhat(String what) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
        alertDialogBuilder.setMessage("Você deseja realmente "+ what);
        alertDialogBuilder.setPositiveButton(mContext.getString(R.string.yep),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        alertDialogBuilder.setNegativeButton(mContext.getString(R.string.nope), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(mContext, R.string.toas_ok, Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
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
            icon = v.findViewById(R.id.icon);
            photo = v.findViewById(R.id.photo);
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
}
