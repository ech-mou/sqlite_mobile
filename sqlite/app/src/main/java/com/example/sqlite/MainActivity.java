package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

   DB_Sqlit db=new DB_Sqlit(this);

   EditText name,email,ID;
   ListView lst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=findViewById(R.id.editText_name);
        email=findViewById(R.id.editText_email);
        ID=findViewById(R.id.editText_id);
        lst=findViewById(R.id.listView_data);
        ShowData();
    }
    public void btn_add_data(View view) {
        String Name= name.getText().toString();
        String Email= email.getText().toString();

        Boolean result=db.insertData(Name,Email);
        if (result==true){
            Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show();
            name.setText("");
            email.setText("");
            ShowData();
        }else{
            Toast.makeText(this, "NO", Toast.LENGTH_SHORT).show();
        }

    }
    public void ShowData(){
        ArrayList<String> listData=db.getAllrecord();
        ArrayAdapter arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,listData);
        lst.setAdapter(arrayAdapter);

    }

    public void btn_update(View view) {

        String Name= name.getText().toString();
        String Email= email.getText().toString();
        String id= ID.getText().toString();

        Boolean result=db.updateData(id,Name,Email);
        if (result==true){
            Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show();
            name.setText("");
            email.setText("");
            ID.setText("");
            ShowData();
        }else{
            Toast.makeText(this, "NO", Toast.LENGTH_SHORT).show();
        }
    }

    public void btn_delete(View view) {
        String id=ID.getText().toString();
        Integer result= db.Delete(id);
        if(result > 0){
            Toast.makeText(this, "OK    ", Toast.LENGTH_SHORT).show();
            ShowData();
        }else{
            Toast.makeText(this, "NO", Toast.LENGTH_SHORT).show();
        }

    }
}