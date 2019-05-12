package com.example.neyesem.utils;


import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

public class NeyesemExtensions {
    public static boolean isServiceRunning(Context ctx, Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
    public static void loadImage(ImageView view, Object from, Priority priority, boolean circle, int placeholder) {
        MultiTransformation<Bitmap> transformation;
        if(circle){
            transformation = new MultiTransformation<>(new CenterCrop(),new CircleCrop());
        }else{
            transformation = new MultiTransformation<>(new CenterCrop());
        }
        RequestOptions requestOptions = new RequestOptions().priority(priority).transform(transformation).error(placeholder);
        Glide.with(view.getContext()).load(from).apply(requestOptions).into(view);
    }
}
