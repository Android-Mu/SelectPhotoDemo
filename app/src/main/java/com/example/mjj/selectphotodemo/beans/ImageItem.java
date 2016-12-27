package com.example.mjj.selectphotodemo.beans;

import android.graphics.Bitmap;

import com.example.mjj.selectphotodemo.utils.Bimp;

import java.io.IOException;
import java.io.Serializable;

/**
 * Description：单个图片实体类
 * <p>
 * Created by Mjj on 2016/12/2.
 */

public class ImageItem implements Serializable {

    public String imageId;
    public String thumbnailPath;
    public String imagePath;
    private Bitmap bitmap;
    public boolean isSelected = false;

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }

    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public Bitmap getBitmap() {
        if (bitmap == null) {
            try {
                bitmap = Bimp.revitionImageSize(imagePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

}
