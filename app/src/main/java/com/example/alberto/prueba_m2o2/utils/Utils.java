package com.example.alberto.prueba_m2o2.utils;

import android.content.Context;
import android.util.DisplayMetrics;

public class Utils {
    public static int convertDpToPixel(int dp, Context context){
        return dp * ((int) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }
}
