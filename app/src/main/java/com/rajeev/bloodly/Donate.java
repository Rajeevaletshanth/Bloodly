package com.rajeev.bloodly;

import android.database.Cursor;
import android.os.Bundle;
//import android.support.v7.app.AlertDialog;
//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class Donate extends AppCompatActivity {
    Button addbtn;
    Button viewbtn;
    Button delbtn;
    Button updatebtn;
    DatabaseHelper db;


    Spinner spinnernames;
    Spinner spinnerunits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doner);

        db = new DatabaseHelper(this);


        spinnernames = (Spinner) findViewById(R.id.spinner);
        spinnerunits = (Spinner) findViewById(R.id.spinner2);
        addbtn = (Button)findViewById(R.id.savebtn);
        viewbtn = (Button)findViewById(R.id.txtviewbtn);
        delbtn = (Button)findViewById(R.id.button3);
        updatebtn = (Button)findViewById(R.id.button);

        addbtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) { if (spinnernames.getSelectedItem().toString().trim().equals("Select")) {
                        Toast.makeText(Donate.this, "Select Blood Group and Save", Toast.LENGTH_SHORT).show();
                    }
                        if (spinnerunits.getSelectedItem().toString().trim().equals("Select")) {
                            Toast.makeText(Donate.this, "Select Blood Units and Save", Toast.LENGTH_SHORT).show();
                        }else{
                            boolean isInserted =    db.insertData(spinnernames.getSelectedItem().toString(),
                                    spinnerunits.getSelectedItem().toString());


                            if (isInserted==true)
                                Toast.makeText(Donate.this,"Details Are Inserted",Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(Donate.this,"Details Are Not Inserted",Toast.LENGTH_LONG).show();
                        }}
                }
        );

        viewbtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res =  db.getAllData();
                        if (res.getCount()==0){
                            showMessage("Error","Nothing Found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()){
                            buffer.append("Blood Group :"+res.getString(1)+"\n");
                            buffer.append("Units :"+res.getString(2)+"\n\n");


                        }

                        showMessage("Available Blood Details",buffer.toString());
                    }
                }
        );

        delbtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (spinnernames.getSelectedItem().toString().trim().equals("Select")) {
                            Toast.makeText(Donate.this, "Select Blood Group and Delete", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Integer deleterows = db.DeleteData(spinnernames.getSelectedItem().toString());
                            if (deleterows > 0)
                                Toast.makeText(Donate.this,"Details Are Deleted",Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(Donate.this,"Details Are Not Deleted",Toast.LENGTH_LONG).show();
                        }
                    }}
        );

        updatebtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (spinnernames.getSelectedItem().toString().trim().equals("Select")) {
                            Toast.makeText(Donate.this, "Select Blood Group and Update", Toast.LENGTH_SHORT).show();
                        }
                        if (spinnerunits.getSelectedItem().toString().trim().equals("Select")) {
                            Toast.makeText(Donate.this, "Select Blood Units and Update", Toast.LENGTH_SHORT).show();
                        }else{

                            boolean isUpdate = db.updateData(spinnernames.getSelectedItem().toString(),
                                    spinnerunits.getSelectedItem().toString());

                            if (isUpdate==true)

                                Toast.makeText(Donate.this,"Details Are Updated",Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(Donate.this,"Details Are Not Updated",Toast.LENGTH_LONG).show();

                        }}
                }
        );

    }


    public void showMessage(String title, String Messsage){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Messsage);
        builder.show();


    }


}
