package com.example.booklibrary;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {
    private String name;
    private String author;
    private String language;
    private int pages;
    private String shortDescription;
    private String longDescription;
    private String imageUrl;
    public Book(){}

    public Book(String name, String author, String language,
                int pages, String shortDescription, String longDescription,
                String imageUrl) {
        this.name = name;
        this.author = author;
        this.language = language;
        this.pages = pages;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.imageUrl = imageUrl;
    }

    protected Book(Parcel in) {
        name = in.readString();
        author = in.readString();
        language = in.readString();
        pages = in.readInt();
        shortDescription = in.readString();
        longDescription = in.readString();
        imageUrl = in.readString();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPages() {
        return String.valueOf(pages);
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(author);
        parcel.writeString(language);
        parcel.writeInt(pages);
        parcel.writeString(shortDescription);
        parcel.writeString(longDescription);
        parcel.writeString(imageUrl);
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", language='" + language + '\'' +
                ", pages=" + pages +
                ", shortDescription='" + shortDescription + '\'' +
                ", longDescription='" + longDescription + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
