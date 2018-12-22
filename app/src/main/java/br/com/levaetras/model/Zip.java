package br.com.levaetras.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.ArraySet;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import br.com.levaetras.utils.Prefs;
import lombok.Data;

@Data
public class Zip implements Parcelable {

    private String zip;
    private String addess;
    public static final  String ZIP_KEY = "ZIP_KEY";
    private static Gson gson = new Gson();
    private static Type type;



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.zip);
        dest.writeString(this.addess);
    }

    public Zip() {}

    public Zip(String zip) {
        this.zip = zip;
    }

    protected Zip(Parcel in) {
        this.zip = in.readString();
        this.addess = in.readString();
    }

    public static final Parcelable.Creator<Zip> CREATOR = new Parcelable.Creator<Zip>() {
        @Override
        public Zip createFromParcel(Parcel source) {
            return new Zip(source);
        }

        @Override
        public Zip[] newArray(int size) {
            return new Zip[size];
        }
    };


    public static Set<Zip> getZip(Context context) {
        String json = Prefs.getStringInPrefs(context, Adm.ADM_KEY);
        if (json == null) return null;
        type = new TypeToken<Set<Zip>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    public static void saveZip(Context context, Zip z) {
        Set<Zip> lis = getZip(context);
        if(lis == null ) lis = new LinkedHashSet<>();
        lis.add(z);
        Prefs.setStringInPrefs(context, Adm.ADM_KEY, gson.toJson(lis));
    }
    public static void removeZip(Context context, Zip z) {
        Set<Zip> lis = getZip(context);
        if(lis == null ) return;
        lis.remove(z);
        Prefs.setStringInPrefs(context, Adm.ADM_KEY, gson.toJson(lis));
    }

}
