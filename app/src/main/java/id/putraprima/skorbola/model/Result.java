package id.putraprima.skorbola.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Result implements Parcelable {

    private int scoreAw, scoreHm;
    private  String nameHome, nameAway;


    public Result() {
    }

    public Result(int scoreAw, int scoreHm, String nameHome, String nameAway) {
        this.scoreAw = scoreAw;
        this.scoreHm = scoreHm;
        this.nameHome = nameHome;
        this.nameAway = nameAway;
    }

    protected Result(Parcel in) {
        scoreAw = in.readInt();
        scoreHm = in.readInt();
        nameHome = in.readString();
        nameAway = in.readString();
    }

    public static final Creator<Result> CREATOR = new Creator<Result>() {
        @Override
        public Result createFromParcel(Parcel in) {
            return new Result(in);
        }

        @Override
        public Result[] newArray(int size) {
            return new Result[size];
        }
    };

    public int getScoreAw() {
        return scoreAw;
    }

    public void setScoreAw(int scoreAw) {
        this.scoreAw = scoreAw;
    }

    public int getScoreHm() {
        return scoreHm;
    }

    public void setScoreHm(int scoreHm) {
        this.scoreHm = scoreHm;
    }

    public String getNameHome() {
        return nameHome;
    }

    public void setNameHome(String nameHome) {
        this.nameHome = nameHome;
    }

    public String getNameAway() {
        return nameAway;
    }

    public void setNameAway(String nameAway) {
        this.nameAway = nameAway;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(scoreAw);
        dest.writeInt(scoreHm);
        dest.writeString(nameHome);
        dest.writeString(nameAway);
    }
}
