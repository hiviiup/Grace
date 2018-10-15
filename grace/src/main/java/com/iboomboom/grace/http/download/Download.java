package com.iboomboom.grace.http.download;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hiviiup on 2017/3/28.
 */

public class Download implements Parcelable {

    private int progress;
    private long currentSize;
    private long totalSize;

    public Download() {
    }

    protected Download(Parcel in) {
        progress = in.readInt();
        currentSize = in.readLong();
        totalSize = in.readLong();
    }

    public static final Creator<Download> CREATOR = new Creator<Download>() {
        @Override
        public Download createFromParcel(Parcel in) {
            return new Download(in);
        }

        @Override
        public Download[] newArray(int size) {
            return new Download[size];
        }
    };

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public long getCurrentSize() {
        return currentSize;
    }

    public void setCurrentSize(long currentSize) {
        this.currentSize = currentSize;
    }

    public long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(progress);
        dest.writeLong(currentSize);
        dest.writeLong(totalSize);
    }
}
