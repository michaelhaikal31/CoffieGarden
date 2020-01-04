package com.royalhouse.coffiegarden.model;

public class modelProduk {
    private Integer start;
    private String nameProduk, hargaProduk, imageProduk, deskripsiProduk;

    public modelProduk(Integer start, String nameProduk, String hargaProduk, String imageProduk, String deskripsiProduk) {
        this.start = start;
        this.nameProduk = nameProduk;
        this.hargaProduk = hargaProduk;
        this.imageProduk = imageProduk;
        this.deskripsiProduk = deskripsiProduk;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public String getNameProduk() {
        return nameProduk;
    }

    public void setNameProduk(String nameProduk) {
        this.nameProduk = nameProduk;
    }

    public String getHargaProduk() {
        return hargaProduk;
    }

    public void setHargaProduk(String hargaProduk) {
        this.hargaProduk = hargaProduk;
    }

    public String getImageProduk() {
        return imageProduk;
    }

    public void setImageProduk(String imageProduk) {
        this.imageProduk = imageProduk;
    }

    public String getDeskripsiProduk() {
        return deskripsiProduk;
    }

    public void setDeskripsiProduk(String deskripsiProduk) {
        this.deskripsiProduk = deskripsiProduk;
    }
}
