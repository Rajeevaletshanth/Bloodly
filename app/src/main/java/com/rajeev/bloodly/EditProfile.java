package com.rajeev.bloodly;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditProfile extends AppCompatActivity {

    public static final String DATABASE_NAME = "myDatabase1";

    profileDBManager profDB;

    EditText editName, editAge, editPhone,editBloodGroup,editPlace;
    Button updateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        profDB = new profileDBManager(this);
        //createTable();

        editName = (EditText)findViewById(R.id.editName);
        editPhone = (EditText)findViewById(R.id.editPhone);
        editBloodGroup =(EditText) findViewById(R.id.editBloodGroup);
        editAge =(EditText) findViewById(R.id.age);
        editPlace =(EditText) findViewById(R.id.editPlace);

        updateBtn = (Button) findViewById(R.id.update);

        update();
    }

    private void update() {

        updateBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String name = editName.getText().toString().trim();
                        String bloodGroup = editBloodGroup.getText().toString().trim();
                        String age = editAge.getText().toString().trim();
                        String phone = editPhone.getText().toString().trim();
                        String place = editPlace.getText().toString().trim();


                        if (name.isEmpty()) {
                            editName.setError("Fill the name");
                            editName.requestFocus();
                            return;
                        }


                        if (phone.isEmpty()) {
                            editPhone.setError("Enter the number");
                            editPhone.requestFocus();
                            return;
                        }

                        if (age.isEmpty()) {
                            editAge.setError("Enter the unit");
                            editAge.requestFocus();
                            return;
                        }

                        if (place.isEmpty()) {
                            editPlace.setError("Enter the place");
                            editPlace.requestFocus();
                            return;
                        }

                        if (bloodGroup.isEmpty()) {
                            editBloodGroup.setError("Enter the blood group");
                            editBloodGroup.requestFocus();
                            return;
                        }

                        boolean isInserted = profDB.update(name, bloodGroup, age, phone, place);

                        if (isInserted == true)
                            Toast.makeText(EditProfile.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(EditProfile.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                    }
                }
        );

    }}
