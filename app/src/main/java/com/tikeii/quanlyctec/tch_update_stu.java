package com.tikeii.quanlyctec;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class tch_update_stu extends AppCompatActivity {
    EditText matkhau,hoten,gioitinh, ngaysinh, noisinh,diachi, sohoso, cccd,sdt,email,mlop ,mgv;
    TextView mssv;
    ImageButton add,cancle;
    ctec_database db = new ctec_database(this);
    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tch_update_stu);
        mssv = findViewById(R.id.edt_updSTU_mssv);
        matkhau = findViewById(R.id.edt_updSTU_matkhau);
        hoten = findViewById(R.id.edt_updSTU_hoten);
        gioitinh = findViewById(R.id.edt_updSTU_gioitinh);
        ngaysinh = findViewById(R.id.edt_updSTU_ngaysinh);
        noisinh = findViewById(R.id.edt_updSTU_noisinh);
        diachi = findViewById(R.id.edt_updSTU_diachi);
        sohoso = findViewById(R.id.edt_updSTU_SHSNH);
        cccd = findViewById(R.id.edt_updSTU_cccd);
        sdt = findViewById(R.id.edt_updSTU_sdt);
        email = findViewById(R.id.edt_updSTU_email);
        mlop = findViewById(R.id.edt_updSTU_mlop);
        mgv = findViewById(R.id.edt_updSTU_msgv);


        Intent i = getIntent();
        String ms = i.getStringExtra("msv");
        mssv.setText(ms);

        add = findViewById(R.id.imgbt_stuin4pg_luu);
        cancle = findViewById(R.id.imgbt_stuin4pg_back);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mk = matkhau.getText().toString();
                String ten = hoten.getText().toString();
                String gt = gioitinh.getText().toString();
                String dob = ngaysinh.getText().toString();
                String nsinh = noisinh.getText().toString();
                String dc = diachi.getText().toString();
                String shs = sohoso.getText().toString();
                String cmt = cccd.getText().toString();
                String phone = sdt.getText().toString();
                String mail = email.getText().toString();
                String lop = mlop.getText().toString();
                String msgv = mgv.getText().toString();
                Boolean UpdateSTU = db.updateSTU(ms, mk, ten, gt, dob, nsinh, dc, shs, cmt, phone, mail, lop, msgv);
                if (UpdateSTU == true) {
                    Toast.makeText(tch_update_stu.this, "Cập Nhật Sinh Viên thành công", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(tch_update_stu.this, "Xin vui lòng thử lại", Toast.LENGTH_LONG).show();
                }
            }
        });

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}