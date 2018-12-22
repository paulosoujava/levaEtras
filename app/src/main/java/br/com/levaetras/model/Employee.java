package br.com.levaetras.model;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Data;

@Data
public class Employee implements Parcelable {

    private String name;
    private String cpf;
    private String cnh;
    private String cnhType;
    private String dateBirthday;
    private String urlPhoto;
    private String status;
    private String email;
    private String phone;
    private  Address address;

    public static final String KEY_EMPLOYEE = "KEY_EMPLOYEE";


    public Employee() {}


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.cpf);
        dest.writeString(this.cnh);
        dest.writeString(this.cnhType);
        dest.writeString(this.dateBirthday);
        dest.writeString(this.urlPhoto);
        dest.writeString(this.email);
        dest.writeString(this.phone);
        dest.writeString(this.status);
        dest.writeParcelable(this.address, flags);
    }

    protected Employee(Parcel in) {
        this.name = in.readString();
        this.cpf = in.readString();
        this.cnh = in.readString();
        this.email = in.readString();
        this.phone = in.readString();
        this.cnhType = in.readString();
        this.dateBirthday = in.readString();
        this.urlPhoto = in.readString();
        this.status = in.readString();
        this.address = in.readParcelable(Address.class.getClassLoader());
    }

    public static final Creator<Employee> CREATOR = new Creator<Employee>() {
        @Override
        public Employee createFromParcel(Parcel source) {
            return new Employee(source);
        }

        @Override
        public Employee[] newArray(int size) {
            return new Employee[size];
        }
    };
}
