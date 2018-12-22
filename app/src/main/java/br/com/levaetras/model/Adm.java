package br.com.levaetras.model;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import br.com.levaetras.utils.Prefs;
import lombok.Data;

@Data
public class Adm {
    private String name;
    private String cpf;
    private  String email;
    private String phone;
    public static final String ADM_KEY = "ADM_KEY";
    private static Gson gson = new Gson();
    private static Type type;


    public static Adm getAdm(Context context) {
        String json = Prefs.getStringInPrefs(context, Adm.ADM_KEY);
        if (json == null) return null;
        type = new TypeToken<Adm>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    public static void saveAdm(Context context, Adm a) {
        Prefs.setStringInPrefs(context, Adm.ADM_KEY, gson.toJson(a));
    }
}
