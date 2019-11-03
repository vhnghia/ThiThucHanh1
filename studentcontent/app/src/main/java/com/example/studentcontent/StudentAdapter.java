package com.example.studentcontent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class StudentAdapter extends BaseAdapter {
     Context context;
     int Layout;
     ArrayList<Student> arrayList;

    public StudentAdapter(Context context,int layout, ArrayList<Student> arrayList){
        this.context = context;
        Layout = layout;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(Layout,null);
        //Anh xa
        TextView txtMa = (TextView)view.findViewById(R.id.txt_idDSV);
        TextView txtTen = (TextView)view.findViewById(R.id.txt_TenSV);
        TextView txtKhoa = (TextView)view.findViewById(R.id.txt_KhoaSV);
        Student student = new Student();
        student = arrayList.get(i);
        txtMa.setText(student.getIdStudent()+"");
        txtTen.setText(student.getTenSV());
        txtKhoa.setText(student.getKhoa());
        return  view;
    }
}
