package com.example.imransk.retrofitimageupload;

import com.google.gson.annotations.SerializedName;

/**
 * Created by imran sk on 12/16/2017.
 */

public class ImageClass {

    @SerializedName("title")
    private String title;

    @SerializedName("image")
    private  String image;

    @SerializedName("response")
    private String response;


    public String getResponse() {
        return response;
    }
}
