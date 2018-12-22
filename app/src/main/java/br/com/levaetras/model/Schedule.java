package br.com.levaetras.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import lombok.Data;

@Data
public class Schedule  implements Parcelable{
    private String day;
    private String hour;
    private String desc;
    private  int qdtdVacancy; //vequenci a brasileirando
    private  String month;
    private List<Client> clients;


    public Schedule() {
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.day);
        dest.writeString(this.hour);
        dest.writeString(this.desc);
        dest.writeInt(this.qdtdVacancy);
        dest.writeString(this.month);
        dest.writeTypedList(this.clients);
    }

    protected Schedule(Parcel in) {
        this.day = in.readString();
        this.hour = in.readString();
        this.desc = in.readString();
        this.qdtdVacancy = in.readInt();
        this.month = in.readString();
        this.clients = in.createTypedArrayList(Client.CREATOR);
    }

    public static final Creator<Schedule> CREATOR = new Creator<Schedule>() {
        @Override
        public Schedule createFromParcel(Parcel source) {
            return new Schedule(source);
        }

        @Override
        public Schedule[] newArray(int size) {
            return new Schedule[size];
        }
    };
}
