package br.com.levaetras.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import br.com.levaetras.R;
import br.com.levaetras.adapter.AdapterClienteSchedule;
import br.com.levaetras.service.ScheduleService;
import br.com.levaetras.utils.Consts;

public class ClientActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout mBack;
    private LinearLayout mTitle;
    private RecyclerView mRecycler;
    private AdapterClienteSchedule mAdaper;
    private String mConfirmation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        initView();

        Intent it = getIntent();
        if( it != null ){
            mConfirmation = it.getStringExtra(Consts.CONFIRM);
            initAdapter();
        }else{
            Toast.makeText(this, "Missing data", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void initView() {
        mBack = findViewById(R.id.back);
        mBack.setOnClickListener(this);
        mTitle = findViewById(R.id.title);
        mRecycler = findViewById(R.id.recycler);
    }

    private void initAdapter() {
        mAdaper = new AdapterClienteSchedule(ScheduleService.getScheduleNotConfirmed(), this, mConfirmation);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecycler.setLayoutManager(layout);
        mRecycler.setAdapter(mAdaper);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
        }
    }
}
