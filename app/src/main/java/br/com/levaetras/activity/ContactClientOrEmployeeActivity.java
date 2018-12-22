package br.com.levaetras.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import br.com.levaetras.R;
import br.com.levaetras.adapter.AdapterContact;
import br.com.levaetras.service.ContactService;

public class ContactClientOrEmployeeActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout mBack;
    private LinearLayout mTitle;
    private RecyclerView mRecycler;
    private AdapterContact mAdaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_client_or_employee);
        initView();
        initAdapter();
    }

    private void initView() {
        mBack = (LinearLayout) findViewById(R.id.back);
        mBack.setOnClickListener(this);
        mTitle = (LinearLayout) findViewById(R.id.title);
        mRecycler = (RecyclerView) findViewById(R.id.recycler);
    }

     private void initAdapter() {
        mAdaper = new AdapterContact(ContactService.getContact(), this);
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
