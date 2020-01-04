package com.royalhouse.coffiegarden.model;

import android.content.Intent;

public class modelClient {
    private String ImgClient;
    private String namaClient;
    private String provisiClient;
    private String commentClient;

    public modelClient(String imgClient, String namaClient, String provisiClient, String commentClient) {
        ImgClient = imgClient;
        this.namaClient = namaClient;
        this.provisiClient = provisiClient;
        this.commentClient = commentClient;
    }

    public String getImgClient() {
        return ImgClient;
    }

    public void setImgClient(String imgClient) {
        ImgClient = imgClient;
    }

    public String getNamaClient() {
        return namaClient;
    }

    public void setNamaClient(String namaClient) {
        this.namaClient = namaClient;
    }

    public String getProvisiClient() {
        return provisiClient;
    }

    public void setProvisiClient(String provisiClient) {
        this.provisiClient = provisiClient;
    }

    public String getCommentClient() {
        return commentClient;
    }

    public void setCommentClient(String commentClient) {
        this.commentClient = commentClient;
    }
}
