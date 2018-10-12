package com.scha.calculetteaidlclient;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by scha on 10/10/2017.
 */

public class Operation implements Parcelable {

    public float operand1;
    public float operand2;
    public int operateur;

    public static int OP_PLUS = 1;
    public static int OP_MOINS = 2;
    public static int OP_DIVISE = 3;

    public Operation(float op1, float op2, int op)
    {
        super();
        operand1 = op1;
        operand2 = op2;
        operateur = op;
    }

    protected Operation(Parcel in)
    {
        operand1 = in.readFloat();
        operand2 = in.readFloat();
        operateur = in.readInt();
    }

    public static final Creator<Operation> CREATOR = new Creator<Operation>()
    {
        @Override
        public Operation createFromParcel(Parcel source)
        {
            return new Operation(source);
        }
        @Override
        public Operation[] newArray(int taille)
        {
            return new Operation[taille];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(operand1);
        dest.writeFloat(operand2);
        dest.writeInt(operateur);
    }

}
