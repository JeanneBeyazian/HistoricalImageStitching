package com.example.historicalimagestitching;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.NonNull;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageProxy;

import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

public class ImageAnalyzer implements ImageAnalysis.Analyzer {

    private long  lastAnalyzedTimestamp = 0L;


    @Override
    public void analyze(@NonNull ImageProxy image) {
        long currentTimestamp = System.currentTimeMillis();

        if (currentTimestamp - lastAnalyzedTimestamp >= TimeUnit.SECONDS.toMillis(1)) {

            // Since format in ImageAnalysis is YUV, image.planes[0]
            // contains the Y (luminance) plane
            ByteBuffer buffer = image.getPlanes()[0].getBuffer();

            int imgWidth = 10;
            int imgHeight = 10;
            byte[] data = buffer.array();
            Bitmap bitmap = convertImg(data);
            int[] pixels = null;

            bitmap.getPixels(pixels, 0, 0, 0, 0, imgWidth, imgHeight);

            // NOW CAN DO ANALYSIS !!!!

            image.close();

        }
    }


    private Bitmap convertImg(byte[] bytes){

        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes , 0, bytes.length);
        return bitmap;

    }
}
