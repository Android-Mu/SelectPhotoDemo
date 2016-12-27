package com.example.mjj.selectphotodemo.beans;

import java.io.Serializable;
import java.util.List;

/**
 * Description：相册列表实体
 * <p>
 * Created by Mjj on 2016/12/2.
 */

public class PhotoFolder implements Serializable {

    // 文件夹名
    private String name;
    // 文件夹路径
    private String dirPath;
    // 该文件夹下图片列表
    private List<Photo> photoList;
    // 标识是否选中该文件夹
    private boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirPath() {
        return dirPath;
    }

    public void setDirPath(String dirPath) {
        this.dirPath = dirPath;
    }

    public List<Photo> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<Photo> photoList) {
        this.photoList = photoList;
    }

}
