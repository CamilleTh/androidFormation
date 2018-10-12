package com.societe.metier;

import android.os.Parcel;
import android.os.Parcelable;


public class Operandes implements Parcelable {

    private double operande1;
        private double operande2;

    public double getOperande1() {
        return operande1;
    }

    public double getOperande2() {
        return operande2;
    }

    public Operandes(double operande1, double operande2) {
        this.operande1 = operande1;
        this.operande2 = operande2;
    }

    public Operandes(Parcel parcel) {
        operande1 = parcel.readDouble();
        operande2 = parcel.readDouble();
    }

    public static final Parcelable.Creator<Operandes> CREATOR = new Parcelable.Creator<Operandes>(){
        @Override
        public Operandes createFromParcel(Parcel parcel) {
            return new Operandes(parcel);
        }

        @Override
        public Operandes[] newArray(int i) {
            return new Operandes[i];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(operande1);
        parcel.writeDouble(operande2);
    }
}
