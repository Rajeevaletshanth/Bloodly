package com.rajeev.bloodly;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HomePage extends AppCompatActivity {

    Dialog myDialog;
    Button register,login,joinus;
    EditText uname,pwd;

    LoginDBHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        databaseHelper = new LoginDBHelper(this);

        myDialog = new Dialog(this);

        uname = (EditText)findViewById(R.id.uname);
        pwd = (EditText)findViewById(R.id.pwd);

        login = (Button)findViewById(R.id.login);
        register = (Button)findViewById(R.id.register);
        joinus = (Button)findViewById(R.id.notification);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //you can use anything in place of i
                Intent i = new Intent(HomePage.this, Register.class);
                startActivity(i);

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = uname.getText().toString();
                String password = pwd.getText().toString();

                Boolean checklogin = databaseHelper.CheckLogin(username, password);
                if(checklogin == true){
                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(HomePage.this, Home.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                }

            }
        });


        joinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //you can use anything in place of i
                Intent i = new Intent(HomePage.this, ViewNotificaionPanel.class);
                startActivity(i);

            }
        });
    }

    public void showPopup(View v){
        TextView txtClose;
        myDialog.setContentView(R.layout.learn_more_pop_up);
        txtClose =(TextView) myDialog.findViewById(R.id.txtClose);
        txtClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

}
