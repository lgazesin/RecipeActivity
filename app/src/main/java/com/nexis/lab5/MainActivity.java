package com.nexis.lab5;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.app.Activity;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    Toolbar toolbar;
    private String stringFile = Environment.getExternalStorageDirectory().getPath() + File.separator + "Test.pdf";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.app_name));
        setSupportActionBar(toolbar);

        ActivityCompat.requestPermissions(this, new String[]{READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());


        ArrayList<Recipe> arrayList = new ArrayList<>();
        arrayList.add(new Recipe(R.drawable.item1,"Vension(Deer) Steak",""));
        arrayList.add(new Recipe(R.drawable.item2,"Hamburger Steak with Onion and Gravy",""));
        arrayList.add(new Recipe(R.drawable.item3,"Chicken Teriyaki(pan fried)",""));
        arrayList.add(new Recipe(R.drawable.item4,"Sticky miso peppers","Vegan"));
        arrayList.add(new Recipe(R.drawable.item5,"Chicken and Waffles(Sweet Hot Fried)",""));
        arrayList.add(new Recipe(R.drawable.item6,"Greek-inspired cauliflower stew","Vegan"));
        arrayList.add(new Recipe(R.drawable.item7,"Chicken Alfredo",""));
        arrayList.add(new Recipe(R.drawable.item8,"Meatloaf",""));
        arrayList.add(new Recipe(R.drawable.item9,"French Toast Waffle",""));

        RecipeAdapter recipeAdapter = new RecipeAdapter(this,R.layout.list_row,arrayList);
        listView.setAdapter(recipeAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0){
                    Intent intent = new Intent(view.getContext(), VensionSteak.class);
                    startActivity(intent);
                }
                if (i == 1){
                    Intent intent = new Intent(view.getContext(), HamburgerSteak.class);
                    startActivity(intent);
                }
                if (i == 2){
                    Intent intent = new Intent(view.getContext(), ChickenTeriyaki.class);
                    startActivity(intent);
                }
                if (i == 3){
                    Intent intent = new Intent(view.getContext(), StickyMiso.class);
                    startActivity(intent);
                }
                if (i == 4){
                    Intent intent = new Intent(view.getContext(), ChickenWaffles.class);
                    startActivity(intent);
                }
                if (i == 5){
                    Intent intent = new Intent(view.getContext(), GreekInspired.class);
                    startActivity(intent);
                }
                if (i == 6){
                    Intent intent = new Intent(view.getContext(), ChickenAlfredo.class);
                    startActivity(intent);
                }
                if (i == 7){
                    Intent intent = new Intent(view.getContext(), Meatloaf.class);
                    startActivity(intent);
                }
                if (i == 8){
                    Intent intent = new Intent(view.getContext(), FrenchToast.class);
                    startActivity(intent);
                }

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return true;
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

            intentShare.setType("text/plain");
            intentShare.putExtra(Intent.EXTRA_SUBJECT, "My Subject Here...");
            intentShare.putExtra(Intent.EXTRA_TEXT,"My Text of the message goes here... write anything");
            startActivity(Intent.createChooser(intentShare,"Share the text"));


        }
        return super.onOptionsItemSelected(item);
    }

}