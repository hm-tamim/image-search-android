package com.hmtamim.imagesearch.utils;

import android.content.Context;
import android.widget.Toast;


public class ToastUtils {
    public static void show(String message, Context context) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
