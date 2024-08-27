package com.example.bookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.bookapp.databinding.ActivitySignUpBinding;

public class SignUp extends AppCompatActivity {

     ActivitySignUpBinding binding;
     DatabaseHelper databaseHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        databaseHelper = new DatabaseHelper(this);

        binding.signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.email.getText().toString();
                String fullname = binding.fullname.getText().toString();
                String password = binding.password.getText().toString();
                String conpassword = binding.Cpass.getText().toString();

                if(email.equals("")||fullname.equals("")|password.equals("")||conpassword.equals("")){
                    Toast.makeText(SignUp.this, "All fields are required", Toast.LENGTH_SHORT).show();
                }else{
                    if(password.equals(conpassword)){
                        Boolean checkUserEmail = databaseHelper.checkEmail(email);

                        if(checkUserEmail == false){
                            Boolean insert = databaseHelper.insertData(email,password);
                            
                            if(insert == true){
                                Toast.makeText(SignUp.this, "Signup Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),Login.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(SignUp.this, "User already exists, Please login", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(SignUp.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            }
        });

        binding.loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });
    }
}