package com.example.imransk.retrofitimageupload;

import com.google.gson.annotations.SerializedName;

/**
 * Created by imran sk on 12/16/2017.
 */

//Model Class

public class ImageClass {

//Two variable title and image send image to server and response variable are get the server response

    @SerializedName("title")  // json Propartise
    private String title;

    @SerializedName("image")  // json Propartise
    private String image;

    @SerializedName("response")   // json Propartise
    private String response;

//This Response Come From Server

    public String getResponse() {
        return response;
    }
}
