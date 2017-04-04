package com.example.nikunj.svnitchapters;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by nikunj on 24/3/17.
 */


public class publish implements Parcelable {
    private String title , image , desc,date,description,likes;
    public publish(){

    }
    public publish(String title,String image,String desc)
    {
        this.title=title;
        this.image=image;
        this.desc=desc;
    }

    protected publish(Parcel in) {
        title = in.readString();
        image = in.readString();
        desc = in.readString();
        date=in.readString();
        likes=in.readString();
        description=in.readString();
    }

    public static final Creator<publish> CREATOR = new Creator<publish>() {
        @Override
        public publish createFromParcel(Parcel in) {
            return new publish(in);
        }

        @Override
        public publish[] newArray(int size) {
            return new publish[size];
        }
    };

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate(){
        return date;
    }

    public String getDescription(){
        return  description;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(image);
        dest.writeString(desc);
        dest.writeString(date);
        dest.writeString(likes);
        dest.writeString(description);
    }
}
