package com.example.foodduck;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class FoodItemList implements Parcelable {
    private String name;
    private String tel;
    public ArrayList<String> menu;
    private String review;
    private String date;
    private int CategoryNum;

    protected FoodItemList(Parcel in) {
        name = in.readString();
        tel = in.readString();
        menu = in.createStringArrayList();
        review = in.readString();
        date = in.readString();
        CategoryNum = in.readInt();
    }

    public static final Creator<FoodItemList> CREATOR = new Creator<FoodItemList>() {
        @Override
        public FoodItemList createFromParcel(Parcel in) {
            return new FoodItemList(in);
        }

        @Override
        public FoodItemList[] newArray(int size) {
            return new FoodItemList[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(tel);
        dest.writeStringList(menu);
        dest.writeString(review);
        dest.writeString(date);
        dest.writeInt(CategoryNum);
    }

    public String getName() {
        return name;
    }

    public int getCategorynum() {
        return CategoryNum;
    }

    public String getmenu1() {
        return menu.get(0);
    }

    public String getmenu2() {
        return menu.get(1);
    }

    public String getTel() {
        return tel;
    }

    public String getReview() {
        return review;
    }

    public String getDate() {
        return date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FoodItemList(int CategoryNum) {
        this.CategoryNum = CategoryNum;
        menu = new ArrayList<String>();
    }

    public void setMenu(String item) {
        menu.add(item);
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public void setDate(String date) {
        this.date = date;
    }

}

