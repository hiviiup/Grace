package com.evayinfo.grace.media;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by DEVIN on 2017/11/8.
 */

public class PhotoSelectConfig implements Parcelable {
    boolean isMultiSelect;
    int maxSelectCount;

    public PhotoSelectConfig(boolean isMultiSelect, int maxSelectCount) {
        this.isMultiSelect = isMultiSelect;
        this.maxSelectCount = maxSelectCount;
    }

    public PhotoSelectConfig() {
    }

    protected PhotoSelectConfig(Parcel in) {
        isMultiSelect = in.readByte() != 0;
        maxSelectCount = in.readInt();
    }

    public static final Creator<PhotoSelectConfig> CREATOR = new Creator<PhotoSelectConfig>() {
        @Override
        public PhotoSelectConfig createFromParcel(Parcel in) {
            return new PhotoSelectConfig(in);
        }

        @Override
        public PhotoSelectConfig[] newArray(int size) {
            return new PhotoSelectConfig[size];
        }
    };

    public boolean isMultiSelect() {
        return isMultiSelect;
    }

    public void setMultiSelect(boolean multiSelect) {
        isMultiSelect = multiSelect;
    }

    public int getMaxSelectCount() {
        return maxSelectCount;
    }

    public void setMaxSelectCount(int maxSelectCount) {
        this.maxSelectCount = maxSelectCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (isMultiSelect ? 1 : 0));
        dest.writeInt(maxSelectCount);
    }
}
