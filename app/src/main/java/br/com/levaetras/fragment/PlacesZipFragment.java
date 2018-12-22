package br.com.levaetras.fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import br.com.levaetras.R;
import br.com.levaetras.adapter.AdapterZip;
import br.com.levaetras.model.Zip;
import br.com.levaetras.service.ZipService;
import br.com.levaetras.utils.MaskEditUtil;


public class PlacesZipFragment extends Fragment implements View.OnClickListener {

    private Dialog myDialog;
    private LinearLayout mAdd;
    private RecyclerView mRecycler;
    private AdapterZip mAdaper;
    private TextView mWarning;

    public PlacesZipFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_places_zip, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myDialog = new Dialog(getActivity());
        initView(getActivity());
        initAdapter();
    }

    private void initView(@NonNull final FragmentActivity itemView) {

        mAdd = (LinearLayout) itemView.findViewById(R.id.add);
        mAdd.setOnClickListener(this);
        mRecycler = (RecyclerView) itemView.findViewById(R.id.recycler);
        mWarning = (TextView) itemView.findViewById(R.id.warning);
    }

    private void initAdapter() {
        List<Zip> mainList = new ArrayList<>();
        if( Zip.getZip(getActivity()) != null )
            mainList.addAll(Zip.getZip(getActivity()));
        if(mainList == null || mainList.size() == 0 ){
            mWarning.setVisibility(View.VISIBLE);
        }else{
            mWarning.setVisibility(View.GONE);
            mAdaper = new AdapterZip(mainList, getActivity());
            RecyclerView.LayoutManager layout = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            mRecycler.setLayoutManager(layout);
            mRecycler.setAdapter(mAdaper);
        }

    }

    public void showPopupZip() {

        //var
        final EditText zip;
        Button create;
        TextView close;


        //layout
        myDialog.setContentView(R.layout.pop_up_zip);

        //binds
        close = myDialog.findViewById(R.id.close);
        zip = myDialog.findViewById(R.id.zip);
        zip.addTextChangedListener(MaskEditUtil.mask(zip, MaskEditUtil.FORMAT_CEP));
        create = myDialog.findViewById(R.id.create);


        //set
        close.setText("X");

        //clicks
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (zip.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), R.string.waring_zip, Toast.LENGTH_SHORT).show();
                    Zip.saveZip(getActivity(), new Zip(zip.getText().toString()));
                    zip.setText("");
                    mAdaper.notifyDataSetChanged();
                } else {
                    Toast.makeText(getActivity(), getString(R.string.success_action), Toast.LENGTH_SHORT).show();
                }
            }
        });

        //transparent
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //show
        myDialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                showPopupZip();
                break;
        }
    }
}
