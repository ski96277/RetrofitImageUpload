package com.example.imransk.retrofitimageupload;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText image_title;
    private Button choose_btn, upload_btn;
    private ImageView img;

    private static final int IMG_REQUEST = 777;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image_title = findViewById(R.id.imageTitle);
        choose_btn = findViewById(R.id.chooseBtn);
        upload_btn = findViewById(R.id.uploadBtn);
        img = findViewById(R.id.imageView);
        choose_btn.setOnClickListener(this);
        upload_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.chooseBtn:
                SelectImage();
                break;
            case R.id.uploadBtn:
                uploadImage();
                break;
        }
    }
private void uploadImage(){
        String image=ImageToString();
        String title= image_title.getText().toString();
        ApiInterface apiInterface=ApiClient.getApiClient().create(ApiInterface.class);

    Call<ImageClass> call=apiInterface.uploadImage(title,image);
    call.enqueue(new Callback<ImageClass>() {
        @Override
        public void onResponse(Call<ImageClass> call, Response<ImageClass> response) {

            ImageClass imageClass=response.body();
            Toast.makeText(MainActivity.this,"Responce From Server"+imageClass.getResponse(),Toast.LENGTH_LONG).show();


            img.setVisibility(View.GONE);
            image_title.setVisibility(View.GONE);
            choose_btn.setEnabled(true);
            upload_btn.setEnabled(false);
            image_title.setText("");

        }

        @Override
        public void onFailure(Call<ImageClass> call, Throwable t) {

        }
    });

}

    private void SelectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMG_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null) {

            Uri path = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), path);

                img.setImageBitmap(bitmap);
                img.setVisibility(View.VISIBLE);
                image_title.setVisibility(View.VISIBLE);
                choose_btn.setEnabled(false);
                upload_btn.setEnabled(true);

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    private String ImageToString() {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imageByte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imageByte, Base64.DEFAULT);

    }
}





