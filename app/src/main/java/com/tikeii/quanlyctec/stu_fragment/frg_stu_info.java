package com.tikeii.quanlyctec.stu_fragment;

import static android.content.Intent.getIntent;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tikeii.quanlyctec.R;
import com.tikeii.quanlyctec.ctec_database;
import com.tikeii.quanlyctec.stu_home;
import com.tikeii.quanlyctec.stu_info;
import com.tikeii.quanlyctec.stu_infopage_adapter;

import java.util.ArrayList;


public class frg_stu_info extends Fragment {
    TextView mssv_id, hoten_id, gioitinh_id, ngaysinh_id, noisinh_id, diachi_id, sohs_id, cccd_id, sdt_id, email_id,mlop_id, msgv_id;

    stu_home stuin4 = new stu_home();





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {




        // Inflate the layout for this fragment
        View itemView = inflater.inflate(R.layout.fragment_frg_stu_info, container, false);
        stuin4 = (stu_home) getActivity();
        mssv_id = itemView.findViewById(R.id.cardview_list_stu_msv);
        hoten_id = itemView.findViewById(R.id.cardview_list_stu_ten);
        gioitinh_id = itemView.findViewById(R.id.cardview_list_stu_gioitinh);
        ngaysinh_id = itemView.findViewById(R.id.cardview_list_stu_namsinh);
        noisinh_id = itemView.findViewById(R.id.cardview_list_stu_noisinh);
        diachi_id = itemView.findViewById(R.id.cardview_list_stu_diachilienlac);
        sohs_id = itemView.findViewById(R.id.cardview_list_stu_SHSNH);
        cccd_id = itemView.findViewById(R.id.cardview_list_stu_cccd);
        sdt_id = itemView.findViewById(R.id.cardview_list_stu_sdt);
        email_id = itemView.findViewById(R.id.cardview_list_stu_email);
        mlop_id = itemView.findViewById(R.id.cardview_list_stu_mlop);
        msgv_id = itemView.findViewById(R.id.cardview_list_stu_mgv);


        mssv_id.setText(getArguments().getString("mssv"));
        hoten_id.setText(getArguments().getString("hoten"));
        gioitinh_id.setText(getArguments().getString("gioitinh"));
        ngaysinh_id.setText(getArguments().getString("ngaysinh"));
        noisinh_id.setText(getArguments().getString("noisinh"));
        diachi_id.setText(getArguments().getString("diachi"));
        sohs_id.setText(getArguments().getString("shs"));
        cccd_id.setText(getArguments().getString("cccd"));
        sdt_id.setText(getArguments().getString("sdt"));
        email_id.setText(getArguments().getString("email"));
        mlop_id.setText(getArguments().getString("malop"));
        msgv_id.setText(getArguments().getString("msgv"));

        return itemView;
    }


}