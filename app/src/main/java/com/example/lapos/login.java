package com.example.lapos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Consumer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lapos.Controller.UserAPI;

public class login extends AppCompatActivity {
    private SharedPreferences sessionData;
    private UserAPI API;
    public User user;
    private EditText email;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        API = new UserAPI();
        sessionData = getSharedPreferences("user_details",MODE_PRIVATE);
        if( sessionData.contains("User") ){
            user = API.Object_from_Json(
                    sessionData.getString("User", null )
            );
            openMain();
        }
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
    }


    public void onClickValider(View view) {

        API.login(
            email.getText().toString(),
            password.getText().toString(),
            new Consumer<User>() {
                @Override
                public void accept(User etd) {
                    user = etd;
                    Log.v("login user ", user.toString());
                    SharedPreferences.Editor editor = sessionData.edit();
                    editor.putString("User", API.Object_to_Json(user) );
                    editor.apply();
                    openMain();
                }
            },
            new Consumer<String>() {
                @Override
                public void accept(String error) {
                    showMessage(error);
                }
            }
        );
    }

    public void showMessage(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void openMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}