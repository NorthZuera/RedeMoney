package br.com.testes.testederecycleview.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class Car implements Parcelable {
    private String model;
    private String brand;
    private String description;
    private int category;
    private int photo;

    public Car(){}
    public Car(String m, String b, int p, String d) {
        model = m;
        brand = b;
        photo = p;
        description = d;
    }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getCategory() { return category; }
    public void setCategory(int category) { this.category = category; }

    public int getPhoto() { return photo; }
    public void setPhoto(int photo) { this.photo = photo; }

    //PARCELABLE
        public Car(Parcel parcel) {
            setModel(parcel.readString());
            setBrand(parcel.readString());
            setDescription(parcel.readString());
            setCategory(parcel.readInt());
            setPhoto(parcel.readInt());
        }
        @Override
        public int describeContents() {return 0;}
        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString( getModel() );
            dest.writeString( getBrand() );
            dest.writeString( getDescription() );
            dest.writeInt( getCategory() );
            dest.writeInt( getPhoto() );
        }
        public static final Parcelable.Creator<Car> CREATOR = new Parcelable.Creator<Car>(){
            @Override
            public Car createFromParcel(Parcel source) { return new Car(source); }
            @Override
            public Car[] newArray(int size) {return new Car[size];}
        };
}
