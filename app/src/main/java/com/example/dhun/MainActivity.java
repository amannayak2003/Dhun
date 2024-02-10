package com.example.dhun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView textView;
    ArrayList<audiomodel> songlist = new ArrayList<audiomodel>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         recyclerView = findViewById(R.id.songlist);
         textView = findViewById(R.id.norecord);
         if(checkpermission()!=true){
             requestpermission();
             return;
         }

         String[] projection = {
                 MediaStore.Audio.Media.TITLE,
                 MediaStore.Audio.Media.DATA,
                 MediaStore.Audio.Media.DURATION
         };
         String selection = MediaStore.Audio.Media.IS_MUSIC + "!=0";
         Cursor cursor = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,projection,selection,null,null);
         while(cursor.moveToNext()){
             audiomodel songdata = new audiomodel(cursor.getString(0),cursor.getString(1),cursor.getString(2));
             if(new File(songdata.getPath()).exists()){
                 songlist.add(songdata);
             }
         }
         if(songlist.size()==0){
             textView.setVisibility(View.VISIBLE);
         }
         else{
//             recyclerview
             recyclerView.setLayoutManager(new LinearLayoutManager(this));
             recyclerView.setAdapter(new MusicListAdapter(songlist,getApplicationContext()));
         }

    }

    boolean checkpermission(){
        int result = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if(result== PackageManager.PERMISSION_GRANTED){
            return true;
        }
        else{
            return false;
        }
    }

    void requestpermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.READ_EXTERNAL_STORAGE)){
            Toast.makeText(MainActivity.this,"READ PERMISSION IS REQUIRD PLEASE ALLOW FROM SETTING",Toast.LENGTH_SHORT).show();
        }
        else{
            ActivityCompat.requestPermissions(MainActivity.this,new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},123);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(recyclerView!=null){
            recyclerView.setAdapter(new MusicListAdapter(songlist,getApplicationContext()));
        }
    }

}