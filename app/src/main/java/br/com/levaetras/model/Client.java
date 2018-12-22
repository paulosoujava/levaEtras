package br.com.levaetras.model;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Data;

@Data
public class Client implements Parcelable {
    private String name;
    private String cpf;
    private String photo;
    private String email;
    private String phone;
    private Address address;
    private  String status;


    public Client() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.cpf);
        dest.writeString(this.photo);
        dest.writeString(this.email);
        dest.writeString(this.phone);
        dest.writeParcelable(this.address, flags);
        dest.writeString(this.status);
    }

    protected Client(Parcel in) {
        this.name = in.readString();
        this.cpf = in.readString();
        this.photo = in.readString();
        this.email = in.readString();
        this.phone = in.readString();
        this.address = in.readParcelable(Address.class.getClassLoader());
        this.status = in.readString();
    }

    public static final Creator<Client> CREATOR = new Creator<Client>() {
        @Override
        public Client createFromParcel(Parcel source) {
            return new Client(source);
        }

        @Override
        public Client[] newArray(int size) {
            return new Client[size];
        }
    };
}
