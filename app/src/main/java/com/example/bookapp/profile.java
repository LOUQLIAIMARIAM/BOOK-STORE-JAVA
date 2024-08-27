package com.example.bookapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import  com.example.bookapp.Login;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.github.dhaval2404.imagepicker.ImagePicker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import android.Manifest;
import java.io.InputStream;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class profile extends AppCompatActivity {

    ImageView imageView;
    TextView text;
    private TextView textViewName;
    private SharedPreferences sharedPreferences;



    private static final int REQUEST_PERMISSION = 1;
    private static final int REQUEST_PICK_IMAGE = 2;

    private ImageView imagePreview;
    private Uri selectedImageUri;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Button btnPickImage = findViewById(R.id.btnPickImage);
        imagePreview = findViewById(R.id.image);


        textViewName = findViewById(R.id.textViewName);
        textViewName = findViewById(R.id.textViewName);
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        // Récupérer la valeur du texte modifié depuis SharedPreferences
        String modifiedText = sharedPreferences.getString("modifiedText", "");
        textViewName.setText(modifiedText);
        imageView = findViewById(R.id.imageView);
        text = findViewById(R.id.emailtext);

        imagePreview.setClipToOutline(true);

        String sharedEmail = DataManager.getInstance().getSharedData(); // Récupérer l'e-mail partagé
        text.setText(sharedEmail);


        btnPickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPermission()) {
                    openImagePicker();
                } else {
                    requestPermission();
                }
            }
        });
        String imageFileName = "my_image.jpg";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imageFile = new File(storageDir, imageFileName);

        if (imageFile.exists()) {
            // L'image existe, la charger et l'afficher dans l'ImageView
            Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
            imagePreview.setVisibility(View.VISIBLE);
            imagePreview.setImageBitmap(bitmap);
        }

    }

    public void showNameEditDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Modifier le nom");

        final EditText editText = new EditText(this);
        editText.setText(textViewName.getText().toString());
        builder.setView(editText);

        builder.setPositiveButton("Enregistrer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newName = editText.getText().toString();
                textViewName.setText(newName);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("modifiedText", newName);
                editor.apply();
                // Vous pouvez enregistrer le nouveau nom dans votre système ou effectuer d'autres opérations nécessaires
            }
        });

        builder.setNegativeButton("Annuler", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private boolean checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int result = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
            return result == PackageManager.PERMISSION_GRANTED;
        }
        return true;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISSION);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openImagePicker();
            } else {
                Toast.makeText(this, "Permission refusée. Impossible de choisir une image.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            selectedImageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
                imagePreview.setVisibility(View.VISIBLE);
                imagePreview.setImageBitmap(bitmap);
                imagePreview.setClipToOutline(true);

                // Enregistrement de l'image dans le stockage externe
                String imageFileName = "my_image.jpg";
                File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                File imageFile = new File(storageDir, imageFileName);

                FileOutputStream outputStream = new FileOutputStream(imageFile);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                outputStream.flush();
                outputStream.close();

                Toast.makeText(this, "Image enregistrée avec succès.", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Erreur lors du chargement de l'image", Toast.LENGTH_SHORT).show();
            }
        }
    }
}



