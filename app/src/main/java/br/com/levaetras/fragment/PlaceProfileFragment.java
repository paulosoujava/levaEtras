package br.com.levaetras.fragment;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;

import br.com.levaetras.R;
import br.com.levaetras.activity.AddressActivity;
import br.com.levaetras.activity.ContactActivity;
import br.com.levaetras.activity.MidiaActivity;
import br.com.levaetras.model.Place;
import br.com.levaetras.utils.Consts;
import br.com.levaetras.utils.MaskEditUtil;
import br.com.levaetras.utils.Utils;
import de.hdodenhof.circleimageview.CircleImageView;


public class PlaceProfileFragment extends Fragment implements View.OnClickListener {


    private ImageView mPhoto;
    private ImageView mAddress;
    private ImageView mContact;
    private ImageView mMidia;
    private EditText mName;
    private Button mSave;
    private Place mPlace;
    private EditText mCnpj;
    private EditText mPayment;
    private EditText mAbout;
    private File fileToUpload;
    Intent photoPickerIntent;
    Intent mIntent;
    private CircleImageView mPhotoPlace;


    public PlaceProfileFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_place_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(getActivity());

    }

    @Override
    public void onStart() {
        super.onStart();
        setData();
    }

    private void setData() {
        mPlace = Place.getPlace(getActivity());
        if (mPlace != null) {
            mName.setText(mPlace.getName());
            mAbout.setText(mPlace.getAbout());
            mCnpj.setText(mPlace.getCnpj());
            mPayment.setText(mPlace.getPayment());
            setImageInView();
        } else {
            mPlace = new Place();
        }
    }

    private void initView(@NonNull final FragmentActivity itemView) {
        mPhoto = (ImageView) itemView.findViewById(R.id.photo);
        mPhoto.setOnClickListener(this);
        mAddress = itemView.findViewById(R.id.address);
        mAddress.setOnClickListener(this);
        mContact = itemView.findViewById(R.id.contact);
        mContact.setOnClickListener(this);
        mMidia = itemView.findViewById(R.id.midia);
        mMidia.setOnClickListener(this);
        mName = (EditText) itemView.findViewById(R.id.name);
        mSave = (Button) itemView.findViewById(R.id.save);
        mSave.setOnClickListener(this);
        mCnpj = (EditText) itemView.findViewById(R.id.cnpj);
        mCnpj.addTextChangedListener(MaskEditUtil.mask(mCnpj, MaskEditUtil.FORMAT_CNPJ));
        mPayment = (EditText) itemView.findViewById(R.id.payment);
        mAbout = (EditText) itemView.findViewById(R.id.abut);
        mPhotoPlace =  itemView.findViewById(R.id.photoPlace);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.address:
                gotThere(AddressActivity.class);
                break;
            case R.id.contact:
                gotThere(ContactActivity.class);

                break;
            case R.id.midia:
                gotThere(MidiaActivity.class);
                break;
            case R.id.save:

                if (validate()) {
                    Place.savePlace(getActivity(), mPlace);
                    Toast.makeText(getActivity(), getString(R.string.success_action), Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.photo:
                photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, Consts.COD_PHOTO);
                break;
        }
    }

    private void gotThere(Class a) {
        mIntent = new Intent(getActivity(), a);
        mIntent.putExtra(Place.PLACE_KEY, mPlace);
        startActivity(mIntent);
    }

    private boolean validate() {

        String name = mName.getText().toString();
        String cnpj = mCnpj.getText().toString();
        String about = mAbout.getText().toString();
        String payment = mPayment.getText().toString();


        boolean is_error = (name.isEmpty() || cnpj.isEmpty() || about.isEmpty() || payment.isEmpty());
        if (is_error) {
            Toast.makeText(getActivity(), R.string.data_empty, Toast.LENGTH_SHORT).show();
            return false;
        } else {
            mPlace.setAbout(about);
            mPlace.setCnpj(cnpj);
            mPlace.setPayment(payment);
            mPlace.setName(name);
            if (mPlace.getPhotoLocal() == null) {
                Toast.makeText(getActivity(), R.string.data_empty_photo, Toast.LENGTH_SHORT).show();
                return false;
            } else {
                return true;
            }

        }
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        Log.e("LOG", "TESTE: " + resultCode + " " + reqCode);
        super.onActivityResult(reqCode, resultCode, data);
        if (reqCode == Consts.COD_PHOTO) {
            try {

                final Uri imageUri = data.getData();
                mPlace.setPhotoLocal(imageUri.toString());
                Place.savePlace(getActivity(),mPlace);
                Log.e("LOG", imageUri.toString());
                //final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                //final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);

                setImageInView();

                //mPhoto.setImageBitmap(selectedImage);
                fileToUpload = new File(new Utils().getUriRealPath(getActivity(), imageUri));

            } catch (OutOfMemoryError o) {
                Toast.makeText(getActivity(), getString(R.string.ops_error_photo) + o.getMessage(), Toast.LENGTH_LONG).show();
            }

        }
    }

    private void setImageInView() {
        Log.e("LOG", "PHOTO "+ mPlace.getPhotoLocal() );
        Picasso.get()
                .load(mPlace.getPhotoLocal())
                .placeholder(R.drawable.no_photo)
                .error(R.drawable.no_photo)
                .into(mPhotoPlace);
    }

    public static void sendImagetToServer(final File fileToUpload, final Context context, final String uid) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Ion.with(context)
//                        .load("POST", Consts.URL_IMAGE)
//                        .setMultipartFile("image", "image/jpeg", fileToUpload)
//                        .setMultipartParameter("uid", uid)
//                        .asString()
//                        .setCallback(new FutureCallback<String>() {
//                            @Override
//                            public void onCompleted(Exception e, String result) {
//                                if (e != null) {
//                                    Toast.makeText(context, "Ops, obtivemos este erro: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//                                } else if (result != null) {
//                                    User user = mUser;
//
//                                    user.setPhoto(Consts.URL_SHOW_IMAGE + result);
//                                    Log.e("LOG", user.getPhoto());
//                                    User.saveProfile(context, user);
//                                }
//                            }
//
//                        });
//            }
//        }).start();

    }
}
