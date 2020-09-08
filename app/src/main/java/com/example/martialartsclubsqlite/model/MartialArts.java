package com.example.martialartsclubsqlite.model;

public class MartialArts {

    private int martialArtsId;
    private String martialArtsName;
    private double martialArtsPrice;
    private String martialArtsColor;

    public MartialArts(int id,String name,double price,String color){

       setMartialArtsId(id);
       setMartialArtsName(name);
       setMartialArtsPrice(price);
       setMartialArtsColor(color);
    }

    public int getMartialArtsId() {
        return martialArtsId;
    }

    public void setMartialArtsId(int martialArtsId) {
        this.martialArtsId = martialArtsId;
    }

    public String getMartialArtsName() {
        return martialArtsName;
    }

    public void setMartialArtsName(String martialArtsName) {
        this.martialArtsName = martialArtsName;
    }

    public double getMartialArtsPrice() {
        return martialArtsPrice;
    }

    public void setMartialArtsPrice(double martialArtsPrice) {
        this.martialArtsPrice = martialArtsPrice;
    }

    public String getMartialArtsColor() {
        return martialArtsColor;
    }

    public void setMartialArtsColor(String martialArtsColor) {
        this.martialArtsColor = martialArtsColor;
    }

    @Override
    public String toString() {
        return "martialArtsId= " + getMartialArtsId()+
                "\nmartialArtsName= " + getMartialArtsName() +
                "\nmartialArtsPrice= " + getMartialArtsPrice() +
                "\nmartialArtsColor= " + getMartialArtsColor();
    }
}
