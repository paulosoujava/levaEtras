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
public class Place implements Parcelable {

    private String name;
    private String cnpj;
    private String payment;
    private String about;
    private String photo;
    private String photoLocal;
    private String email;
    private String phoneCel;
    private String phoneRes;
    private Midia midia;
    private Address address;
    private static Gson gson = new Gson();
    private static Type type;
    public  static  final  String PLACE_KEY = "PLACE_KEY";


    public Place() {}


    public static Place getPlace(Context context) {
        String json = Prefs.getStringInPrefs(context, Place.PLACE_KEY);
        if (json == null) return null;
        type = new TypeToken<Place>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    public static void savePlace(Context context, Place a) {
        Prefs.setStringInPrefs(context, Place.PLACE_KEY, gson.toJson(a));
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.cnpj);
        dest.writeString(this.payment);
        dest.writeString(this.about);
        dest.writeString(this.photo);
        dest.writeString(this.photoLocal);
        dest.writeString(this.email);
        dest.writeString(this.phoneCel);
        dest.writeString(this.phoneRes);
        dest.writeParcelable(this.midia, flags);
        dest.writeParcelable(this.address, flags);
    }

    protected Place(Parcel in) {
        this.name = in.readString();
        this.cnpj = in.readString();
        this.payment = in.readString();
        this.about = in.readString();
        this.photo = in.readString();
        this.photoLocal = in.readString();
        this.email = in.readString();
        this.phoneCel = in.readString();
        this.phoneRes = in.readString();
        this.midia = in.readParcelable(Midia.class.getClassLoader());
        this.address = in.readParcelable(Address.class.getClassLoader());
    }

    public static final Creator<Place> CREATOR = new Creator<Place>() {
        @Override
        public Place createFromParcel(Parcel source) {
            return new Place(source);
        }

        @Override
        public Place[] newArray(int size) {
            return new Place[size];
        }
    };
}
