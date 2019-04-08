
package com.duan1.nhom4.wallpaper.model.gif;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GIF {

    @SerializedName("HD_WALLPAPER")
    @Expose
    private List<GifImage> hDWALLPAPER = null;

    public List<GifImage> getAllGif() {
        return hDWALLPAPER;
    }

    public void setHDWALLPAPER(List<GifImage> hDWALLPAPER) {
        this.hDWALLPAPER = hDWALLPAPER;
    }

}
