package br.com.levaetras.model;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Data;

@Data
public class Score implements Parcelable {
    private String nameClient;
    private String score;
    private String answer;
    private String desc;



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nameClient);
        dest.writeString(this.score);
        dest.writeString(this.answer);
        dest.writeString(this.desc);
    }

    public Score() {
    }

    protected Score(Parcel in) {
        this.nameClient = in.readString();
        this.score = in.readString();
        this.answer = in.readString();
        this.desc = in.readString();
    }

    public static final Parcelable.Creator<Score> CREATOR = new Parcelable.Creator<Score>() {
        @Override
        public Score createFromParcel(Parcel source) {
            return new Score(source);
        }

        @Override
        public Score[] newArray(int size) {
            return new Score[size];
        }
    };
}
