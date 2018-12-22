package br.com.levaetras.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import br.com.levaetras.R;
import br.com.levaetras.adapter.ItemMenuAdapter;
import br.com.levaetras.fragment.HomeFragment;
import br.com.levaetras.fragment.PlaceProfileFragment;
import br.com.levaetras.fragment.PlacesZipFragment;
import br.com.levaetras.model.Adm;
import br.com.levaetras.utils.EmailValidator;
import br.com.levaetras.utils.MaskEditUtil;
import br.com.levaetras.utils.ValidateCpf;
import nl.psdcompany.duonavigationdrawer.views.DuoDrawerLayout;
import nl.psdcompany.duonavigationdrawer.views.DuoMenuView;
import nl.psdcompany.duonavigationdrawer.widgets.DuoDrawerToggle;


public class MainActivity extends AppCompatActivity implements DuoMenuView.OnMenuClickListener {
    private ItemMenuAdapter mMenuAdapter;
    private ViewHolder mViewHolder;
    private Dialog myDialog;
    private ArrayList<String> mTitles = new ArrayList<>();
    private Adm mAdm;
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTitles = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.menuOptions)));

        // Initialize the views
        mViewHolder = new ViewHolder();

        // Handle toolbar actions
        handleToolbar();

        // Handle menu actions
        handleMenu();

        // Handle drawer actions
        handleDrawer();

        // Show main fragment in container
        goToFragment(new HomeFragment(), false);
        mMenuAdapter.setViewSelected(0, true);
        setTitle(mTitles.get(0));

        myDialog = new Dialog(this);
        mContext = MainActivity.this;

    }

    private void handleToolbar() {
        setSupportActionBar(mViewHolder.mToolbar);
    }

    private void handleDrawer() {
        DuoDrawerToggle duoDrawerToggle = new DuoDrawerToggle(this,
                mViewHolder.mDuoDrawerLayout,
                mViewHolder.mToolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        mViewHolder.mDuoDrawerLayout.setDrawerListener(duoDrawerToggle);
        duoDrawerToggle.syncState();

    }

    private void handleMenu() {
        mMenuAdapter = new ItemMenuAdapter(mTitles);

        mViewHolder.mDuoMenuView.setOnMenuClickListener(this);
        mViewHolder.mDuoMenuView.setAdapter(mMenuAdapter);
    }

    @Override
    public void onFooterClicked() {
        exit();
    }

    @Override
    public void onHeaderClicked() {
        showPopupAdm();
    }

    private void goToFragment(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (addToBackStack) {
            transaction.addToBackStack(null);
        }

        transaction.replace(R.id.container, fragment).commit();
    }

    @Override
    public void onOptionClicked(int position, Object objectClicked) {
        // Set the toolbar title
        setTitle(mTitles.get(position));

        // Set the right options selected
        mMenuAdapter.setViewSelected(position, true);

        // Navigate to the right fragment
        switch (position) {
            case 1:
                goToFragment(new PlaceProfileFragment(), false);
                break;
            case 2:
                goToFragment(new PlacesZipFragment(), false);
                break;
            case 3:
                startActivity(new Intent(this, AboutUsActivity.class));
                break;
            default:
                goToFragment(new HomeFragment(), false);
                break;

        }

        // Close the drawer
        mViewHolder.mDuoDrawerLayout.closeDrawer();
    }

    private class ViewHolder {
        private DuoDrawerLayout mDuoDrawerLayout;
        private DuoMenuView mDuoMenuView;
        private Toolbar mToolbar;

        ViewHolder() {
            mDuoDrawerLayout = findViewById(R.id.drawer);
            mDuoMenuView = (DuoMenuView) mDuoDrawerLayout.getMenuView();
            mToolbar = findViewById(R.id.toolbar);
        }
    }

    public void showPopupAdm() {

        Button create;
        final TextView close;
        final EditText name, email, phone, cpf;


        //layout
        myDialog.setContentView(R.layout.pop_up_adm);

        //binds
        close = myDialog.findViewById(R.id.close);
        create = myDialog.findViewById(R.id.create);
        name = myDialog.findViewById(R.id.name);
        cpf = myDialog.findViewById(R.id.cpf);
        email = myDialog.findViewById(R.id.email);
        phone = myDialog.findViewById(R.id.phone);

        cpf.addTextChangedListener(MaskEditUtil.mask(cpf, MaskEditUtil.FORMAT_CPF));
        phone.addTextChangedListener(MaskEditUtil.mask(phone, MaskEditUtil.FORMAT_FONE));

        //set
        close.setText("X");
        //set data
        mAdm = Adm.getAdm(mContext);
        if (mAdm != null) {
            cpf.setText( mAdm.getCpf());
            email.setText(mAdm.getEmail());
            phone.setText(mAdm.getPhone());
            name.setText(mAdm.getName());
        } else {
            mAdm = new Adm();
        }


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

                mAdm.setCpf(cpf.getText().toString());
                mAdm.setEmail(email.getText().toString());
                mAdm.setName(name.getText().toString());
                mAdm.setPhone(phone.getText().toString());
                if (validate()) {
                    Adm.saveAdm(mContext, mAdm);
                    Toast.makeText(mContext, R.string.success_action, Toast.LENGTH_SHORT).show();
                }
            }
        });

        //transparent
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //show
        myDialog.show();
    }

    private void exit() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(R.string.title_exit);
        alertDialogBuilder.setPositiveButton(getString(R.string.yep),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        alertDialogBuilder.setNegativeButton(getString(R.string.nope), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private boolean validate() {

        boolean is_error;
        EmailValidator em = new EmailValidator();

        is_error = (mAdm.getCpf() == null || mAdm.getCpf().isEmpty() ||
                mAdm.getEmail() == null || mAdm.getEmail().isEmpty() ||
                mAdm.getName() == null || mAdm.getName().isEmpty() ||
                mAdm.getCpf() == null || mAdm.getCpf().isEmpty()
        );
        if (is_error) {
            Toast.makeText(this, R.string.ops_set_all_data, Toast.LENGTH_SHORT).show();
            return false;
        } else if (!em.validate(mAdm.getEmail())) {
            Toast.makeText(this, R.string.ops_email_error, Toast.LENGTH_SHORT).show();
            return false;
        } else if (ValidateCpf.isCPF(mAdm.getCpf())) {
            Toast.makeText(this, R.string.ops_cpf_error, Toast.LENGTH_SHORT).show();
            return false;
        } else
            return true;
    }
}
