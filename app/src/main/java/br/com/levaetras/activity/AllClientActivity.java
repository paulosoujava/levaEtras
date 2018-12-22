package br.com.levaetras.activity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import br.com.levaetras.R;
import br.com.levaetras.adapter.AdapterAllClient;
import br.com.levaetras.service.ClientService;

public class AllClientActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout mBack;
    private LinearLayout mTitle;
    private RecyclerView mRecycler;
    private AdapterAllClient mAdaper;
    private Dialog myDialog;
    private LinearLayout mAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_client);
        initView();
        initAdapter();
        myDialog = new Dialog(this);
    }

    private void initView() {
        mBack = findViewById(R.id.back);
        mBack.setOnClickListener(this);
        mTitle = findViewById(R.id.title);
        mRecycler = findViewById(R.id.recycler);
        mAll = (LinearLayout) findViewById(R.id.all);
        mAll.setOnClickListener(this);
    }

    private void initAdapter() {
        mAdaper = new AdapterAllClient(ClientService.getClient(), this);
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
            case R.id.all:
                showPopup();
                break;
        }
    }

    public void showPopup() {

        //var
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
