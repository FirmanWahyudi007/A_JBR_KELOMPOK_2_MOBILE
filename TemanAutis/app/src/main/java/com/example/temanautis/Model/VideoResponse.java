package com.example.temanautis.Model;

import java.util.List;

public class VideoResponse {
    private String code,message;
    private List<VideoModel> videoModelList;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<VideoModel> getVideoModelList() {
        return videoModelList;
    }

    public void setVideoModelList(List<VideoModel> videoModelList) {
        this.videoModelList = videoModelList;
    }
}
