package com.example.bookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookapp.databinding.ActivityLoginBinding;

public class Login extends AppCompatActivity {

    ActivityLoginBinding binding;
    DatabaseHelper databaseHelper;
    EditText emailEditText;
    EditText passwordEditText;
    Button loginButton;
    String data;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper = new DatabaseHelper(this);

        emailEditText = findViewById(R.id.Email); // Replace `editTextEmail` with the actual ID of your email EditText view
        passwordEditText = findViewById(R.id.Pass); // Replace `editTextPassword` with the actual ID of your password EditText view
        loginButton = findViewById(R.id.loginButton);

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String email = binding.Email.getText().toString();
               String password = binding.Pass.getText().toString();

               data = emailEditText.getText().toString();



               if(email.equals("")||password.equals("")){
                   Toast.makeText(Login.this, "all field are required", Toast.LENGTH_SHORT).show();
               }else{
                   Boolean checkCredentials = databaseHelper.chekEmailPassword(email,password);

                   if(checkCredentials == true){
                         Toast.makeText(Login.this, "Login successfully", Toast.LENGTH_SHORT).show();
                         DataManager.getInstance().setSharedData(email);
                         Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                         startActivity(intent);
                   }else{
                       Toast.makeText(Login.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                   }
               }

            }
        });
        binding.signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,SignUp.class);
                startActivity(intent);
            }
        });




    }


    public String getEmail(){
      return  data;
    }


}

