package br.com.levaetras.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import br.com.levaetras.R;
import br.com.levaetras.model.Place;
import br.com.levaetras.utils.EmailValidator;
import br.com.levaetras.utils.MaskEditUtil;

public class ContactActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout mBack;
    private TextView mName;
    private EditText mEmail;
    private EditText mPhoneCel;
    private EditText mPhonePhoneFix;
    private Button mCreate;
    private Intent mIntent;
    private Place mPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mIntent = getIntent();
        if( mIntent != null ){
            mPlace = mIntent.getParcelableExtra(Place.PLACE_KEY);
            mPhoneCel.setText(mPlace.getPhoneCel());
            mPhonePhoneFix.setText(mPlace.getPhoneRes());
            mEmail.setText(mPlace.getEmail());

        }else{
            Toast.makeText(this, getString(R.string.fail_intent), Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void initView() {
        mBack = (LinearLayout) findViewById(R.id.back);
        mBack.setOnClickListener(this);
        mName = (TextView) findViewById(R.id.name);
        mEmail = (EditText) findViewById(R.id.email);
        mPhoneCel = (EditText) findViewById(R.id.phoneCel);
        mPhoneCel.addTextChangedListener(MaskEditUtil.mask(mPhoneCel, MaskEditUtil.FORMAT_FONE));
        mPhonePhoneFix = (EditText) findViewById(R.id.phonePhoneFix);
        mPhonePhoneFix.addTextChangedListener(MaskEditUtil.mask(mPhonePhoneFix, MaskEditUtil.FORMAT_FONE));
        mCreate = (Button) findViewById(R.id.create);
        mCreate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.create:
                validate();
                break;
        }
    }

    private void validate() {
        String email = mEmail.getText().toString();
        String phoneCel = mPhoneCel.getText().toString();
        String phoneFix = mPhonePhoneFix.getText().toString();
        EmailValidator em = new EmailValidator();
        if( email.isEmpty() || phoneCel.isEmpty() || phoneFix.isEmpty() ){
            Toast.makeText(this, getString(R.string.data_empty), Toast.LENGTH_SHORT).show();
        }else{
            if(!em.validate(email)){
                Toast.makeText(this, getString(R.string.ops_email_error), Toast.LENGTH_SHORT).show();
            }else{
                mPlace.setPhoneCel(phoneCel);
                mPlace.setPhoneRes(phoneFix);
                mPlace.setEmail(email);
                Place.savePlace(this, mPlace);
                Toast.makeText(this, getString(R.string.success_action), Toast.LENGTH_SHORT).show();
            }

        }
    }


}
