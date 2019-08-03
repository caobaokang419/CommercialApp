package com.gary.commercial.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import retrofit2.http.Url;

/**
 * Created by GaryCao on 2019/08/03.
 */
public class GlideUtil {
    public static void loadImageUrl(Context context, Url imageUrl, ImageView imageView){
        Glide.with(context).load(imageUrl).into(imageView);
    }

    public static void loadImageUrl(Context context, Url imageUrl, ImageView imageView, int defResId){
        Glide.with(context).load(imageUrl).placeholder(defResId).into(imageView);
    }
}
