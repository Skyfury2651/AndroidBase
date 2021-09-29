package com.example.emptyandroid;

import androidx.appcompat.app.AppCompatActivity;


import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public final static String FILE_NAME = "note.txt";
    public final static String FILE_PATH = "sdcard/Demo";

    EditText editText;
    Button btSave;
    Button btRead;
    RadioGroup radGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        btRead = findViewById(R.id.btRead);
        btSave = findViewById(R.id.btSave);
        radGroup = findViewById(R.id.radGroup);

        btSave.setOnClickListener(this);
        btRead.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int idChecked = radGroup.getCheckedRadioButtonId();
        switch (view.getId()) {
            case R.id.btSave:
                if (idChecked == R.id.radInternal) {
                    onSaveInternal();
                } else {
                    onSaveExternal();
                }
                break;
            case R.id.btRead:
                if (idChecked == R.id.radInternal) {
                    onReadInternal();
                } else {
                    onReadExternal();
                }
                break;
        }
    }

    private void onSaveInternal() {
        try {
            FileOutputStream fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(editText.getText().toString().getBytes());
            fos.flush();
            fos.close();
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void onReadInternal() {
        try {
            FileInputStream fis = openFileInput(FILE_NAME);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            StringBuffer sb = new StringBuffer();
            String line = reader.readLine();
            while (line != null) {
                sb.append(line);
                line = reader.readLine();
            }
            Toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void onSaveExternal() {
        try {
            File directory = new File(FILE_PATH);
            File file = new File(FILE_PATH + FILE_NAME);
            if (!directory.exists()) {
                directory.mkdir();
            }

            // Create file
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(editText.getText().toString().getBytes());
            fos.flush();
            fos.close();
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void onReadExternal() {
        try {
            File file = new File(FILE_NAME);

            FileInputStream fis = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            StringBuffer sb = new StringBuffer();
            String line = reader.readLine();
            while (line != null) {
                sb.append(line);
                line = reader.readLine();
            }
            Toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}