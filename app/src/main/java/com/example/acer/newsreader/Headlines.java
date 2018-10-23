package com.example.acer.newsreader;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.acer.newsreader.Adapter.HeadlineAdapter;
import com.example.acer.newsreader.Interface.MyApi;
import com.example.acer.newsreader.Interface.RetrofitBuilder;
import com.example.acer.newsreader.model.Article;
import com.example.acer.newsreader.model.Headline;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class Headlines extends AppCompatActivity {



    RecyclerView recyclerview;
    HeadlineAdapter adapter;
    ArrayList<Article> arrayList = new ArrayList<>();
    Toolbar toolbar;


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
        setContentView(R.layout.headlines);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back_action_name);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Headlines.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        recyclerview = (RecyclerView) findViewById(R.id.recyclerview_view);

        progressDialog = progressSetter();
        progressDialog.show();

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
        String sourceId= getIntent().getExtras().getString("source");
        disposable.add(my_api.getHeadline(sourceId, getString(R.string.api_key))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new io.reactivex.functions.Consumer<Headline>() {
                    @Override
                    public void accept(Headline headline) throws Exception {

                        progressDialog.dismiss();

                        recyclerview.setLayoutManager(new LinearLayoutManager(Headlines.this));
                        adapter = new HeadlineAdapter(headline.getArticles(), Headlines.this);
                        recyclerview.setAdapter(adapter);

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


