package br.com.levaetras.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import br.com.levaetras.R;
import br.com.levaetras.model.Address;
import br.com.levaetras.model.Place;
import br.com.levaetras.utils.MaskEditUtil;

public class AddressActivity extends AppCompatActivity implements View.OnClickListener {

    private Intent mIntent;
    private Place mPlace;
    private LinearLayout mBack;
    private EditText mAddress;
    private EditText mNumber;
    private EditText mNeib;
    private EditText mZip;
    private EditText mCity;
    private EditText mState;
    private Button mCreate;
    private Address mAddressPlace;
    private EditText mAddressReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        initView();


    }

    @Override
    protected void onStart() {
        super.onStart();
        mIntent = getIntent();
        if (mIntent != null) {
            mPlace = mIntent.getParcelableExtra(Place.PLACE_KEY);
            Log.e("LOG", mPlace.toString());
            if( mPlace != null && mPlace.getAddress() != null ){
                setData();
            }
        } else {
            Toast.makeText(this, R.string.fail_intent, Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void setData() {
        mAddress.setText(mPlace.getAddress().getAddress());
        mNumber.setText(mPlace.getAddress().getNumber());
        mNeib.setText(mPlace.getAddress().getNeighborhood());
        mCity.setText(mPlace.getAddress().getCity());
        mState.setText(mPlace.getAddress().getState());
        mZip.setText(mPlace.getAddress().getZip());
        mAddressReference.setText(mPlace.getAddress().getReference());
    }

    private void initView() {
        mBack = (LinearLayout) findViewById(R.id.back);
        mBack.setOnClickListener(this);
        mAddress = (EditText) findViewById(R.id.address);
        mNumber = (EditText) findViewById(R.id.number);
        mNeib = (EditText) findViewById(R.id.neib);
        mZip = (EditText) findViewById(R.id.zip);
        mZip.addTextChangedListener(MaskEditUtil.mask(mZip, MaskEditUtil.FORMAT_CEP));
        mCity = (EditText) findViewById(R.id.city);
        mState = (EditText) findViewById(R.id.state);
        mCreate = (Button) findViewById(R.id.create);
        mCreate.setOnClickListener(this);
        mAddressReference = (EditText) findViewById(R.id.reference_address);
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
        mAddressPlace = mPlace.getAddress();
        if (mAddressPlace == null)
            mAddressPlace = new Address();

        String zip = mZip.getText().toString();
        String address = mAddress.getText().toString();
        String nei = mNeib.getText().toString();
        String city = mCity.getText().toString();
        String number = mNumber.getText().toString();
        String reference = mAddressReference.getText().toString();
        String state = mState.getText().toString();

        boolean is_error = (zip.isEmpty() || address.isEmpty() || nei.isEmpty() || city.isEmpty() || state.isEmpty());
        if (is_error) {
            Toast.makeText(this, getString(R.string.ops_set_all_data), Toast.LENGTH_SHORT).show();
        } else {
            mAddressPlace.setState(state);
            mAddressPlace.setZip(zip);
            mAddressPlace.setNumber(number);
            mAddressPlace.setNeighborhood(nei);
            mAddressPlace.setCity(city);
            mAddressPlace.setAddress(address);
            mAddressPlace.setReference(reference);
            mPlace.setAddress( mAddressPlace );
            Place.savePlace(this, mPlace);
            Toast.makeText(this, getString(R.string.success_action), Toast.LENGTH_SHORT).show();
        }

    }
}
