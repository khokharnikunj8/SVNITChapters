package com.example.nikunj.svnitchapters;

/**
 * Created by nikunj on 24/3/17.
 */


public class publish {
    private String title , image , desc;
    public publish(){

    }
    public publish(String title,String image,String desc)
    {
        this.title=title;
        this.image=image;
        this.desc=desc;
    }

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
}
