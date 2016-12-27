package com.example.mjj.selectphotodemo.beans;

import java.io.Serializable;

/**
 * Description：图片选择界面图片实体
 * <p>
 * Created by Mjj on 2016/12/2.
 */
public class Photo implements Serializable {

    private int id;
    private String path;  //路径
    private boolean isCamera;

    public Photo(String path) {
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isCamera() {
        return isCamera;
    }

    public void setIsCamera(boolean isCamera) {
        this.isCamera = isCamera;
    }
}
