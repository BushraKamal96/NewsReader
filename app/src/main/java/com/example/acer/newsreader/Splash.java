package com.example.acer.newsreader;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.acer.newsreader.Interface.MyApi;
import com.example.acer.newsreader.Interface.RetrofitBuilder;
import com.example.acer.newsreader.model.RootObject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;


public class Splash extends AppCompatActivity {

    CompositeDisposable disposable = new CompositeDisposable();
    MyApi my_api;

    ProgressDialog progressDialog;

    ProgressDialog progressSetter() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Getting news");
        progressDialog.setTitle("Please wait..");
        return progressDialog;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread mythread = new Thread() {

            public void run() {
                try {
                    sleep(1000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        };
        mythread.start();


        progressDialog = progressSetter();
        progressDialog.show();

    }

    {

         //creating retrofit object

         Retrofit retrofit = RetrofitBuilder.getInstance();

         //now saying retrofit to implement myapi class


         my_api = retrofit.create(MyApi.class);

         try

         {
             getData();
         } catch (
                 Exception e)

         {
             Log.e("ExceptionFromServer", e.getMessage());
         }
     }



    private void getData() {

        disposable.add(my_api.getRootObject()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new io.reactivex.functions.Consumer<RootObject>() {
                    @Override
                    public void accept(RootObject rootObjects) throws Exception {

                        progressDialog.dismiss();

                        Common.sources = rootObjects.getSources();
                        Intent intent = new Intent(Splash.this, MainActivity.class);
                        startActivity(intent);

                    }
                }));
        progressDialog.dismiss();
    }

    @Override
    protected void onStop() {
        super.onStop();
        disposable.dispose();
    }
}
