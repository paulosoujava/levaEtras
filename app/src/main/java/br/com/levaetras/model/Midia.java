package br.com.levaetras.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import br.com.levaetras.utils.Prefs;
import lombok.Data;

@Data
public class Midia implements Parcelable {
    private String facebook;
    private String instagram;
    private String site;
    private String youtube;
    private static Gson gson = new Gson();
    private static Type type;
    public  static  final  String MIDIA_KEY = "MIDIA_KEY";


    public Midia() {}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.facebook);
        dest.writeString(this.instagram);
        dest.writeString(this.site);
        dest.writeString(this.youtube);
    }

    protected Midia(Parcel in) {
        this.facebook = in.readString();
        this.instagram = in.readString();
        this.site = in.readString();
        this.youtube = in.readString();
    }

    public static final Parcelable.Creator<Midia> CREATOR = new Parcelable.Creator<Midia>() {
        @Override
        public Midia createFromParcel(Parcel source) {
            return new Midia(source);
        }

        @Override
        public Midia[] newArray(int size) {
            return new Midia[size];
        }
    };


    public static Midia getMidia(Context context) {
        String json = Prefs.getStringInPrefs(context, Midia.MIDIA_KEY);
        if (json == null) return null;
        type = new TypeToken<Adm>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    public static void saveMidia(Context context, Adm a) {
        Prefs.setStringInPrefs(context, Midia.MIDIA_KEY, gson.toJson(a));
    }
}
