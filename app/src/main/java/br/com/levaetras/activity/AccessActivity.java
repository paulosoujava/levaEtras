package br.com.levaetras.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import br.com.levaetras.R;

public class AccessActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout mBack;
    private LinearLayout mTitle;
    private EditText mEmail;
    private EditText mPass;
    private EditText mPassRep;
    private Button mCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access);
        initView();
    }

    private void initView() {
        mBack = (LinearLayout) findViewById(R.id.back);
        mBack.setOnClickListener(this);
        mTitle = (LinearLayout) findViewById(R.id.title);
        mEmail = (EditText) findViewById(R.id.email);
        mPass = (EditText) findViewById(R.id.pass);
        mPassRep = (EditText) findViewById(R.id.rep_pass);
        mCreate = (Button) findViewById(R.id.create);
        mCreate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create:

                break;
            case R.id.back:
                finish();
                break;
            default:
                break;
        }
    }
}
