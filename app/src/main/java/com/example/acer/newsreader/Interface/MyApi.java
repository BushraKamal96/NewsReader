package com.example.acer.newsreader.Interface;

import com.example.acer.newsreader.model.Headline;
import com.example.acer.newsreader.model.RootObject;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by acer on 10/16/2018.
 */

public interface MyApi {
@GET ("sources?apiKey=632040e6ff184ac19da403aa919a57f9")
    Observable<RootObject> getRootObject();

    @GET("top-headlines")
    Observable<Headline> getHeadline(@Query("sources") String source,
                                     @Query("apiKey") String apiKey);

}
