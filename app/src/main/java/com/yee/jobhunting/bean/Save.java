package com.yee.jobhunting.bean;

import android.content.Context;
import android.content.SharedPreferences;

public class Save {

    public static void saveInfo(Context context, String data) {

        SharedPreferences sharedPreferences = context.getSharedPreferences("config",context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("infoName", data);
        editor.putString("infoGender", data);
        editor.putString("infoBirth", data);
        editor.putString("infoEmail", data);

        editor.commit();
    }

}
