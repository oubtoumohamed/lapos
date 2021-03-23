package com.example.lapos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Payement extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payement);
    }

    public void btn_calculate_(View view){
        Button btn = findViewById(view.getId());

        Toast.makeText(this,btn.getText(), Toast.LENGTH_LONG).show();
    }
    public void lick(View v){

        Toast.makeText(this,"mlkml", Toast.LENGTH_LONG).show();

        /*new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(R.string.confirm)
                .setMessage(R.string.carte_confirm_empty)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).setNegativeButton(R.string.no, null).show();*/
    }
}