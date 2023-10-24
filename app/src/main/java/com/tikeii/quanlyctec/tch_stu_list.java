package com.tikeii.quanlyctec;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class tch_stu_list extends AppCompatActivity {
    ArrayList<String> mssv, hoten, gioitinh, ngaysinh, noisinh, diachi, shs, cccd, sdt, email,malop, msgv;
    ctec_database db;
    tch_list_stu_adapter adapter;

    RecyclerView recyclerView;

    Button exit,deletein4, upd, tim,add;
    String mlop;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tch_stu_list);
        Intent i = this.getIntent();
        String id = i.getStringExtra("MSGV_INPUT");
        String classid = i.getStringExtra("MALOP_GV");

        upd = findViewById(R.id.tch_list_stu_update);
        tim = findViewById(R.id.tch_list_stu_find);
        add = findViewById(R.id.tch_list_stu_add);
        exit = findViewById(R.id.back_to_tch_home1);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(tch_stu_list.this, tch_add_stu.class);
                startActivity(i);
            }
        });
        upd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
            }
        });
        tim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                find();
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        db = new ctec_database(this);
        mssv = new ArrayList<>();
        hoten = new ArrayList<>();
        gioitinh = new ArrayList<>();
        ngaysinh = new ArrayList<>();
        noisinh = new ArrayList<>();
        diachi = new ArrayList<>();
        shs = new ArrayList<>();
        cccd = new ArrayList<>();
        sdt = new ArrayList<>();
        email = new ArrayList<>();
        malop = new ArrayList<>();
        msgv = new ArrayList<>();
        recyclerView = findViewById(R.id.tch_list_stu);
        adapter = new tch_list_stu_adapter(this, mssv, hoten, gioitinh, ngaysinh, noisinh, diachi, shs, cccd, sdt, email, malop, msgv);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displaySTU();

        deletein4 = findViewById(R.id.tch_list_stu_detele);
        Cursor cursor = db.getdataSTU_withClassID(classid);
        if (cursor.getCount() == 0) {
            deletein4.setVisibility(View.INVISIBLE);
            upd.setVisibility(View.INVISIBLE);
            tim.setVisibility(View.INVISIBLE);
            Toast.makeText(tch_stu_list.this, "Không có dữ liệu hiển thị", Toast.LENGTH_LONG).show();
            return;
        }

        deletein4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comfirm_det_stu_diaglog();
            }
        });


    }




    private void displaySTU() {
        Intent i = getIntent();
        String mlop = i.getStringExtra("MALOP_GV");
        Cursor cursor = db.getdataSTU_withClassID(mlop);
            while (cursor.moveToNext()) {
                mssv.add(cursor.getString(0));
                hoten.add(cursor.getString(3));
                gioitinh.add(cursor.getString(4));
                ngaysinh.add(cursor.getString(5));
                noisinh.add(cursor.getString(6));
                diachi.add(cursor.getString(7));
                shs.add(cursor.getString(8));
                cccd.add(cursor.getString(9));
                sdt.add(cursor.getString(10));
                email.add(cursor.getString(11));
                malop.add(cursor.getString(12));
                msgv.add(cursor.getString(13));
            }
    }

    private void comfirm_det_stu_diaglog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_confrim_del_stu);
        dialog.setCanceledOnTouchOutside(false);
        ctec_database db = new ctec_database(this);



        Button yes = dialog.findViewById(R.id.confirm_btn_yes);
        Button no = dialog.findViewById(R.id.confirm_btn_no);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText msv_input = dialog.findViewById(R.id.mssv_inputToDel);
                String id = msv_input.getText().toString();
                boolean result = db.deleteSTU(id);
                if (result==true) {
                    Toast.makeText(tch_stu_list.this, "Đã xóa sinh viên", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),tch_stu_list.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(tch_stu_list.this, "Không thành công, vui lòng thử lại", Toast.LENGTH_SHORT).show();
                }
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

    private void update(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.tch_update_stu_dialog);
        dialog.setCanceledOnTouchOutside(false);
        ctec_database db = new ctec_database(this);
        Button yes, no;
        EditText msv = dialog.findViewById(R.id.tch_update_stu_mssv_dialog);
        yes = dialog.findViewById(R.id.tch_btnupd_stu_cfirm_dialog);
        no = dialog.findViewById(R.id.tch_btnupd_stu_huy_dialog);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String m = msv.getText().toString();
                Intent n = new Intent(getApplicationContext(),tch_update_stu.class );
                n.putExtra("msv",m);
                startActivity(n);
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
    private void find(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.tch_find_stu_dialog);
        dialog.setCanceledOnTouchOutside(false);
        ctec_database db = new ctec_database(this);
        Button yes, no;
        EditText msv = dialog.findViewById(R.id.tch_find_stu_mssv_dialog);
        yes = dialog.findViewById(R.id.tch_btnfind_stu_cfirm_dialog);
        no = dialog.findViewById(R.id.tch_btnfind_stu_huy_dialog);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String m = msv.getText().toString();
                Intent n = new Intent(getApplicationContext(),stu_info.class );
                n.putExtra("MSSV_INPUT",m);
                startActivity(n);
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