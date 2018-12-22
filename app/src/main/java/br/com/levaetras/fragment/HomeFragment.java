package br.com.levaetras.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;

import br.com.levaetras.R;
import br.com.levaetras.activity.AllClientActivity;
import br.com.levaetras.activity.ClientActivity;
import br.com.levaetras.activity.ContactClientOrEmployeeActivity;
import br.com.levaetras.activity.EmployeeActivity;
import br.com.levaetras.activity.ScheduleActivity;
import br.com.levaetras.activity.ScoreActivity;
import br.com.levaetras.adapter.AdapterClient;
import br.com.levaetras.model.Place;
import br.com.levaetras.service.ScheduleService;
import br.com.levaetras.utils.Consts;
import de.hdodenhof.circleimageview.CircleImageView;

public class HomeFragment extends Fragment implements View.OnClickListener {


    private LinearLayout mScoreContainer;
    private LinearLayout mScheduleContainer;
    private LinearLayout mClientContainer;
    private LinearLayout mEmployeeContainer;
    private LinearLayout mMessageContainer;
    private TextView mClientName;
    private TextView mClientHour;
    private TextView mClientDay;
    private CardView mConfirmScheduleContainer;
    private CardView mConfirmedScheduleContainer;
    private AdapterClient mAdaper;
    private RecyclerView mConfirmed;
    private RecyclerView mNot_confirmed;
    private ImageView mComfirmed;
    private ImageView mConfirmClick;
    private RecyclerView mNotConfirmedRecycler;
    private ImageView mConfirmedClick;
    private RecyclerView mConfirmedRecycler;
    private CircleImageView mImage;
    private Place mPlace;


    public HomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(getActivity());
        initConfirmedAdapter();
        initNotConfirmedAdapter();
        mPlace = Place.getPlace(getActivity());
        if( mPlace != null && mPlace.getPhotoLocal() != null )
            setImageInView();
    }

    private void initView(@NonNull final FragmentActivity itemView) {
        mScoreContainer = (LinearLayout) itemView.findViewById(R.id.container_score);
        mScoreContainer.setOnClickListener(this);
        mScheduleContainer = (LinearLayout) itemView.findViewById(R.id.container_schedule);
        mScheduleContainer.setOnClickListener(this);
        mClientContainer = (LinearLayout) itemView.findViewById(R.id.container_client);
        mClientContainer.setOnClickListener(this);
        mEmployeeContainer = (LinearLayout) itemView.findViewById(R.id.container_employee);
        mEmployeeContainer.setOnClickListener(this);
        mMessageContainer = (LinearLayout) itemView.findViewById(R.id.container_message);
        mMessageContainer.setOnClickListener(this);
        mClientName = (TextView) itemView.findViewById(R.id.name_client);
        mClientHour = (TextView) itemView.findViewById(R.id.hour_client);
        mClientDay = (TextView) itemView.findViewById(R.id.day_client);
        mConfirmClick = (ImageView) itemView.findViewById(R.id.click_confirm);
        mConfirmClick.setOnClickListener(this);
        mNotConfirmedRecycler = (RecyclerView) itemView.findViewById(R.id.recycler_not_confirmed);
        mConfirmedClick = (ImageView) itemView.findViewById(R.id.click_confirmed);
        mConfirmedClick.setOnClickListener(this);
        mConfirmedRecycler = (RecyclerView) itemView.findViewById(R.id.recycler_confirmed);
        mImage = (CircleImageView) itemView.findViewById(R.id.image);
    }


    private void initConfirmedAdapter() {
        mAdaper = new AdapterClient(ScheduleService.getScheduleConfirmed(), getActivity());
        RecyclerView.LayoutManager layout = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mConfirmedRecycler.setLayoutManager(layout);
        mConfirmedRecycler.setAdapter(mAdaper);
    }

    private void initNotConfirmedAdapter() {
        mAdaper = new AdapterClient(ScheduleService.getScheduleNotConfirmed(), getActivity());
        RecyclerView.LayoutManager layout = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mNotConfirmedRecycler.setLayoutManager(layout);
        mNotConfirmedRecycler.setAdapter(mAdaper);
    }

    @Override
    public void onClick(View v) {

        Intent it = new Intent(getActivity(), ClientActivity.class);

        switch (v.getId()) {
            case R.id.container_score:
                getActivity().startActivity(new Intent(getActivity(), ScoreActivity.class));
                break;
            case R.id.container_schedule:
                getActivity().startActivity(new Intent(getActivity(), ScheduleActivity.class));
                break;
            case R.id.container_client:
                getActivity().startActivity(new Intent(getActivity(), AllClientActivity.class));
                break;
            case R.id.container_employee:
                getActivity().startActivity(new Intent(getActivity(), EmployeeActivity.class));
                break;
            case R.id.container_message:
                getActivity().startActivity(new Intent(getActivity(), ContactClientOrEmployeeActivity.class));
                break;
            case R.id.click_confirm:
                it.putExtra(Consts.CONFIRM, Consts.CONFIRM);
                getActivity().startActivity(it);
                break;
            case R.id.click_confirmed:
                it.putExtra(Consts.CONFIRM, Consts.CONFIRMED);
                getActivity().startActivity(it);
                break;
        }
    }

    private void setImageInView() {
        Picasso.get()
                .load(mPlace.getPhotoLocal())
                .placeholder(R.drawable.no_photo)
                .error(R.drawable.no_photo)
                .into(mImage);
    }
}
