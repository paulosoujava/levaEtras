package br.com.levaetras.utils;

import android.content.Context;
import android.content.SharedPreferences;


public class Prefs {

    public static final String PREFS = "PREFS_LT";

    public static SharedPreferences.Editor editor;

    public static void setStringInPrefs(Context context, String key, String value) {
        editor = openEdit(context).edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getStringInPrefs(Context context, String key) {
        return openEdit(context).getString(key, null);
    }

    public static void setBooleanInPrefs(Context context, String key, Boolean value) {
        editor = openEdit(context).edit();
        editor.putBoolean(key, value);
        editor.apply();
    }
    public static Boolean getBooleanInPrefs(Context context, String key) {
        return openEdit(context).getBoolean(key, false);
    }

    public static void setIntInPrefs(Context context, String key, int value) {
        editor = openEdit(context).edit();
        editor.putInt(key, value);
        editor.apply();
    }
    public static int getIntInPrefs(Context context, String key) {
        return openEdit(context).getInt(key, 0);
    }

    public static void removeThisKey(Context context, String key) {
        openEdit(context).edit().remove(key).apply();
    }

    private static SharedPreferences openEdit(Context context){
        return context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }



}
