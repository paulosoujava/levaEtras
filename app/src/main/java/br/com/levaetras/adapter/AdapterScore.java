package br.com.levaetras.adapter;

import android.app.Dialog;
import android.content.Context;
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
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.com.levaetras.R;
import br.com.levaetras.model.Schedule;
import br.com.levaetras.model.Score;

public class AdapterScore extends RecyclerView.Adapter<AdapterScore.myViewHolder> implements View.OnClickListener {

    Context mContext;
    List<Score> mList;
    Score mScore;
    Dialog myDialog;


    public AdapterScore(List<Score> scores, Context context) {
        this.mContext = context;
        this.mList = scores;
        myDialog = new Dialog(context);
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return new myViewHolder(inflater.inflate(R.layout.item_score, parent, false));

    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {

        mScore = mList.get(position);
        holder.score.setText(mScore.getScore());
        holder.answer.setText(mScore.getAnswer());
        holder.desc.setText(mScore.getDesc());
        holder.name.setText(mScore.getNameClient());

  //clicks
        holder.container.setOnClickListener(this);
    }


    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.container:
                showPopup();
                break;
        }

    }


    private void myToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        CardView container;
        TextView name, desc, answer, score;



        public myViewHolder(View v) {
            super(v);
            container = v.findViewById(R.id.container);
            desc = v.findViewById(R.id.desc);
            score = v.findViewById(R.id.score);
            answer = v.findViewById(R.id.answer);
            name = v.findViewById(R.id.name);
        }
    }
    public void showPopup() {

        //var
        Button create, cancel;
        TextView close;



        //layout
        myDialog.setContentView(R.layout.pop_up_answer);

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
