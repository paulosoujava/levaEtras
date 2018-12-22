package br.com.levaetras.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
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
import br.com.levaetras.activity.EmployeeActivity;
import br.com.levaetras.model.Employee;
import br.com.levaetras.utils.Consts;

public class AdapterEmployee extends RecyclerView.Adapter<AdapterEmployee.myViewHolder> implements View.OnClickListener {

    Context mContext;
    List<Employee> mListEmployee;
    Employee employee;


    public AdapterEmployee(List<Employee> list, Context context) {
        this.mContext = context;
        this.mListEmployee = list;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return new myViewHolder(inflater.inflate(R.layout.item_employee, parent, false));

    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {

        employee = mListEmployee.get(position);
        holder.name.setText(employee.getName());
        holder.status.setText(setStatus(employee));

        //clicks
        holder.chat.setOnClickListener(this);
        holder.email.setOnClickListener(this);
        holder.phone.setOnClickListener(this);
        holder.delete.setOnClickListener(this);
        holder.container_start.setOnClickListener(this);

        Picasso.get()
                .load(employee.getUrlPhoto())
                .placeholder(R.drawable.no_photo)
                .error(R.drawable.no_photo)
                .into(holder.photo);

    }

    @NonNull
    private String setStatus(Employee employee) {
        String status;
        switch (employee.getStatus()) {
            case Consts.STATUS_NOT_ACCESS:
                status = mContext.getString(R.string.status_not_access);
                break;
            case Consts.STATUS_PROFILE:
                status = mContext.getString(R.string.status_incompleto);
                break;
            case Consts.STATUS_START:
                status = mContext.getString(R.string.status_start);
                break;
            case Consts.STATUS_STOP:
                status = mContext.getString(R.string.status_stop);
                break;
            default:
                status = mContext.getString(R.string.status_error);
        }
        return status;
    }


    @Override
    public int getItemCount() {
        return mListEmployee == null ? 0 : mListEmployee.size();
    }

    @Override
    public void onClick(View view) {

        Intent it;

        switch (view.getId()) {
            case R.id.email:
                it = new Intent(Intent.ACTION_SEND);
                it.setType("text/html");
                it.putExtra(Intent.EXTRA_EMAIL, employee.getEmail());
                it.putExtra(Intent.EXTRA_SUBJECT, "Assunto");
                it.putExtra(Intent.EXTRA_TEXT, "Escreva aqui...");
                mContext.startActivity(Intent.createChooser(it, "Enviar Mensagem"));
                break;
            case R.id.chat:
                it = new Intent(mContext, ChatActivity.class);
                it.putExtra(Employee.KEY_EMPLOYEE, employee);
                mContext.startActivity(it);
                break;
            case R.id.phone:
                it = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + employee.getPhone()));
                mContext.startActivity(it);
                break;
            case R.id.delete:
                delete(employee.getName());
                break;
            case R.id.container_start:
                switch (employee.getStatus()) {
                    case Consts.STATUS_NOT_ACCESS:
                        myToast(String.format(mContext.getString(R.string.msg_not_access), employee.getName()));
                        break;
                    case Consts.STATUS_PROFILE:
                        myToast(String.format(mContext.getString(R.string.msg_not_completed), employee.getName()));
                        break;
                    case Consts.STATUS_START:
                        myToast(String.format(mContext.getString(R.string.msg_not_start), employee.getName()));
                        break;
                    case Consts.STATUS_STOP:
                        myToast(String.format(mContext.getString(R.string.msg_not_stop), employee.getName()));
                        break;

                }
                break;
        }

    }

    private void delete(String name) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
        alertDialogBuilder.setMessage(mContext.getString(R.string.msg_delete_employee) + name + "?");
        alertDialogBuilder.setPositiveButton(mContext.getString(R.string.yep) ,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        alertDialogBuilder.setNegativeButton(mContext.getString(R.string.nope) , new DialogInterface.OnClickListener() {
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

        ImageView photo, delete, chat, phone, email;
        TextView name, status;
        LinearLayout container_start;


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
            container_start = v.findViewById(R.id.container_start);
        }
    }
}
