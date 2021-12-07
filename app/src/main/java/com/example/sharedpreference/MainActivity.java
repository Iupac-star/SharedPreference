package com.example.sharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText fname, sname, email;
    Button btn_update, btn_clear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fname = (EditText)findViewById(R.id.edit_fname);
        sname = (EditText)findViewById(R.id.edit_sname);
        email = (EditText)findViewById(R.id.edit_email);

        btn_clear = (Button)findViewById(R.id.btn_clear);
        btn_update = (Button)findViewById(R.id.btn_update);

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Details updated successfully", Toast.LENGTH_SHORT).show();
            }
        });

        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearDetails();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences file = getSharedPreferences("udetails",MODE_APPEND);
        String Fname = file.getString("f_name","");
        String Sname = file.getString("s_name","");
        String em = file.getString("e_mail","");
        fname.setText(Fname);
        sname.setText(Sname);
        email.setText(em);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences file = getSharedPreferences("udetails",MODE_PRIVATE);
        SharedPreferences.Editor edit_details = file.edit();
        edit_details.putString("f_name",fname.getText().toString());
        edit_details.putString("s_name",sname.getText().toString());
        edit_details.putString("e_mail",email.getText().toString());
        edit_details.commit();
    }

    public void clearDetails(){
        SharedPreferences file = getSharedPreferences("udetails",MODE_PRIVATE);
        SharedPreferences.Editor editor = file.edit();
        fname.setText("");
        sname.setText("");
        email.setText("");
        editor.clear();
    }
}