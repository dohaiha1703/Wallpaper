
package com.duan1.nhom4.wallpaper.model.cate;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CateItem {

    @SerializedName("HD_WALLPAPER")
    @Expose
    private List<CATEGORY> cATEGORY = null;

    public List<CATEGORY> getCATEGORY() {
        return cATEGORY;
    }

    public void setCATEGORY(List<CATEGORY> cATEGORY) {
        this.cATEGORY = cATEGORY;
    }

}
