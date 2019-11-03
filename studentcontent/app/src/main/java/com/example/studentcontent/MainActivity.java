package com.example.studentcontent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button btnSave, btnSelect,btnDelete,btnUpdate;
    private EditText edtId, edtTen, edtKhoa;
    private GridView gridView;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edtId.getText().toString().equalsIgnoreCase("") && !edtTen.getText().toString().equalsIgnoreCase("") && !edtKhoa.getText().toString().equalsIgnoreCase("")) {
                    if(KiemTraSo()) {
                        if(KiemTraTrung()) {
                            String uri = "content://com.example.studentcontent";
                            ContentValues contentValues = new ContentValues();
                            contentValues.put("id", edtId.getText().toString());
                            contentValues.put("ten", edtTen.getText().toString());
                            contentValues.put("khoa", edtKhoa.getText().toString());
                            Uri student = Uri.parse(uri);
                            Uri insert = getContentResolver().insert(student, contentValues);
                            Toast.makeText(getApplicationContext(), "Them thanh cong", Toast.LENGTH_SHORT).show();
                        }else
                            Toast.makeText(getApplicationContext(),"Loi trung ma",Toast.LENGTH_SHORT).show();
                    }else
                        Toast.makeText(getApplicationContext(), "Vui long nhap id la so", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Vui long nhap day du thong tin", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtId.getText().toString().equalsIgnoreCase("")){
                    ArrayList<String> list_string = new ArrayList<>();
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

                            list_string.add(cursor.getInt(0) + "");
                            list_string.add(cursor.getString(1));
                            list_string.add(cursor.getString(2));
                        } while (cursor.moveToNext());
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, list_string);
                        gridView.setAdapter(arrayAdapter);
                    } else
                        Toast.makeText(getApplicationContext(), "Khong co ket qua tra ve", Toast.LENGTH_SHORT).show();
                }else{
                    ArrayList<String> list_string = new ArrayList<>();
                    int i = 0;
                    String uri = "content://com.example.studentcontent";
                    Uri student = Uri.parse(uri);
                    Cursor cursor = getContentResolver().query(student, null, null, null, "ten");
                    if (cursor != null) {
                        cursor.moveToFirst();
                        do {
                            if(edtId.getText().toString().equalsIgnoreCase(cursor.getInt(0)+""))
                            {
                                list_string.add(cursor.getInt(0)+"");
                                list_string.add(cursor.getString(1));
                                list_string.add(cursor.getString(2));
                                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, list_string);
                                gridView.setAdapter(arrayAdapter);
                                arrayAdapter.notifyDataSetChanged();
                                return;
                            }

                        } while (cursor.moveToNext());

                    } else
                        Toast.makeText(getApplicationContext(), "Khong co ket qua tra ve", Toast.LENGTH_SHORT).show();
                }

            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = "content://com.example.studentcontent/studentdata";
                Uri student = Uri.parse(uri);
                if(KiemTraSo()){
                    String selection = Integer.parseInt(edtId.getText().toString())+"";
                    int i = getContentResolver().delete(student,"id="+selection,null);
                    if(i>0){
                        Toast.makeText(getApplicationContext(),"Xoa thanh cong",Toast.LENGTH_SHORT).show();
                    }else
                        Toast.makeText(getApplicationContext(),"Khong co ma so trong danh sach",Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(getApplicationContext(),"Vui long nhap ma so la so",Toast.LENGTH_SHORT).show();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edtId.getText().toString().equalsIgnoreCase("") && !edtTen.getText().toString().equalsIgnoreCase("") && !edtKhoa.getText().toString().equalsIgnoreCase("")) {
                    if(KiemTraSo()){
                        String uri = "content://com.example.studentcontent/studentdata";
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("id",edtId.getText().toString());
                        contentValues.put("ten",edtTen.getText().toString());
                        contentValues.put("khoa",edtKhoa.getText().toString());
                        Uri book = Uri.parse(uri);
                        int i = getContentResolver().update(book,contentValues,"id="+edtId.getText().toString(),null);
                        if(i>0)
                            Toast.makeText(getApplicationContext(),"update thanh cong",Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(getApplicationContext(),"Loi khong itm thay ma de update",Toast.LENGTH_SHORT).show();
                    }else
                        Toast.makeText(getApplicationContext(), "Vui long nhap id la so", Toast.LENGTH_SHORT).show();

                }else
                    Toast.makeText(getApplicationContext(),"Khong duoc de trong thong tin",Toast.LENGTH_SHORT).show();
                }
        });
    }

    public void AnhXa() {
        btnSave = (Button) findViewById(R.id.btnSave);
        btnSelect = (Button) findViewById(R.id.btnSelect);
        btnDelete = (Button)findViewById(R.id.btnDelete);
        btnUpdate = (Button)findViewById(R.id.btnUpdate);
        edtId = (EditText) findViewById(R.id.edtMaSo);
        edtTen = (EditText) findViewById(R.id.edtTen);
        edtKhoa = (EditText) findViewById(R.id.edtKhoa);
        gridView = (GridView) findViewById(R.id.gv);
        dbHelper = new DBHelper(this);
    }

    public boolean KiemTraSo() {
        try {
            float kiemTraSo = Float.parseFloat(edtId.getText().toString());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean KiemTraTrung() {
        ArrayList<String> list_string = new ArrayList<>();
        int i = 0;
        int maSo = Integer.parseInt(edtId.getText().toString());
        String uri = "content://com.example.studentcontent";
        Uri student = Uri.parse(uri);
        Cursor cursor = getContentResolver().query(student, null, null, null, "ten");
        i = cursor.getCount();
        if (i == 0) {
            Toast.makeText(getApplicationContext(), "Khong co ket qua tra ve", Toast.LENGTH_SHORT).show();
        } else {
            if (cursor != null) {
                cursor.moveToFirst();
                do {
                    int a = cursor.getInt(0);
                    if (a == maSo) {
                        return false;
                    }
                    list_string.add(cursor.getInt(0) + "");
                    list_string.add(cursor.getString(1));
                    list_string.add(cursor.getString(2));
                } while (cursor.moveToNext());
            }
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.idThem:
                ShowDialogThem();
                return true;
            case R.id.idTim1SinhVien:
                ShowDialogTim();
                return true;
            case R.id.idDanhSach:
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }



    public void ShowDialogThem(){
        Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.custom_them);
        final EditText id = (EditText)dialog.findViewById(R.id.edtId);
        final EditText ten = (EditText)dialog.findViewById(R.id.edtTen);
        final EditText khoa = (EditText)dialog.findViewById(R.id.edtKhoa);
        final Button them = (Button)dialog.findViewById(R.id.btnSaveCustom);
        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = "content://com.example.studentcontent";
                Uri student = Uri.parse(uri);
                ContentValues contentValues = new ContentValues();
                contentValues.put("id",Integer.parseInt(id.getText().toString()));
                contentValues.put("ten",ten.getText().toString());
                contentValues.put("khoa",khoa.getText().toString());
                Uri insert = getContentResolver().insert(student,contentValues);
            }
        });
        dialog.show();
    }

    public void ShowDialogTim(){
        Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.timtheoma);
        final EditText ma = (EditText)dialog.findViewById(R.id.idTimTheoMa);
        final Button tim = (Button)dialog.findViewById(R.id.idTIm);
        tim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> list_string = new ArrayList<>();
                int i = 0;
                String uri = "content://com.example.studentcontent";
                Uri student = Uri.parse(uri);
                Cursor cursor = getContentResolver().query(student, null, null, null, "ten");
                if (cursor != null) {
                    cursor.moveToFirst();
                    do {
                        if(ma.getText().toString().equalsIgnoreCase(cursor.getInt(0)+""))
                        {
                            list_string.add(cursor.getInt(0)+"");
                            list_string.add(cursor.getString(1));
                            list_string.add(cursor.getString(2));
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, list_string);
                            gridView.setAdapter(arrayAdapter);
                            arrayAdapter.notifyDataSetChanged();
                            return;
                        }

                    } while (cursor.moveToNext());

                } else
                    Toast.makeText(getApplicationContext(), "Khong co ket qua tra ve", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
    }

}
