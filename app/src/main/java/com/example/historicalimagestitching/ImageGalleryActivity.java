package com.example.historicalimagestitching;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

//https://www.sitepoint.com/handling-displaying-images-android/

public class ImageGalleryActivity extends Activity {

    private Integer[] images = {1, 2, 3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_selection_box);
        addImagesToThegallery();
    }

    private void addImagesToThegallery() {

        LinearLayout imageGallery = findViewById(R.id.imageGallery);
        for (Integer image : images) {
            imageGallery.addView(getImageView(image));
        }
    }


    private View getImageView(Integer image) {
        ImageView imageView = new ImageView(getApplicationContext());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 0, 10, 0);
        imageView.setLayoutParams(lp);
        imageView.setImageResource(image);
        return imageView;
    }

}
