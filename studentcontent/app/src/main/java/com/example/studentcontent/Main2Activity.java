package com.example.studentcontent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    ListView listView;
    ArrayList<Student> arrayList;
    StudentAdapter studentAdapter;
    Button btn_tim;
    int ma;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        listView = (ListView)findViewById(R.id.lv);
        arrayList = new ArrayList<>();
        btn_tim = (Button)findViewById(R.id.select);
        btn_tim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayList = new ArrayList<>();
                int i = 0;
                String uri = "content://com.example.studentcontent";
                Uri student = Uri.parse(uri);
                Cursor cursor = getContentResolver().query(student, null, null, null, "ten");
                i = cursor.getCount();
                if (i == 0) {
                    Toast.makeText(getApplicationContext(), "Khong co ket qua tra ve", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (cursor != null) {
                    cursor.moveToFirst();
                    do {
                        Student student1 = new Student();
                        student1.setIdStudent(cursor.getInt(0));
                        student1.setTenSV(cursor.getString(1));
                        student1.setKhoa(cursor.getString(2));
                        arrayList.add(student1);
                    } while (cursor.moveToNext());
                }
                studentAdapter = new StudentAdapter(getApplicationContext(),R.layout.dong_sinh_vien,arrayList);
                listView.setAdapter(studentAdapter);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ma = i;
                Xoa();

            }
        });
    }

    public void Xoa(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xoa");
        builder.setMessage("Ban co muon xoa sinh vien nay khong?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int a = arrayList.get(ma).getIdStudent();
                String uri = "content://com.example.studentcontent/studentdata";
                Uri student = Uri.parse(uri);
                String selection = a+"";
                int k = getContentResolver().delete(student,"id="+selection,null);
                if(k>0){
                    Toast.makeText(getApplicationContext(),"Xoa thanh cong",Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(getApplicationContext(),"Khong co ma so trong danh sach",Toast.LENGTH_SHORT).show();
                arrayList.remove(ma);
                ArrayAdapter<Student> arrayAdapter = new ArrayAdapter<>(getApplication(),R.layout.dong_sinh_vien,arrayList);
                arrayAdapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }

}
