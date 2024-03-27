package com.example.comeng2024hafta0601;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
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

                Document doc =  Jsoup.connect(strings[0]).get();
                Log.d("Title",doc.title());

               /* URL url = new URL(strings[0]);
                HttpsURLConnection con= (HttpsURLConnection) url.openConnection();
                con.connect();

                InputStream in = con.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);

                int data= reader.read();
                while(data!=-1){
                    sb.append((char)data);
                    data= reader.read();
                }

            */

            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


            return sb.toString();
        }
    }
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv=(ImageView) findViewById(R.id.imageView);
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

    private class GoruntuIndir extends AsyncTask<String,Void, Bitmap>{
        @Override
        protected Bitmap doInBackground(String... strings) {

            try {
                URL url = new URL("http://www.agaclar.net/forum/attachments/sukulent/7290d1171654782-pasa-008.jpg");
                HttpURLConnection con= (HttpURLConnection)url.openConnection();
                con.connect();
                InputStream in = con.getInputStream();
                Bitmap btm= BitmapFactory.decodeStream(in);

                return  btm;
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }


    public void Getir(View V){

        GoruntuIndir gi= new GoruntuIndir();

        try {
            iv.setImageBitmap(gi.execute().get());

        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }

}