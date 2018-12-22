package br.com.levaetras.model;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Data;

@Data
public class Address implements Parcelable {
    private String address;
    private String number;
    private String zip;
    private String city;
    private String state;
    private String reference;
    private String neighborhood;

    public Address() {}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.address);
        dest.writeString(this.number);
        dest.writeString(this.zip);
        dest.writeString(this.city);
        dest.writeString(this.state);
        dest.writeString(this.reference);
        dest.writeString(this.neighborhood);
    }

    protected Address(Parcel in) {
        this.address = in.readString();
        this.number = in.readString();
        this.zip = in.readString();
        this.city = in.readString();
        this.state = in.readString();
        this.reference = in.readString();
        this.neighborhood = in.readString();
    }

    public static final Parcelable.Creator<Address> CREATOR = new Parcelable.Creator<Address>() {
        @Override
        public Address createFromParcel(Parcel source) {
            return new Address(source);
        }

        @Override
        public Address[] newArray(int size) {
            return new Address[size];
        }
    };
}
