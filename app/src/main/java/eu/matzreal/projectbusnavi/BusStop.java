package eu.matzreal.projectbusnavi;

import android.os.Parcel;
import android.os.Parcelable;

public class BusStop implements Parcelable {
    private int id;
    private String name;

    public BusStop(int id, String name) {
        this.id = id;
        this.name = name;
    }

    protected BusStop(Parcel in) {
        id = in.readInt();
        name = in.readString();
    }

    public static final Creator<BusStop> CREATOR = new Creator<BusStop>() {
        @Override
        public BusStop createFromParcel(Parcel in) {
            return new BusStop(in);
        }

        @Override
        public BusStop[] newArray(int size) {
            return new BusStop[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
    }
}
