package com.nexis.lab5;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.Objects;

public class ChickenAlfredo extends AppCompatActivity {
    private String stringFile = Environment.getExternalStorageDirectory().getPath() + File.separator + "Test.pdf";
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chicken_alfredo);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        text = findViewById(R.id.text);

        ActivityCompat.requestPermissions(this, new String[]{READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_search){
            new AlertDialog.Builder(this)
                    .setTitle("Help")
                    .setMessage("Do you need help?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }else if (id == R.id.share){
            File file = new File(stringFile);

            if (!file.exists()){
                Toast.makeText(this,"You clicked",Toast.LENGTH_LONG).show();
            }
            Intent intentShare = new Intent(Intent.ACTION_SEND);
            intentShare.setType("application/pdf");
            intentShare.putExtra(Intent.EXTRA_STREAM, Uri.parse("file//" + file));
            startActivity(Intent.createChooser(intentShare,"Share the file..."));

            String txt = text.getText().toString();
            intentShare.setType("text/plain");
            intentShare.putExtra(Intent.EXTRA_SUBJECT, "My Subject Here...");
            intentShare.putExtra(Intent.EXTRA_TEXT,txt);
            startActivity(Intent.createChooser(intentShare,"Share the text"));
        }
        return super.onOptionsItemSelected(item);
    }
}