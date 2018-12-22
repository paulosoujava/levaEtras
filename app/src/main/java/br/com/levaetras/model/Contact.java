package br.com.levaetras.model;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Data;

@Data
public class Contact implements Parcelable {
    private String uid;
    private String device;
    private String name;
    private String date_hour;
    private String url_photo;
    private  String status; //read not_read


    public Contact() {}


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.uid);
        dest.writeString(this.device);
        dest.writeString(this.name);
        dest.writeString(this.date_hour);
        dest.writeString(this.url_photo);
        dest.writeString(this.status);
    }

    protected Contact(Parcel in) {
        this.uid = in.readString();
        this.device = in.readString();
        this.name = in.readString();
        this.date_hour = in.readString();
        this.url_photo = in.readString();
        this.status = in.readString();
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel source) {
            return new Contact(source);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };
}
