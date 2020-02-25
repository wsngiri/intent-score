package id.putraprima.skorbola.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class Score implements Parcelable {

    private  String nameHome;
    private  String nameAway;
    private Uri homeUri, awayUri;

    public Score(String nameHome, String nameAway, Uri homeUri, Uri awayUri) {
        this.nameHome = nameHome;
        this.nameAway = nameAway;
        this.homeUri = homeUri;
        this.awayUri = awayUri;
    }

    protected Score(Parcel in) {
        nameHome = in.readString();
        nameAway = in.readString();
        homeUri = in.readParcelable(Uri.class.getClassLoader());
        awayUri = in.readParcelable(Uri.class.getClassLoader());
    }

    public static final Creator<Score> CREATOR = new Creator<Score>() {
        @Override
        public Score createFromParcel(Parcel in) {
            return new Score(in);
        }

        @Override
        public Score[] newArray(int size) {
            return new Score[size];
        }
    };

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

    public Uri getHomeUri() {
        return homeUri;
    }

    public void setHomeUri(Uri homeUri) {
        this.homeUri = homeUri;
    }

    public Uri getAwayUri() {
        return awayUri;
    }

    public void setAwayUri(Uri awayUri) {
        this.awayUri = awayUri;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nameHome);
        dest.writeString(nameAway);
        dest.writeParcelable(homeUri, flags);
        dest.writeParcelable(awayUri, flags);
    }
}

