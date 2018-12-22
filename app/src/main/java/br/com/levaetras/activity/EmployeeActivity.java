package br.com.levaetras.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import br.com.levaetras.R;
import br.com.levaetras.adapter.AdapterEmployee;
import br.com.levaetras.service.EmployeeService;

public class EmployeeActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout mBack;
    private LinearLayout mTitle;
    private LinearLayout mAdd;
    AdapterEmployee mAdaper;
    private RecyclerView mRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initAdapter();
    }

    private void initView() {
        mBack = findViewById(R.id.back);
        mBack.setOnClickListener(this);
        mTitle = findViewById(R.id.title);
        mAdd = findViewById(R.id.add);
        mAdd.setOnClickListener(this);

        mRecycler =  findViewById(R.id.recycler);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                startActivity(new Intent(this, AccessActivity.class));
                break;
            case R.id.back:
                finish();
                break;
        }
    }

    private void initAdapter() {
        mAdaper = new AdapterEmployee(EmployeeService.getEmployee(), this);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecycler.setLayoutManager(layout);
        mRecycler.setAdapter(mAdaper);
    }
}
