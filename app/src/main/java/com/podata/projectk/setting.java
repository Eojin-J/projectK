package com.podata.projectk;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class setting {
    // Preference 읽기
    public static String getConfigValue(Context context, String key) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        System.out.println("불러오기작동" + key);
        return pref.getString(key, "2");
    }
    // Preference 쓰기
    public static void setConfigValue(Context context, String key, String value){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        System.out.println("저장작동" + value);
        editor.commit();
    }
}

