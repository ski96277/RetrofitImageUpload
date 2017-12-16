package com.example.imransk.retrofitimageupload;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by imran sk on 12/16/2017.
 */

public class ApiClient {

    //Change your ip address as you pc
    private static final  String BaseUrl = "http://192.168.0.104/imageupload/";
//    private static final  String BaseUrl = "http://192.168.147.2/imageupload/";
    private static Retrofit retrofit;

    public static Retrofit getApiClient() {
        if (retrofit==null){
            retrofit=new Retrofit.Builder().baseUrl(BaseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
