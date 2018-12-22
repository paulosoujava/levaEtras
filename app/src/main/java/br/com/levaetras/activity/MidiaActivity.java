package br.com.levaetras.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import br.com.levaetras.R;
import br.com.levaetras.model.Midia;
import br.com.levaetras.model.Place;

public class MidiaActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout mBack;
    private EditText mFace;
    private EditText mInst;
    private EditText mYou;
    private EditText mSite;
    private Button mCreate;
    private Place mPlace;
    private Midia mMidia;
    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_midia);
        initView();

    }

    @Override
    protected void onStart() {
        super.onStart();
        mIntent = getIntent();
        if( mIntent != null ){
            mPlace = mIntent.getParcelableExtra(Place.PLACE_KEY);
            setData();
        }else{
            Toast.makeText(this, R.string.fail_intent, Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void setData() {
        mMidia = mPlace.getMidia();

        if( mMidia == null ) mMidia = new Midia();

        mFace.setText( mMidia.getFacebook());
        mInst.setText( mMidia.getInstagram());
        mSite.setText( mMidia.getSite());
        mYou.setText( mMidia.getYoutube());
    }

    private void initView() {
        mBack = (LinearLayout) findViewById(R.id.back);
        mBack.setOnClickListener(this);
        mFace = (EditText) findViewById(R.id.face);
        mInst = (EditText) findViewById(R.id.inst);
        mYou = (EditText) findViewById(R.id.you);
        mSite = (EditText) findViewById(R.id.site);
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
        String face = mFace.getText().toString();
        String inst = mInst.getText().toString();
        String site = mSite.getText().toString();
        String you  = mYou.getText().toString();

        boolean is_error = ( face.isEmpty() || inst.isEmpty() || site.isEmpty() || you.isEmpty() );
        if( is_error ){
            Toast.makeText(this, getString(R.string.data_empty), Toast.LENGTH_SHORT).show();
        }else{

            mMidia.setFacebook(face);
            mMidia.setInstagram(inst);
            mMidia.setSite(site);
            mMidia.setYoutube(you);
            mPlace.setMidia( mMidia );
            Place.savePlace(this,mPlace);
            Log.e("LOG", "MIDIA "+ mPlace.getMidia().toString());
            Toast.makeText(this, getString(R.string.success_action), Toast.LENGTH_SHORT).show();
        }
    }
}
