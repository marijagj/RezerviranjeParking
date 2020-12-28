package com.example.rezerviranjeparking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
 EditText username,password,repassword;
 Button signup;
 DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username=(EditText) findViewById(R.id.username);
        password=(EditText) findViewById(R.id.password);
        repassword=(EditText) findViewById(R.id.repassword);
        signup=(Button) findViewById(R.id.btnsignup);
        db=new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();
                if(user.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(MainActivity.this,"Please enter all the fields",Toast.LENGTH_SHORT).show();
                else
                {
                    if(pass.equals(repass)) {
                        Boolean checkuser=db.checkusername(user);
                        if(checkuser==false)
                        {
                            Boolean insert=db.insertData(user,pass);
                            if(insert==true){
                                Toast.makeText(MainActivity.this,"Registered sucessfully",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(v.getContext(),HomeActivity.class);
                                intent.putExtra("User",user);
                                v.getContext().startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(MainActivity.this,"Registration failed",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this,"User already exist! please sign in",Toast.LENGTH_SHORT).show();
                        }

                    }
                    else
                    {
                        Toast.makeText(MainActivity.this,"Passwords dont match",Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Паркинг резервација");

    }
}