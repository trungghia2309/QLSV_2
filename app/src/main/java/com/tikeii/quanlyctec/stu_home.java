package com.tikeii.quanlyctec;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.tikeii.quanlyctec.stu_fragment.frg_stu_changePW;
import com.tikeii.quanlyctec.stu_fragment.frg_stu_home;
import com.tikeii.quanlyctec.stu_fragment.frg_stu_info;
import com.tikeii.quanlyctec.stu_fragment.frg_stu_result;


public class stu_home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Button info_btn, result_btn,lichthi_btn;
    TextView stu_home_name;
    ctec_database db;
    DrawerLayout stu_draw;
    String stu_id;

    String mssv, hoten, gioitinh, ngaysinh, noisinh, diachi, shs, cccd, sdt, email,malop, msgv;

    private static final int Fragment_stu_home = 1;
    private static final int Fragment_stu_info = 2;
    private static final int Fragment_stu_result = 3;
    private static final int Fragment_stu_changePW = 4;

    private int currentFragment = Fragment_stu_home;

    private Toolbar supportActionBar;
    private DrawerLayout drawerLayout;





    @SuppressLint({"WrongViewCast", "ResourceType", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu_home);


        Toolbar stu_toolbar = findViewById(R.id.stu_home_toolbar);
        setSupportActionBar(stu_toolbar);
        stu_draw = findViewById(R.id.stu_home_drawerlayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, stu_draw ,stu_toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        stu_draw.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = findViewById(R.id.stu_home_Nav);
        navigationView.setNavigationItemSelectedListener(this);
        replaceLayout(new frg_stu_home());
        navigationView.getMenu().findItem(R.id.stu_nav_item_home).setChecked(true);


        stu_draw = findViewById(R.id.stu_home_drawerlayout);
        info_btn = findViewById(R.id.btn_info);
        result_btn = findViewById(R.id.btn_kqht);
        lichthi_btn = findViewById(R.id.btn_lt);
        stu_home_name = findViewById(R.id.stu_name);
        db = new ctec_database(this);
        Intent g_int = getIntent();
        stu_id = g_int.getStringExtra("MSSV_INPUT");
        Cursor cursor = db.getdataSTU_withID(stu_id);
        while (cursor.moveToNext()) {
            stu_home_name.setText(cursor.getString(3));
        }




        info_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(stu_home.this,stu_info.class);
                i.putExtra("MSSV_INPUT",stu_id);
                startActivity(i);
            }
        });

        lichthi_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), stu_lichthi_webview.class);
                startActivity(i);
            }
        });

        result_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultSTU_view(stu_id);
            }
        });

    }

    private void resultSTU_view(String id) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.stu_result_view);
        dialog.setCanceledOnTouchOutside(false);
        ctec_database db = new ctec_database(this);
        Button yes = dialog.findViewById(R.id.confirm_btn_yes);
        Button no = dialog.findViewById(R.id.confirm_btn_no);
        EditText mamon = dialog.findViewById(R.id.mamon_inputToview);


        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mmon = mamon.getText().toString();
                Intent intent = new Intent(getApplicationContext(), resultview.class);
                intent.putExtra("MSSV_INPUT",id);
                intent.putExtra("MAMON_INPUT", mmon);
                startActivity(intent);
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               dialog.cancel();
            }
        });

        dialog.show();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.stu_nav_item_home){
            if (Fragment_stu_home != currentFragment) {
                replaceLayout(new frg_stu_home());
                currentFragment = Fragment_stu_home;
            }
        }else if( id == R.id.stu_nav_item_info){
            if (Fragment_stu_info != currentFragment) {
                sendIn4tofrg(stu_id);
                currentFragment = Fragment_stu_info;

            }

        }else if( id == R.id.stu_nav_item_result){
            if (Fragment_stu_result != currentFragment) {
                sendResultToFrg(stu_id);
                currentFragment = Fragment_stu_result;
            }

        } else if ( id == R.id.stu_nav_item_changePW) {
            if (Fragment_stu_changePW != currentFragment) {
                changePW(stu_id);
                currentFragment = Fragment_stu_changePW;
            }

        } else if ( id == R.id.stu_nav_item_logout) {
                logout();
        }
        stu_draw.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (stu_draw.isDrawerOpen(GravityCompat.START)) {

            stu_draw.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }



    private void replaceLayout(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.stu_home_framelayout,fragment);
        transaction.commit();
    }

    private void sendIn4tofrg(String id) {
        Bundle bundle = new Bundle();
        frg_stu_info frg_in4 = new frg_stu_info();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.stu_home_framelayout,frg_in4);
        transaction.commit();
        Cursor cursor = db.getdataSTU_withID(id);
        if (cursor.getCount()==0) {
            Toast.makeText(stu_home.this,"Không có dữ liệu hiển thị",Toast.LENGTH_LONG).show();
        } else {
            while (cursor.moveToNext()) {

                mssv = cursor.getString(0);
                hoten = cursor.getString(3);
                gioitinh = cursor.getString(4);
                ngaysinh = cursor.getString(5);
                noisinh = cursor.getString(6);
                diachi = cursor.getString(7);
                shs = cursor.getString(8);
                cccd =  cursor.getString(9);
                sdt = cursor.getString(10);
                email = cursor.getString(11);
                malop = cursor.getString(12);
                msgv = cursor.getString(13);

            }
            bundle.putString("mssv",mssv);
            bundle.putString("hoten",hoten);
            bundle.putString("gioitinh",gioitinh);
            bundle.putString("ngaysinh",ngaysinh);
            bundle.putString("noisinh",noisinh);
            bundle.putString("diachi",diachi);
            bundle.putString("shs",shs);
            bundle.putString("cccd",cccd);
            bundle.putString("sdt",sdt);
            bundle.putString("email",email);
            bundle.putString("malop",malop);
            bundle.putString("msgv",msgv);
            frg_in4.setArguments(bundle);
        }

    }

    private void sendResultToFrg(String id) {
        frg_stu_result frg_result = new frg_stu_result();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.stu_home_framelayout,frg_result);
        transaction.commit();
        resultSTU_view(stu_id);

    }

    private void changePW(String id) {
        String pw = null;
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.stu_change_pw_dialog);
        dialog.setCanceledOnTouchOutside(false);
        ctec_database db = new ctec_database(this);

        Button yes = dialog.findViewById(R.id.stu_cpw_confirm);
        Button no = dialog.findViewById(R.id.stu_cpw_cancle);
        Button check = dialog.findViewById(R.id.stu_cpw_check);
        TextView title = dialog.findViewById(R.id.dmk);
        EditText oldpw = dialog.findViewById(R.id.stu_cpw_oldpw);
        EditText newpw = dialog.findViewById(R.id.stu_cpw_newpw); newpw.setVisibility(View.INVISIBLE);
        EditText cfpw = dialog.findViewById(R.id.stu_cpw_cfpw); cfpw.setVisibility(View.INVISIBLE);

        frg_stu_changePW frg_chg = new frg_stu_changePW();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.stu_home_framelayout,frg_chg);
        transaction.commit();


        String new_pw = newpw.getText().toString();
        String cf_pw = cfpw.getText().toString();

        Cursor cursor = db.getdataSTU_withID(stu_id);
        while (cursor.moveToNext()) {
            pw = cursor.getString(1);
        }


        String finalPw = pw;
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String old = oldpw.getText().toString();
                title.setText(old);
                if (old.equals(finalPw)) {
                    newpw.setVisibility(View.VISIBLE);
                    cfpw.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(getApplicationContext(),"Mật khẩu khẩu cũ không khớp !",Toast.LENGTH_LONG).show();
                }
            }
        });

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String new_pw = newpw.getText().toString();
                String cf_pw = cfpw.getText().toString();
                if (new_pw.equals(cf_pw)==true) {
                   boolean kq =  db.STU_CHANGEPASSWORD(stu_id,new_pw);
                   if (kq == true) {
                       Toast.makeText(getApplicationContext(), "Mật khẩu đã thay đổi", Toast.LENGTH_LONG).show();
                   } else {
                       Toast.makeText(getApplicationContext(),"Có lỗi xảy ra ! Vui lòng thử lại",Toast.LENGTH_LONG).show();
                   }
                } else {
                    Toast.makeText(getApplicationContext(),"Mật khẩu mới không trùng khớp !",Toast.LENGTH_LONG).show();
                }
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });

        dialog.show();
    }

    private void logout() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.stu_logout_dialog);
        dialog.setCanceledOnTouchOutside(false);
        Button yes = dialog.findViewById(R.id.stu_logout_cfirm);
        Button no = dialog.findViewById(R.id.stu_logout_cancle);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });

        dialog.show();

    }




}
