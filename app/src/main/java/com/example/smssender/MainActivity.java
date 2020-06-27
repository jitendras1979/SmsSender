package com.example.smssender;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.Permission;

public class MainActivity extends AppCompatActivity {

    EditText txtPhone;
    EditText txtMessage;
    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtPhone=findViewById(R.id.txtNumber);
        txtMessage=findViewById(R.id.txtMessage);
        btnSend=findViewById(R.id.btnSend);

    }

    public void sendMessage(View view) {

        int PermissionCheck= ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);

        if (PermissionCheck== PackageManager.PERMISSION_GRANTED) {

            Mymessage();
        }
        else
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},0);

        }

    }

    private void Mymessage() {
        String phoneNumber=txtPhone.getText().toString().trim();
        String Message=txtMessage.getText().toString().trim();

        if (!txtPhone.getText().toString().equals("") || !txtMessage.getText().toString().equals("")) {
            SmsManager smsManager=SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber,null,Message,null,null);
            Toast.makeText(this, "Message Sent", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Please fill Pnone or Message", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {

            case 0:
                if(grantResults.length>=0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    Mymessage();
                }
                else
                {
                    Toast.makeText(this, "You dont have required permission", Toast.LENGTH_SHORT).show();
                }
        }
    }
}
