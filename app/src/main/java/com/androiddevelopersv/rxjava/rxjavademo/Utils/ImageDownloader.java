package com.androiddevelopersv.rxjava.rxjavademo.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.concurrent.Callable;

import io.reactivex.Observable;

public class ImageDownloader {
  @Deprecated
  public static Observable<Bitmap> getImageBitmapObservable(final String imageURL){
    return Observable.fromCallable(new Callable<Bitmap>() {
      @Override
      public Bitmap call() throws Exception {
        return downloadImage(imageURL);
      }
    });
  }

  private static Bitmap downloadImage(String imageURL) throws IOException {
    java.net.URL url = new java.net.URL(imageURL);
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setDoInput(true);
    connection.connect();
    InputStream input = connection.getInputStream();
    Bitmap imageBitmap = BitmapFactory.decodeStream(input);
    return imageBitmap;
  }
}