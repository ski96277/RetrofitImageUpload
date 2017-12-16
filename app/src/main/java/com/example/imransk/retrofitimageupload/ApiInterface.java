package com.example.imransk.retrofitimageupload;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by imran sk on 12/16/2017.
 */

public interface ApiInterface {

    // this method commuticate with API


    @FormUrlEncoded
    @POST("upload.php")
    Call<ImageClass> uploadImage(@Field("title") String title, @Field("image") String image);
}
