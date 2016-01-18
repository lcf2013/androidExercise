package com.zhenai.aidlserver;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by admin on 2016/1/18.
 */
public class Book implements Parcelable{
    private String bookName;
    private int bookPrice;

    protected  Book(){}
    protected Book(Parcel in) {
        bookName = in.readString();
        bookPrice = in.readInt();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(bookName);
        parcel.writeInt(bookPrice);
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(int bookPrice) {
        this.bookPrice = bookPrice;
    }
}
