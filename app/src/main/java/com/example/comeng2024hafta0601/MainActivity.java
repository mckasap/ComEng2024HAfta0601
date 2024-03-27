package com.example.comeng2024hafta0601;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    private class myAsyncTask extends AsyncTask<String,Void,String>{


        @Override
        protected String doInBackground(String... strings) {
            StringBuilder sb= new StringBuilder("");
            try {
                URL url = new URL(strings[0]);
                HttpsURLConnection con= (HttpsURLConnection) url.openConnection();
                con.connect();

                InputStream in = con.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);

                int data= reader.read();
                while(data!=-1){
                    sb.append((char)data);
                    data= reader.read();
                }



            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


            return sb.toString();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myAsyncTask task= new myAsyncTask();

        try {
            String str= task.execute("https://www.google.com").get();
            Log.d("COMENG",str);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}