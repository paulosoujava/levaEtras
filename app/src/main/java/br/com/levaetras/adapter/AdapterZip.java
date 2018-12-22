package br.com.levaetras.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Set;

import br.com.levaetras.R;
import br.com.levaetras.model.Schedule;
import br.com.levaetras.model.Zip;
import br.com.levaetras.utils.MaskEditUtil;

public class AdapterZip extends RecyclerView.Adapter<AdapterZip.myViewHolder> implements View.OnClickListener {

    private Context mContext;
    private  List<Zip> mListZip;
    private  Zip mZip;
    private Dialog myDialog;



    public AdapterZip(List<Zip> zips, Context context) {
        this.mContext = context;
        this.mListZip = zips;
        myDialog = new Dialog(mContext);
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return new myViewHolder(inflater.inflate(R.layout.item_zip, parent, false));

    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {

        mZip = mListZip.get(position);
        holder.zip.setText(mZip.getZip());
        holder.desc.setText(mZip.getAddess());

  //clicks
        holder.edit.setOnClickListener(this);
        holder.delete.setOnClickListener(this);
    }


    @Override
    public int getItemCount() {
        return mListZip == null ? 0 : mListZip.size();
    }

    @Override
    public void onClick(View view) {

        Intent it;

        switch (view.getId()) {
            case R.id.delete:
                popUpDelete();
                break;
            case R.id.edit:
                showPopupZip();
                break;

        }

    }


    private void myToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        ImageView delete, edit;
        TextView zip, desc;



        public myViewHolder(View v) {
            super(v);
            delete = v.findViewById(R.id.delete);
            edit = v.findViewById(R.id.edit);
            desc = v.findViewById(R.id.desc);
            zip = v.findViewById(R.id.zip);
        }
    }

    private void popUpDelete() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
        alertDialogBuilder.setMessage(R.string.title_exit);
        alertDialogBuilder.setPositiveButton(mContext.getString(R.string.yep),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Zip.removeZip(mContext, mZip);
                        myToast(mContext.getString(R.string.success_action));

                    }
                });

        alertDialogBuilder.setNegativeButton(mContext.getString(R.string.nope), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void showPopupZip() {

        //var
        final EditText zip;
        Button create;
        TextView close;


        //layout
        myDialog.setContentView(R.layout.pop_up_zip);

        //binds
        close = myDialog.findViewById(R.id.close);
        zip = myDialog.findViewById(R.id.zip);
        zip.addTextChangedListener(MaskEditUtil.mask(zip, MaskEditUtil.FORMAT_CEP));
        create = myDialog.findViewById(R.id.create);


        //set
        close.setText("X");
        zip.setText(mZip.getZip());

        //clicks
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( zip.getText().toString().isEmpty()){
                    Toast.makeText(mContext, R.string.waring_zip, Toast.LENGTH_SHORT).show();
                    Zip.saveZip(mContext, new Zip(zip.getText().toString()));
                    Zip.removeZip(mContext,mZip);
                    notifyDataSetChanged();

                }else{
                    Toast.makeText(mContext, mContext.getString(R.string.success_action), Toast.LENGTH_SHORT).show();
                }
            }
        });

        //transparent
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //show
        myDialog.show();
    }

}
