package com.modules.basemodule.http.bean;

public class ResponseRemoteConfig {

    private String sticker_name;
    private String sticker_version;
    private String music_name;
    private String music_version;

    @Override
    public String toString() {
        return "sticker_name='" + sticker_name + '\'' +
                ", sticker_version='" + sticker_version + '\'' +
                ", music_name='" + music_name + '\'' +
                ", music_version='" + music_version;
    }
}
