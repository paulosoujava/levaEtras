package br.com.levaetras.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.com.levaetras.R;
import br.com.levaetras.model.Schedule;
import br.com.levaetras.model.Zip;

public class AdapterSchedule extends RecyclerView.Adapter<AdapterSchedule.myViewHolder> implements View.OnClickListener {

    Context mContext;
    List<Schedule> mList;
    Schedule mSchedule ;



    public AdapterSchedule(List<Schedule> schedules, Context context) {
        this.mContext = context;
        this.mList = schedules;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return new myViewHolder(inflater.inflate(R.layout.item_schedule, parent, false));

    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {

        mSchedule = mList.get(position);
        holder.day.setText(mSchedule.getDay());
        holder.month.setText(mSchedule.getMonth());
        holder.qtd.setText(" "+ mSchedule.getQdtdVacancy());
        holder.desc.setText(mSchedule.getDesc());

  //clicks
        holder.container.setOnClickListener(this);
    }


    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
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

        CardView container;
        TextView month, desc, day, qtd;



        public myViewHolder(View v) {
            super(v);
            container = v.findViewById(R.id.container);
            desc = v.findViewById(R.id.desc);
            month = v.findViewById(R.id.month);
            day = v.findViewById(R.id.day);
            qtd = v.findViewById(R.id.qtd);
        }
    }
}
